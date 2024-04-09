package Shape;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import UI.canva;

public class ObjuseClass extends Obj{
    private int x,y;
    public ObjuseClass(canva canva,int x, int y)
    {
        super("useClass",x,y,120,90);
        super.pos[0]= new Point(x,y);
        super.pos[1] = new Point(x+120,y);
        super.pos[2] = new Point(x+120,y+90);
        super.pos[3]= new Point(x,y+90);
        super.componentNum=1;
        super.connectports[0]=midPoint(pos[0], pos[1]);
        super.connectports[1]=midPoint(pos[1], pos[2]);
        super.connectports[2]=midPoint(pos[2], pos[3]);
        super.connectports[3]=midPoint(pos[3], pos[0]);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        //super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, getWidth(), getHeight());
        //g.drawString(super.name, x+37, y+50);

        //string centered
        FontMetrics metrics = g.getFontMetrics();
        int posx = x + (getWidth() - metrics.stringWidth(super.name)) / 2;
        int posy = y + ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(super.name, posx, posy);
    }

    
}
