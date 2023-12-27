import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
public class UI extends JFrame {

    public UI() {
        setTitle("My First JFrame");
        setSize(720, 540); // Width, Height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a button to the frame
        JButton button = new JButton("Click Me");
        add(button); // This adds the button to the frame
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
