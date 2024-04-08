package Shape;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class group extends Obj{
    private ArrayList<Obj> member = new ArrayList<>();

    public group(ArrayList<Obj> member, int minX, int minY, int maxX, int maxY)
    {
        super("Group",minX,minY,maxX-minX,maxY-minY);
        this.member=member;
        super.pos[0]= new Point(minX,minY);
        super.pos[1] = new Point(maxX,minY);
        super.pos[2] = new Point(maxX,maxY);
        super.pos[3]= new Point(minX,maxY);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.BLACK);
        for(Obj obj:member)
        {
            obj.paint(g);
        }
    }
    
}
