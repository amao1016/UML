package Shape;

import java.awt.Point;

public class Lassociation extends Line{
    public Lassociation(int startx,int starty,int endx,int endy,Obj firObj,Obj secObj,int startport,int endport)
    {
        super("Lassociation");
        super.setBounds(endx,endy,5,5);
        super.setStart(new Point(startx,starty));
        super.setEnd(new Point(endx,endy));
        super.firstObj = firObj;
        super.secObj = secObj;
        super.startport = startport;
        super.endport = endport;

    }
    public Lassociation(int startx,int starty,int endx,int endy)
    {
        super("Lassociation");
        super.setBounds(endx,endy,5,5);
        super.setStart(new Point(startx,starty));
        super.setEnd(new Point(endx,endy));


    }
    public Lassociation()
    {
        super("Lassociation");
    }
}
