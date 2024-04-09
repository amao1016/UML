package Shape;

import java.awt.Point;

public class Lcomposition extends Line{
    public Lcomposition(int startx,int starty,int endx,int endy, Obj firObj,Obj secObj,int startport,int endport)
    {
        super("Lcomposition");
        super.setBounds(endx,endy,5,5);
        super.setStart(new Point(startx,starty));
        super.setEnd(new Point(endx,endy));
        super.firstObj = firObj;
        super.secObj = secObj;
        super.startport = startport;
        super.endport = endport;
    }
    public Lcomposition()
    {
        super("Lcomposition");
    }
}
