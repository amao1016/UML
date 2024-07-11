package Button;
import java.awt.Point;

import javax.swing.ImageIcon;
import UI.canva;
import Shape.Lcomposition;
import Shape.Line;
import Shape.Obj;
public class composition extends Mode{
    public composition(ImageIcon icon,canva canva)
    {
        super(ButtonType.composition,icon,canva);
    }
    @Override
    public void Pressed(int x,int y)
    {
        canva.addport(x,y,true);
        canva.currentLine = new Lcomposition();
        canva.currentLine.setStart(new Point(x,y));
        canva.pressX=x;
        canva.pressY=y;
    }
    @Override
    public void Dragged(int startx,int starty,int endx,int endy)
    {
        canva.releaseX=endx;
        canva.releaseY=endy;
    }
    @Override
    public void Released(int startx,int starty,int endx,int endy, Obj firObj, Obj secObj,int startport,int endport)
    {
        Line line = new Lcomposition(startx,starty,endx,endy,firObj,secObj,startport,endport);
        canva.currentLine=line;
    }
}
