package Button;
import java.awt.Point;

import javax.sound.sampled.Line;
import javax.swing.ImageIcon;
import UI.canva;
import Shape.Line;

public class generation extends Button{
    public Line l;
    public generation(ImageIcon icon,canva canva)
    {
        super("generation",icon,canva);
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
