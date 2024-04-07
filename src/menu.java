import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JMenuBar {
    public menu()
    {
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
            String newName = JOptionPane.showInputDialog(menu.this,"Enter new name:");
            if (newName != null && !newName.isEmpty()) {

                System.out.println("New Name: " + newName);
            }
            System.out.println("New Name: " + newName);
        }
    }
}
