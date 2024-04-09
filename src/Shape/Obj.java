package Shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Obj extends JPanel{
    Point clickPoint;
    public Point[] pos = new Point[4]; //順序：象限2143
    public Point[] connectports = new Point[4];//上右下左
    boolean selected;
    String name;
    private int x,y;
    public int componentNum;

    public Obj(String name,int x,int y, int w, int h)
    {
        super();
        this.name = name;
        this.x = x;
        this.y = y;

        setPreferredSize(new Dimension(w,h));
        setOpaque(false);
        setBounds(x,y,w,h);
    }
    Point midPoint(Point a,Point b)
    {
        return new Point((a.x+b.x)/2,(a.y+b.y)/2);
    }
    //到時候的判斷式：if(ifselected() or inside) showports;
    public boolean insideObj(int x,int y)
    {
        return isinside(x, y, pos[0].x, pos[0].y, pos[2].x, pos[2].y);
    }
    public boolean ifselected(int startx, int starty, int endx, int endy)//被圈起來的時候，反向的insideObj
    {
        if(startx>endx)startx=swap(endx,endx=startx);
        if(starty>endy)starty=swap(endy,endy=starty);
        if ((isinside(pos[0].x, pos[0].y, startx, starty, endx, endy)) 
        &&(isinside(pos[2].x, pos[2].y, startx, starty, endx, endy))) 
        {
            return true;
        }
        return false;
    }
    int swap(int a,int b)
    {
        return a;
    }
    public void drawports(Graphics g)//not used now 
    {
        g.setColor(Color.BLACK);
        for(int i=0; i<4; i++)
            g.fillRect((int)connectports[i].getX(), (int)connectports[i].getY(), 3, 3);
    }
    public int findport(int x,int y)//選上下左右
    {
        boolean firstDiagonal = (x - pos[0].x) * (pos[2].y - pos[0].y) - (y - pos[0].y) * (pos[2].x - pos[0].x) > 0;//左到右的對角線，>0 在右側
        boolean secDiagonal = (x - pos[1].x) * (pos[3].y - pos[1].y) - (y - pos[1].y) * (pos[3].x - pos[1].x) > 0;//右到左的對角線

        if (firstDiagonal && secDiagonal) {
            return 1; //右
        } else if (!firstDiagonal && secDiagonal) {
            return 2; //下
        } else if (!firstDiagonal && !secDiagonal) {
            return 3; //左
        } else {
            return 0; //上
        }
    }
    void updatePos(int x,int y)
    {
        this.clickPoint = new Point(x,y);
    }
    boolean isinside(int checkx, int checky, int range_startx, int range_starty,int range_endx, int range_endy) //if check in range
    {
        if(range_startx<checkx && checkx<range_endx && range_starty<checky && checky<range_endy)
        {
            return true;
        }
        return false;

    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
        setBounds(x,y,getWidth(),getHeight());
        pos[0]= new Point(x,y);
        pos[1] = new Point(x+getWidth(),y);
        pos[2] = new Point(x+getWidth(),y+getHeight());
        pos[3]= new Point(x,y+getHeight());
        connectports[0]=midPoint(pos[0], pos[1]);
        connectports[1]=midPoint(pos[1], pos[2]);
        connectports[2]=midPoint(pos[2], pos[3]);
        connectports[3]=midPoint(pos[3], pos[0]);
    }

}
