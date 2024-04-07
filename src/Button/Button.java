package Button;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.canva;
public class Button extends JButton{
    private String name;
    public Button(String name, ImageIcon icon)
    {
        super(icon);
        this.name = name;
    }
    public void mouseClicked(int x,int y){}
    public void mousePressed(int x,int y){}
    public void mousedReleased(int x,int y){}

    public String getName()
    {
        return name;
    }

}
