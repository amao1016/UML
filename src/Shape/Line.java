package Shape;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

public class Line extends JComponent{
    private Point start;
    private Point end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end =end;
    }
    protected void paintComponent(Graphics g)//一樣是Jcomponent的不需要呼叫
    {
        g.drawLine(start.x,start.y,end.x,end.y);
    }
    public void setStart(Point start)
    {
        this.start = start;
    }
    public void setEnd(Point end)
    {
        this.end = end;
    }

}
