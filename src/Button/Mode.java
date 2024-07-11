package Button;

import javax.swing.JButton;

import Shape.Obj;

import javax.swing.ImageIcon;

import UI.canva;
public class Mode extends JButton{
    private ButtonType name;
    protected canva canva;
    public Mode(ButtonType name, ImageIcon icon,canva canva)
    {
        super(icon);
        this.name = name;
        this.canva=canva;
    }
    public void Pressed(int x,int y){}
    public void Clicked(int x,int y){}
    public void Dragged(int startx,int starty,int endx,int endy){}
    public void Released(int startx,int starty,int endx,int endy, Obj firObj, Obj secObj,int startport,int endport){}

    public ButtonType getType()
    {
        return name;
    }
}
