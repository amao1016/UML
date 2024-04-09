package Shape;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import UI.canva;

public class ObjClass extends Obj{
    private int x,y;
    public ObjClass(canva canva,int x, int y)
    {
        super("class",x,y,120,180);
        super.pos[0]= new Point(x,y);
        super.pos[1] = new Point(x+120,y);
        super.pos[2] = new Point(x+120,y+180);
        super.pos[3]= new Point(x,y+180);
        super.componentNum=1;
        super.connectports[0]=midPoint(pos[0], pos[1]);
        super.connectports[1]=midPoint(pos[1], pos[2]);
        super.connectports[2]=midPoint(pos[2], pos[3]);
        super.connectports[3]=midPoint(pos[3], pos[0]);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        //width-1.height-1是為了在範圍內劃出
        g.drawRect(x, y, getWidth()-1, getHeight()/3);
        g.drawRect(x, y+getHeight()/3, getWidth()-1, getHeight()/3);
        g.drawRect(x, y+getHeight()/3*2, getWidth()-1, getHeight()/3-1);
        //g.drawString(super.name, x+45, y+35);
        FontMetrics metrics = g.getFontMetrics();
        int posx = x + (getWidth() - metrics.stringWidth(super.name)) / 2;
        g.drawString(super.name, posx, y+35);
    }

    
}
