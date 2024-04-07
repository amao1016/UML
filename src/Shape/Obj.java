package Shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JButton;

public class Obj extends JButton{
    Point clickPoint;
    Point[] pos = new Point[4]; //順序：象限2143
    Point[] connectports = new Point[4];//上右下左
    boolean selected;
    public Obj(int x,int y, int w, int h)
    {
        super.setPreferredSize(new Dimension(w,h));
        setPreferredSize(new Dimension(w,h));
    }
    //到時候的判斷式：if(ifselected() or inside) showports;
    public boolean insideObj(int x,int y)
    {
        return isinside(x, y, pos[0].x, pos[0].y, pos[2].x, pos[2].y);
    }
    public boolean ifselected(int startx, int starty, int endx, int endy)//被圈起來的時候，反向的insideObj
    {
        if ((isinside(pos[0].x, pos[0].y, startx, starty, endx, endy)) 
        &&(isinside(pos[2].x, pos[2].y, startx, starty, endx, endy))) 
        {
            return true;
        }
        return false;
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        //draw(g);
    }
    
    public Point findport(int x,int y)//選上下左右
    {
        boolean firstDiagonal = (x - pos[0].x) * (pos[2].y - pos[0].y) - (y - pos[0].y) * (pos[2].x - pos[0].x) > 0;//左到右的對角線，>0 在右側
        boolean secDiagonal = (x - pos[1].x) * (pos[3].y - pos[1].y) - (y - pos[1].y) * (pos[3].x - pos[1].x) > 0;//右到左的對角線

        if (firstDiagonal && secDiagonal) {
            return connectports[1]; //右
        } else if (!firstDiagonal && secDiagonal) {
            return connectports[2]; //下
        } else if (!firstDiagonal && !secDiagonal) {
            return connectports[3]; //左
        } else {
            return connectports[0]; //上
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

}
