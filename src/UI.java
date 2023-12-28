import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class UI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField staffIDField;
    private StaffList staffList; // Assuming staffList is initialized elsewhere

    public UI(StaffList staffList) {
        this.staffList = staffList;

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

        return panel;
    }

}
