package Shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class Line extends JPanel{
    public Point start;
    public Point end;
    private String typename;
    public Obj firstObj,secObj;
    public int startport,endport;
    public Line(String name)
    {
        this.typename=name;
        setPreferredSize(new Dimension(5,5));
        setOpaque(false);
    }
    public String getName()
    {
        return this.typename;
    }

    public void setStart(Point start)
    {
        this.start = start;
    }
    public void setEnd(Point end)
    {
        this.end = end;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        // g.setColor(Color.BLACK);
        // if(currentLine!=null) g.drawLine(pressX, pressY, releaseX, releaseY);
        // int startx,starty,endx,endy;
        // for(Line line:Lines)
        // {
        //     startx = (int)line.firstObj.connectports[line.startport].getX();
        //     starty = (int)line.firstObj.connectports[line.startport].getY();
        //     endx = (int)line.secObj.connectports[line.endport].getX();
        //     endy = (int)line.secObj.connectports[line.endport].getY();
        //     g.drawLine(startx, starty,endx, endy);
        // }
        // drawarrow(g);
    }
    public void drawarrow(Graphics g, double angle, int endx, int endy)
    {

    }
}
