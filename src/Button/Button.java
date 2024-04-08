package Button;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import UI.canva;
public class Button extends JButton{
    private String name;
    protected canva canva;
    public Button(String name, ImageIcon icon,canva canva)
    {
        super(icon);
        this.name = name;
        this.canva=canva;
    }
    public void Pressed(int x,int y){}
    public void Clicked(int x,int y){}
    public void Dragged(int x, int y){}
    public void Released(int x,int y){}

    public String getName()
    {
        return name;
    }

}
