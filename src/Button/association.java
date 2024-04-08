package Button;

import java.awt.Point;

import javax.sound.sampled.Line;
import javax.swing.ImageIcon;

import UI.canva;

public class association extends Button{
    private Line l;
    public association(ImageIcon icon,canva canva)
    {
        super("association",icon,canva);
    }
    @Override
    public void Pressed(int x,int y)
    {
        l.setStart(new Point(x,y));
    }
    @Override
    public void Dragged(int x,int y)
    {

    }
    @Override
    public void Released(int x,int y)
    {

    }
}
