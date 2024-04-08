package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.canva;

public class menu extends JMenuBar {
    private UI ui;
    public menu(UI ui)
    {
        this.ui=ui;
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem groupMenuItem = new JMenuItem("Group");
        JMenuItem ungroupMenuItem = new JMenuItem("UnGroup");
        JMenuItem renameMenuItem = new JMenuItem("change object name");
        groupMenuItem.addActionListener(new groupListener());
        ungroupMenuItem.addActionListener(new ungroupListener());
        renameMenuItem.addActionListener(new changeNameListenser());
        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);
        editMenu.add(renameMenuItem);
        add(fileMenu);
        add(editMenu);
    }
    class groupListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //group();
        }
    }
    class ungroupListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //ungroup();
        }
    }
    class changeNameListenser implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(canva.selectedObjsNum==1)
            {
                String newName = JOptionPane.showInputDialog("Enter new name:");
                if (newName != null && !newName.isEmpty()) 
                {
                    canva.lastObj.setName(newName);
                    canva.lastObj.repaint();
                }                
            }
        }
    }
}
