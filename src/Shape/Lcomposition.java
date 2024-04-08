package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Lcomposition extends Line{
    public Lcomposition(int startx,int starty,int endx,int endy)
    {
        super("Lcomposition");
        super.setBounds(endx,endy,5,5);

        super.setStart(new Point(startx,starty));
        super.setEnd(new Point(endx,endy));

    }
    public Lcomposition()
    {
        super("Lcomposition");
    }
}
