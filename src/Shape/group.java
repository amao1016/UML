package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
public class group extends Obj{
    private ArrayList<Obj> member = new ArrayList<>();
    private int minx,miny;
    public group(ArrayList<Obj> member, int minX, int minY, int maxX, int maxY)
    {
        super("Group",minX-2,minY-2,maxX-minX+4,maxY-minY+4);

        super.pos[0]= new Point(minX,minY);
        super.pos[1] = new Point(maxX,minY);
        super.pos[2] = new Point(maxX,maxY);
        super.pos[3]= new Point(minX,maxY);
        super.connectports[0]=midPoint(pos[0], pos[1]);
        super.connectports[1]=midPoint(pos[1], pos[2]);
        super.connectports[2]=midPoint(pos[2], pos[3]);
        super.connectports[3]=midPoint(pos[3], pos[0]);
        super.componentNum=member.size();
        this.minx=minX;
        this.miny=minY;
        findallmember(member);

    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
    }
    @Override
    public void move(int x, int y)
    {        
        this.x += x;
        this.y += y;
        setBounds(this.x,this.y,getWidth(),getHeight());
        pos[0]= new Point(this.x,this.y);
        pos[1] = new Point(this.x+getWidth(),this.y);
        pos[2] = new Point(this.x+getWidth(),this.y+getHeight());
        pos[3]= new Point(this.x,this.y+getHeight());
        connectports[0]=midPoint(pos[0], pos[1]);
        connectports[1]=midPoint(pos[1], pos[2]);
        connectports[2]=midPoint(pos[2], pos[3]);
        connectports[3]=midPoint(pos[3], pos[0]);
        for (Obj mem : member) {
            mem.move(x, y);
        }
    }
    private void findallmember(ArrayList<Obj> member) 
    {
        for (Obj obj : member) {
            if (obj instanceof group) 
            {
                
                findallmember(((group) obj).member);
            } 
            this.member.add(obj);
            super.member.add(obj);
        }
    }
}
