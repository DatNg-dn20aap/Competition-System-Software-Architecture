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
        setSize(360, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        createMainPanel();
        createStaffTypePanels();

        add(cardPanel);
    }

    private void createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); // Set to BorderLayout for better arrangement

        // Create and add title label
        JLabel titleLabel = new JLabel("Main Screen", JLabel.CENTER); // Center-aligned title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font for title (optional)
        mainPanel.add(titleLabel, BorderLayout.NORTH); // Add the title label at the top

        // Panel for ID input
        JPanel idInputPanel = new JPanel();
        JLabel idInputLabel = new JLabel("Enter Your ID here:");
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

        // Add components to the ID input panel
        idInputPanel.add(idInputLabel);
        idInputPanel.add(staffIDField);
        idInputPanel.add(submitButton);

        // Add the ID input panel to the center of the main panel
        mainPanel.add(idInputPanel, BorderLayout.CENTER);

        // Finally, add the main panel to the cardPanel
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
                JPanel OfficialPanel = new JPanel(new GridLayout(5, 1)); // Grid layout for 4 buttons
                // Create buttons and add them to the EmergencyPanel
                addOfficialPanelButtons(OfficialPanel);
                panel.add(OfficialPanel, BorderLayout.CENTER);
                break;
            default:
                break;
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
        addScoresButton.addActionListener(e -> addingScoresForCompetitor());
        dataEntryPanel.add(addScoresButton);
    }

    private void addOfficialPanelButtons(JPanel officialPanel) {
        JButton searchCompetitorButton = new JButton("Search for Competitor Details");
        searchCompetitorButton.addActionListener(e -> searchForCompetitor());
        officialPanel.add(searchCompetitorButton);

        JButton viewResultsButton = new JButton("View Results/Scores of An Competitor");
        viewResultsButton.addActionListener(e -> searchCompetitorforScores());
        officialPanel.add(viewResultsButton);

        JButton registerCompetitorButton = new JButton("Register a new Competitor");
        registerCompetitorButton.addActionListener(e -> registerNewCompetitor());
        officialPanel.add(registerCompetitorButton);

        JButton removeCompetitorButton = new JButton("Remove an existing Competitor");
        removeCompetitorButton.addActionListener(e -> removeCompetitor());
        officialPanel.add(removeCompetitorButton);

        JButton alterCompetitorDetails = new JButton("Alter existing Competitor Details");
        alterCompetitorDetails.addActionListener(e -> alterCompetitorDetail());
        officialPanel.add(alterCompetitorDetails);
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
                // Check if competitor has scores
                if (!competitor.hasScores()) {
                    JOptionPane.showMessageDialog(this, "Competitor found but has no scores yet.", "No Scores", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int response = JOptionPane.showConfirmDialog(this, "Competitor found. Do you want to view full details?", "Competitor Found", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    viewCompetitorDetails(competitor);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor found with ID: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
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

    private void addingScoresForCompetitor(){
        // Prompt for competitor ID
        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor ID:", "Add Scores", JOptionPane.QUESTION_MESSAGE);
        if (competitorID != null && !competitorID.trim().isEmpty()) {
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());
            if (competitor != null) {
                // Prompt for scores
                String scoreInput = JOptionPane.showInputDialog(this, "Enter scores (comma separated):", "Add Scores", JOptionPane.QUESTION_MESSAGE);
                if (scoreInput != null && !scoreInput.trim().isEmpty()) {
                    String[] scoresStr = scoreInput.split(",");
                    try {
                        for (String scoreStr : scoresStr) {
                            int score = Integer.parseInt(scoreStr.trim());
                            competitor.addScore(score); // Assuming addScore is a method in Competitor class
                        }
                        JOptionPane.showMessageDialog(this, "Scores added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid score format. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor found with ID: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
        // User cancelled the operation if competitorID is null
    }

    private void registerNewCompetitor() {
        Object[] options = {"IceSkating", "JavelinThrow"};
        int type = JOptionPane.showOptionDialog(this, "Select Competitor Type:", "Register New Competitor", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (type == JOptionPane.CLOSED_OPTION) return;

        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor ID:", "Register New Competitor", JOptionPane.QUESTION_MESSAGE);
        if (competitorID == null || competitorID.trim().isEmpty()) return;

        String firstName = JOptionPane.showInputDialog(this, "Enter Competitor First Name:", "Register New Competitor", JOptionPane.QUESTION_MESSAGE);
        String middleName = JOptionPane.showInputDialog(this, "Enter Competitor Middle Name (optional):", "Register New Competitor", JOptionPane.QUESTION_MESSAGE);
        String lastName = JOptionPane.showInputDialog(this, "Enter Competitor Last Name:", "Register New Competitor", JOptionPane.QUESTION_MESSAGE);

        // Assuming Level is an enum with predefined values
        Level level = (Level) JOptionPane.showInputDialog(this, "Select Level:", "Register New Competitor", JOptionPane.QUESTION_MESSAGE, null, Level.values(), Level.values()[0]);

        // Collect age as integer
        int age;
        try {
            String ageInput = JOptionPane.showInputDialog(this, "Enter Competitor Age:", "Register New Competitor", JOptionPane.QUESTION_MESSAGE);
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Collect gender
        String gender = JOptionPane.showInputDialog(this, "Enter Competitor Gender (Male/Female):", "Register New Competitor", JOptionPane.QUESTION_MESSAGE);

        // Collect country
        String country = JOptionPane.showInputDialog(this, "Enter Competitor Country:", "Register New Competitor", JOptionPane.QUESTION_MESSAGE);

        if (firstName == null || lastName == null || gender == null || country == null) {
            JOptionPane.showMessageDialog(this, "Required information missing.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Name name = new Name(firstName.trim(), lastName.trim(), middleName != null ? middleName.trim() : "");

        Competitor newCompetitor;
        if (type == 0) {
            newCompetitor = new IceSkatingCompetitor(competitorID.trim(), name, level, age, gender, country);
        } else {
            newCompetitor = new JavelinThrowCompetitor(competitorID.trim(), name, level, age, gender, country);
        }

        competitorList.addCompetitor(newCompetitor);
        JOptionPane.showMessageDialog(this, "New competitor registered successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void removeCompetitor() {
        // Prompt the user to enter the competitor's ID to be removed
        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor ID to remove:", "Remove Competitor", JOptionPane.QUESTION_MESSAGE);

        if (competitorID != null && !competitorID.trim().isEmpty()) {
            // Check if the competitor exists
            Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());
            if (competitor != null) {
                // Remove the competitor
                competitorList.removeCompetitor(competitor);
                JOptionPane.showMessageDialog(this, "Competitor with ID: " + competitorID + " has been removed.", "Competitor Removed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Competitor not found
                JOptionPane.showMessageDialog(this, "No competitor found with ID: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } else if (competitorID != null) {
            // User pressed OK but didn't enter an ID
            JOptionPane.showMessageDialog(this, "Please enter a valid Competitor ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
        // If competitorID is null, the user canceled the operation
    }

    private void alterCompetitorDetail() {
        String competitorID = JOptionPane.showInputDialog(this, "Enter Competitor ID to alter:", "Alter Competitor Details", JOptionPane.QUESTION_MESSAGE);
        if (competitorID == null || competitorID.trim().isEmpty()) return;

        // Check if the competitor exists
        Competitor competitor = competitorList.getCompetitorByID(competitorID.trim());
        if (competitor == null) {
            JOptionPane.showMessageDialog(this, "No competitor found with ID: " + competitorID, "Competitor Not Found", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prompt for new details
        String firstName = JOptionPane.showInputDialog(this, "Enter new First Name:", competitor.getCompetitorName().getFirstName());
        String middleName = JOptionPane.showInputDialog(this, "Enter new Middle Name (optional):", competitor.getCompetitorName().getMiddleName());
        String lastName = JOptionPane.showInputDialog(this, "Enter new Last Name:", competitor.getCompetitorName().getLastName());
        String levelStr = JOptionPane.showInputDialog(this, "Enter new Level:", competitor.getCompetitorLevel().toString());
        String ageStr = JOptionPane.showInputDialog(this, "Enter new Age:", Integer.toString(competitor.getCompetitorAge()));
        String gender = JOptionPane.showInputDialog(this, "Enter new Gender:", competitor.getCompetitorGender());
        String country = JOptionPane.showInputDialog(this, "Enter new Country:", competitor.getCompetitorCountry());

        // Validate and parse inputs
        if (firstName == null || lastName == null || levelStr == null || ageStr == null || gender == null || country == null) {
            JOptionPane.showMessageDialog(this, "Missing information. No changes made.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Assuming Level is an enum with values like Level.STANDARD, Level.ADVANCED, etc.
        Level currentLevel = competitor.getCompetitorLevel();
        Level[] levels = Level.values();
        Level newLevel = (Level) JOptionPane.showInputDialog(this, "Select new Level:", "Alter Competitor Details", JOptionPane.QUESTION_MESSAGE, null, levels, currentLevel);

        if (newLevel == null) {
            JOptionPane.showMessageDialog(this, "Level selection is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newAge;
        try {
            newAge = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update competitor details
        Name newName = new Name(firstName.trim(), lastName.trim(), middleName != null ? middleName.trim() : "");
        competitorList.alterCompetitorDetails(competitorID.trim(), newName, newLevel, newAge, gender.trim(), country.trim());
        JOptionPane.showMessageDialog(this, "Competitor details updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
    }
}
