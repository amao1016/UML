package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Lgeneration extends Line{
    public Lgeneration(int startx,int starty,int endx,int endy, Obj firObj,Obj secObj,int startport,int endport)
    {
        super("Lgeneration");
        super.setBounds(endx,endy,5,5);

        super.setStart(new Point(startx,starty));
        super.setEnd(new Point(endx,endy));
        super.firstObj = firObj;
        super.secObj = secObj;
        super.startport = startport;
        super.endport = endport;
    }
    public Lgeneration()
    {
        super("Lgeneration");
    }
    @Override
    public void drawarrow(Graphics g,double angle, int endx, int endy)
    {
        angle-=Math.toRadians(20);
        Polygon arrow;
        arrow = new Polygon();
        arrow.addPoint(0, 5);
        arrow.addPoint(10, 0);
        arrow.addPoint(0, -5);
        AffineTransform rotation = AffineTransform.getRotateInstance(angle,0, 0);
        Polygon rotatedTriangle = new Polygon();
        for (int i = 0; i < arrow.npoints; i++) {
            Point p = new Point(arrow.xpoints[i], arrow.ypoints[i]);
            rotation.transform(p, p);
            rotatedTriangle.addPoint(p.x+endx, p.y+endy);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(rotatedTriangle);

    }
}
