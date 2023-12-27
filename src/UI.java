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

        // Main panel with a button
        JPanel mainPanel = new JPanel();
        JButton button = new JButton("Show White Page");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "WhitePage");
            }
        });
        mainPanel.add(button);

        // White page panel
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);

        // Add panels to cardPanel
        cardPanel.add(mainPanel, "MainPage");
        cardPanel.add(whitePanel, "WhitePage");

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
