import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener{
    private JButton lastSelectedButton;
    public UI() {
        setTitle("Swing Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);

        setJMenuBar(new menu());

        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel leftPanel = new functionPanel();      
        contentPane.add(leftPanel, BorderLayout.LINE_START); 

        JPanel rightPanel = new JPanel();
        contentPane.add(rightPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton) e.getSource();
        if(lastSelectedButton!=null){lastSelectedButton.setBackground(new Color(105,105,105));}
        selectedButton.setBackground(Color.BLACK);
        lastSelectedButton = selectedButton;
        System.out.println(selectedButton.getClass().getName());
        System.out.println(1);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UI main = new UI();
            main.setVisible(true);
        });
    }
}
