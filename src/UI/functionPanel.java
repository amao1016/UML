package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Button.Mode;
import Button.ButtonFactory;
import Button.ButtonType;
import UI.canva;
public class functionPanel extends JPanel{
        private UI ui;
        private Mode[] buttons = new Mode[6];
        private Mode lastSelectedButton;
        private canva canva;

        public functionPanel(UI ui,canva canva)
        {
            this.ui=ui;
            this.canva=canva;
            setLayout(new GridLayout(0, 1));
            setPreferredSize(new Dimension(96, getHeight()));
            
            ImageIcon icon;
            Image image;
            String[] iconPaths = {"select.png", "AssociationLineIcon.png", "GenerationLineIcon.png",
            "CompositionLineIcon.png", "ClassIcon.png", "UseCaseIcon.png"};
            ButtonType[] btn_name = {ButtonType.select, ButtonType.association, ButtonType.generation, ButtonType.composition,
                                    ButtonType.Class, ButtonType.useClass};
            for (int i = 0; i < buttons.length; i++) 
            {
                icon = new ImageIcon(iconPaths[i]);
                image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
                buttons[i] = ButtonFactory.createButton(btn_name[i], icon,canva);
                buttons[i].addActionListener(new buttonListener());
                buttons[i].setFocusPainted(false); 
                buttons[i].setBackground(new Color(105, 105, 105));
                add(buttons[i]);
            }

        }
        class buttonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                canva.ports.clear();
                //canva.repaint();
                Mode selectedButton = (Mode) e.getSource();
                if(lastSelectedButton != null) {
                    lastSelectedButton.setBackground(new Color(105, 105, 105));
                }
                selectedButton.setBackground(Color.BLACK);
                lastSelectedButton = selectedButton;
                canva.setSelectedbtn(selectedButton);
            }
        }

        
}
