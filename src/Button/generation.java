package Button;
import java.awt.Point;

import javax.swing.ImageIcon;
import UI.canva;
import Shape.Line;
import Shape.Lgeneration;
import Shape.Obj;
public class generation extends Button{
    public generation(ImageIcon icon,canva canva)
    {
        super("generation",icon,canva);
    }
    @Override
    public void Pressed(int x,int y)
    {
        canva.addport(x,y,true);
        canva.currentLine = new Lgeneration();
        canva.currentLine.setStart(new Point(x,y));
        System.out.println(canva.currentLine.getName());
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
        Line line = new Lgeneration(startx,starty,endx,endy,firObj,secObj, startport, endport);
        canva.currentLine=line;
    }
}
