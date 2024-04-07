package UI;
import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private JButton lastSelectedButton;
    public UI() {
        setTitle("Swing Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);

        setJMenuBar(new menu(this));
        JPanel contentPane = new JPanel(new BorderLayout());
        canva rightPanel = new canva(this);
        contentPane.add(rightPanel, BorderLayout.CENTER);
        functionPanel leftPanel = new functionPanel(this,rightPanel);      
        contentPane.add(leftPanel, BorderLayout.LINE_START); 

        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UI main = new UI();
            main.setVisible(true);
        });
    }
}