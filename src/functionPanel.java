import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class functionPanel extends JPanel{
        private JButton[] buttons;
        private JButton lastSelectedButton;

        public functionPanel()
        {
            setLayout(new GridLayout(0, 1));
            setPreferredSize(new Dimension(96, getHeight()));
            
            buttons = new JButton[6];
            ImageIcon icon;
            Image image;
            String[] iconPaths = {"select.png", "AssociationLineIcon.png", "GenerationLineIcon.png",
            "CompositionLineIcon.png", "ClassIcon.png", "UseCaseIcon.png"};
            for (int i = 0; i < buttons.length; i++) 
            {
                icon = new ImageIcon(iconPaths[i]);
                image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
                buttons[i] = new JButton(icon);
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton selectedButton = (JButton) e.getSource();
                        if(lastSelectedButton != null) {
                            lastSelectedButton.setBackground(new Color(105, 105, 105));
                        }
                        selectedButton.setBackground(Color.BLACK);
                        lastSelectedButton = selectedButton;
                        System.out.println("hi");
                    }
                });
                buttons[i].setFocusPainted(false); 
                buttons[i].setBackground(new Color(105, 105, 105));
                add(buttons[i]);
            }
        }

}
