package Button;

import java.awt.Point;

import javax.swing.ImageIcon;

import UI.canva;
import Shape.Line;
import Shape.Lassociation;
import Shape.Obj;

public class association extends Button{
    private Line l;
    public association(ImageIcon icon,canva canva)
    {
        super("association",icon,canva);
    }
    @Override
    public void Pressed(int x,int y)
    {
        canva.addport(x,y,true);
        canva.currentLine = new Lassociation();
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
    public void Released(int startx,int starty,int endx,int endy,Obj firstObj,Obj secObj,int startport, int endport)
    {
        Line line = new Lassociation(startx,starty,endx,endy,firstObj,secObj,startport,endport);
        canva.currentLine=line;
    }
}
