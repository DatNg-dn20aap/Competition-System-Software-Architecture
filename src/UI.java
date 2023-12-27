import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class UI extends JFrame {
    private CardLayout cardLayout; // Declare CardLayout
    private JPanel cardPanel;

    public UI() {
        setTitle("My First JFrame");
        setSize(720, 540); // Width, Height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Main panel with a button to show the white page
        JPanel mainPanel = new JPanel();
        JButton toWhitePageButton = new JButton("Show White Page");
        toWhitePageButton.addActionListener(e -> cardLayout.show(cardPanel, "WhitePage"));
        mainPanel.add(toWhitePageButton);

        // White page panel with a button to show the red page
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        JButton toRedPageButton = new JButton("Show Red Page");
        toRedPageButton.addActionListener(e -> cardLayout.show(cardPanel, "RedPage"));
        whitePanel.add(toRedPageButton);

        // Red page panel
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);

        // Add panels to cardPanel
        cardPanel.add(mainPanel, "MainPage");
        cardPanel.add(whitePanel, "WhitePage");
        cardPanel.add(redPanel, "RedPage");

        // Add cardPanel to the frame
        add(cardPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UI frametest = new UI();
                frametest.setVisible(true);
            }
        });
    }

}
