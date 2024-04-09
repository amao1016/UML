package Shape;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.util.ArrayList;
public class group extends Obj{
    private ArrayList<Obj> member = new ArrayList<>();
    private int minx,miny;
    public group(ArrayList<Obj> member, int minX, int minY, int maxX, int maxY)
    {
        super("Group",minX,minY,maxX-minX+1,maxY-minY+1);
        this.member=member;
        super.pos[0]= new Point(minX,minY);
        super.pos[1] = new Point(maxX,minY);
        super.pos[2] = new Point(maxX,maxY);
        super.pos[3]= new Point(minX,maxY);
        super.componentNum=member.size();
        this.minx=minX;
        this.miny=minY;
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        for(Obj obj:member)
        {
            int newX = obj.pos[0].x - minx;
            int newY = obj.pos[0].y - miny;
    
            obj.setLocation(newX, newY);
            add(obj);
        }
    }
    
}
