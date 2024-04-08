package Button;
import java.awt.Point;

import javax.swing.ImageIcon;
import UI.canva;
import Shape.Lcomposition;
import Shape.Line;
public class composition extends Button{
    private Line l;
    public composition(ImageIcon icon,canva canva)
    {
        super("composition",icon,canva);
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
