package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ObjuseClass extends Obj{
    private int x,y;
    public ObjuseClass(int x, int y)
    {
        super(x,y,120,90);
        this.x = x;
        this.y = y;
        super.pos[0]= new Point(x,y);
        super.pos[1] = new Point(x+120,y);
        super.pos[2] = new Point(x+120,y+90);
        super.pos[3]= new Point(x,y+90);
        super.connectports[0]=midPoint(pos[0], pos[1]);
        super.connectports[1]=midPoint(pos[1], pos[2]);
        super.connectports[2]=midPoint(pos[2], pos[3]);
        super.connectports[3]=midPoint(pos[3], pos[0]);
        setOpaque(false);

    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(x, y, getWidth(), getHeight()); // 绘制圆形按钮
        System.out.println("width:"+getWidth());

    }
    Point midPoint(Point a,Point b)
    {
        return new Point((a.x+b.x)/2,(a.y+b.y)/2);
    }
    
}
