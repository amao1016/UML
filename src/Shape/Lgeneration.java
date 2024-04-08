package Shape;

import java.awt.Point;

public class Lgeneration extends Line{
    public Lgeneration(int startx,int starty,int endx,int endy)
    {
        super("Lgeneration");
        super.setBounds(endx,endy,5,5);

        super.setStart(new Point(startx,starty));
        super.setEnd(new Point(endx,endy));

    }
    public Lgeneration()
    {
        super("Lgeneration");
    }
}
