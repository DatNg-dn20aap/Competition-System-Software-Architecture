import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class UI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField staffIDField;
    private StaffList staffList;
    private CompetitorList competitorList;

    public UI(StaffList staffList, CompetitorList competitorList) {
        this.staffList = staffList;
        this.competitorList = competitorList;
        setTitle("Staff Management System");
        setSize(720, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        createMainPanel();
        createStaffTypePanels();

        add(cardPanel);
    }

    private void createMainPanel() {
        JPanel mainPanel = new JPanel();
        staffIDField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffID = staffIDField.getText();
                Staff staff = staffList.getStaffFromID(staffID);

                if (staff != null) {
                    cardLayout.show(cardPanel, staff.getStaffType().toString());
                } else {
                    JOptionPane.showMessageDialog(UI.this, "Staff ID not found");
                }
            }
        });

        mainPanel.add(staffIDField);
        mainPanel.add(submitButton);
        cardPanel.add(mainPanel, "MainPage");
    }

    private void createStaffTypePanels() {
        JPanel audiencePanel = createPanelWithExitButton(Color.BLUE, "Audience");
        JPanel refereePanel = createPanelWithExitButton(Color.GREEN, "Referee");
        JPanel emergencyPanel = createPanelWithExitButton(Color.RED, "Emergency");
        JPanel dataEntryPanel = createPanelWithExitButton(Color.YELLOW, "DataEntry");
        JPanel officialPanel = createPanelWithExitButton(Color.ORANGE, "Official");

        cardPanel.add(audiencePanel, "Audience");
        cardPanel.add(refereePanel, "Referee");
        cardPanel.add(emergencyPanel, "Emergency");
        cardPanel.add(dataEntryPanel, "DataEntry");
        cardPanel.add(officialPanel, "Official");
    }

    private JPanel createPanelWithExitButton(Color backgroundColor, String name) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(backgroundColor);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainPage");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(exitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Use switch case to handle different panel types
        switch (name) {
            case "Audience":
                JTextArea reportArea = new JTextArea();
                reportArea.setEditable(false);
                String report = competitorList.generateLimitedReport();
                reportArea.setText(report);
                JScrollPane scrollPane = new JScrollPane(reportArea);
                panel.add(scrollPane, BorderLayout.CENTER);
                break;
            case "Referee":
                JPanel refereesPanel = new JPanel(new GridLayout(4, 1)); // Grid layout for 4 buttons
                // Create buttons and add them to the refereesPanel
                addRefereePanelButtons(refereesPanel);
                panel.add(refereesPanel, BorderLayout.CENTER);
                break;
            case "Emergency":
                JPanel EmergencyPanel = new JPanel(new GridLayout(4, 1)); // Grid layout for 4 buttons
                // Create buttons and add them to the EmergencyPanel
                addEmergencyPanelButtons(EmergencyPanel);
                panel.add(EmergencyPanel, BorderLayout.CENTER);
                break;
            case "DataEntry":
                JPanel DataEntryPanel = new JPanel(new GridLayout(4, 1)); // Grid layout for 4 buttons
                // Create buttons and add them to the EmergencyPanel
                addDataEntryPanelButtons(DataEntryPanel);
                panel.add(DataEntryPanel, BorderLayout.CENTER);
                break;
            case "Official":
                JPanel OfficialPanel = new JPanel(new GridLayout(4, 1)); // Grid layout for 4 buttons
                // Create buttons and add them to the EmergencyPanel
                addOfficialPanelButtons(OfficialPanel);
                panel.add(OfficialPanel, BorderLayout.CENTER);
                break;
            // Additional cases can be added here for other types
            // case "Official":
            // ...
        }

        return panel;
    }

    private void addRefereePanelButtons(JPanel refereesPanel) {
        JButton showGamesButton = new JButton("Show All Matches Report");
        showGamesButton.addActionListener(e -> showAllGamesReport());
        refereesPanel.add(showGamesButton);

        JButton searchCompetitorButton = new JButton("Search for Competitor Details");
        searchCompetitorButton.addActionListener(e -> searchForCompetitor());
        refereesPanel.add(searchCompetitorButton);

        JButton viewResultsButton = new JButton("View Results/Scores of An Competitor");
        viewResultsButton.addActionListener(e -> searchCompetitorforScores());
        refereesPanel.add(viewResultsButton);
    }

    private void addEmergencyPanelButtons(JPanel emergencyPanel) {
        JButton searchCompetitorButton = new JButton("Search for Competitor Details");
        searchCompetitorButton.addActionListener(e -> searchForCompetitorExtraDetails());
        emergencyPanel.add(searchCompetitorButton);
    }

    private void addDataEntryPanelButtons(JPanel dataEntryPanel) {
        JButton searchCompetitorButton = new JButton("Search for Competitor Details");
        searchCompetitorButton.addActionListener(e -> searchForCompetitor());
        dataEntryPanel.add(searchCompetitorButton);

        JButton viewResultsButton = new JButton("View Results/Scores of An Competitor");
        viewResultsButton.addActionListener(e -> searchCompetitorforScores());
        dataEntryPanel.add(viewResultsButton);

        JButton addScoresButton = new JButton("Add Scores for an Competitor");
        addScoresButton.addActionListener(e -> AddingScoresForCompetitor());
        dataEntryPanel.add(addScoresButton);
    }

    private void addOfficialPanelButtons(JPanel officialPanel) {
        //Place Holder for Later.
    }

    // Placeholder methods for button functionalities
    private void showAllGamesReport() {
        String report = competitorList.generateReport(); // Call generateReport method

        // Display the report in a dialog box
        JTextArea textArea = new JTextArea(15, 50); // Set dimensions as needed
        textArea.setText(report);
        textArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(this, scrollPane, "All Games Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchForCompetitorExtraDetails() {
        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor ID:", "Search Competitor", JOptionPane.QUESTION_MESSAGE);

        if (competitorID != null && !competitorID.trim().isEmpty()) {
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());

            if (competitor != null) {
                // Ask user if they want to view full details
                int response = JOptionPane.showConfirmDialog(this, "Competitor found. Do you want to view full Extra details?", "Competitor Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    // User chose to view full details
                    viewCompetitorExtraDetails(competitor);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor found with ID: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
        // User cancelled the operation if competitorID is null
    }

    private void searchForCompetitor() {
        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor ID:", "Search Competitor", JOptionPane.QUESTION_MESSAGE);

        if (competitorID != null && !competitorID.trim().isEmpty()) {
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());

            if (competitor != null) {
                // Ask user if they want to view full details
                int response = JOptionPane.showConfirmDialog(this, "Competitor found. Do you want to view full details?", "Competitor Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    // User chose to view full details
                    viewCompetitorDetails(competitor);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor found with ID: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
        // User cancelled the operation if competitorID is null
    }

    private void viewCompetitorExtraDetails(Competitor competitor) {
        String details = competitorList.getCompetitorExtraDetails(competitor);
        JOptionPane.showMessageDialog(this, details, "Competitor Extra Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewCompetitorDetails(Competitor competitor) {
        String details = competitorList.getCompetitorDetails(competitor);
        JOptionPane.showMessageDialog(this, details, "Competitor Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchCompetitorforScores() {
        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor ID:", "Search Competitor", JOptionPane.QUESTION_MESSAGE);

        if (competitorID != null && !competitorID.trim().isEmpty()) {
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());

            if (competitor != null) {
                // Ask user if they want to view full details
                int response = JOptionPane.showConfirmDialog(this, "Competitor found. Do you want to view all Results/Scores?", "Competitor Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    // User chose to view full details
                    viewCompetitorScores(competitor);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor found with ID: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
        // User cancelled the operation if competitorID is null
    }

    private void viewCompetitorScores(Competitor competitor) {
        String details = competitorList.getCompetitorScores(competitor);
        JOptionPane.showMessageDialog(this, details, "Competitor Scores", JOptionPane.INFORMATION_MESSAGE);
    }
}
