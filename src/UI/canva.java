package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;
import Button.Button;
import Shape.Obj;
import Shape.group;
import Shape.Line;
public class canva extends JPanel{
    public int selectAreaStartX=-1,selectAreaStartY=-1,selectAreaEndX=-1,selectAreaEndY=-1;
    public int pressX,pressY;
    public int releaseX,releaseY;
    public Line currentLine;
    public static ArrayList<Obj> selectedObjs = new ArrayList<>();
    public static int selectedObjsNum;
    public static Obj lastObj;
    private Obj connectObj;
    private UI ui;
    public ArrayList<Point> ports=new ArrayList<>();
    public ArrayList<ArrayList<Obj>> groupList=new ArrayList<>();
    private ArrayList<Line> Lines = new ArrayList<>();
    Button selectedButton;
    public static ArrayList<Obj> objs = new ArrayList<Obj>();
    boolean mouseE=false;
    static int width, height;
    public static boolean notmove;
    public canva(UI ui)
    {
        setLayout(null);
        this.ui=ui;
        this.width=getWidth();
        this.height=getHeight();
        canvaListener listener=new canvaListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);

    }
    class canvaListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            ports.clear();
            selectedObjs.clear();
            System.out.println("Clicked");
            if(selectedButton!=null)selectedButton.Clicked(e.getX(),e.getY());
            repaint();

        }
        @Override
        public void mousePressed(MouseEvent e)
        {
            ports.clear();
            selectedObjs.clear();
            System.out.println("Pressed");
            if(selectedButton!=null)selectedButton.Pressed(e.getX(),e.getY());
        }
        @Override
        public void mouseDragged(MouseEvent e)
        {
            //ports.clear();
            System.out.println("Dragged");
            if(selectedButton!=null)
            {
                    if(selectedButton.getName().equals("select"))
                    {
                        if(notmove) 
                        {
                            selectedButton.Dragged(selectAreaStartX,selectAreaStartY,e.getX(),e.getY());
                            repaint();
                        }
                        else
                        {
                                lastObj.move(e.getX(), e.getY());
                                lastObj.repaint();
                        }
                    }
                    else if(lastObj!=null)//line
                    {
                        Point p = lastObj.findport(pressX,pressY);
                        pressX = (int)p.getX();
                        pressY = (int)p.getY();
                        selectedButton.Dragged(pressX,pressY,e.getX(),e.getY());
                        repaint();
                    }
                
                
            }
                //if(currentLine!=null) repaint();
            
        }
        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Released");
            if(selectedButton!=null)
            {
                if(notmove&&selectedButton.getName().equals("select"))
                {
                    selectedButton.Released(selectAreaStartX,selectAreaStartY,e.getX(),e.getY());
                }
                else if(lastObj!=null) 
                {
                    if(selectedButton.getName().equals("select")&&selectedObjsNum==1)//Obj move
                    {
                        lastObj.move(e.getX(), e.getY());
                        lastObj.repaint();
                        lastObj=null;                     
                    }
                    else
                    {
                        System.out.println(lastObj.getName());
                        ports.clear();
                        addport(e.getX(),e.getY(),true);
                        if(connectObj!=null)
                        {
                            Point p = lastObj.findport(e.getX(),e.getY());
                            releaseX = (int)p.getX();
                            releaseY = (int)p.getY();
                            selectedButton.Released(pressX,pressY,releaseX,releaseY);
                            if(currentLine!=null)
                            {
                                Lines.add(currentLine);
                                currentLine=null;
                            }
                        }
                    }
                }
                repaint();
            }
            reset();

        }
    }
    public void reset()
    {
        pressX=-1;
        pressY=-1;
        releaseX=-1;
        releaseY=-1;
        notmove=false;
    }
    public void addport(int x,int y,boolean drawport)
    {
        selectedObjsNum=0;
        connectObj = lastObj;
        for(int i=objs.size()-1;i>=0;i--)
        {
            if(objs.get(i).insideObj(x,y))
            {
                if(drawport)
                {
                    for(Point connect:objs.get(i).connectports)
                        ports.add(connect);
                }
                //selectedObjs.add(obj);
                selectedObjsNum++;
                lastObj = objs.get(i);
                break;
            }
        }
        if(connectObj!=null)
        {
            if(connectObj.equals(lastObj)&&!selectedButton.getName().equals("select"))connectObj=null;
        }
        if(selectedObjsNum==0)notmove=true;
    }
    public void addport()
    {
        selectedObjsNum=0;
        for(Obj obj:objs)
        {
            if(obj.ifselected(selectAreaStartX,selectAreaStartY,selectAreaEndX,selectAreaEndY))
            {
                for(Point connect:obj.connectports)
                ports.add(connect);
                selectedObjs.add(obj);
                selectedObjsNum++;
            }
        }
        //System.out.println(selectedObjsNum);
        if(selectedObjsNum>=1)lastObj = selectedObjs.getLast();

    }
    protected void paintComponent(Graphics g)//Jcomponents的mothod, JPanel屬Jcomponents
    {
        super.paintComponent(g);
        drawports(g);
        drawLine(g);
        //drawarrow(g);

    }
    public void drawarrow(Graphics g)
    {
        for(Line line:Lines)
        {
            int m = (line.end.y-line.start.y)/(line.end.x-line.start.x);
            double angle = Math.atan(m);
            g.setColor(Color.BLACK);
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform rotation = AffineTransform.getRotateInstance(angle, line.start.x, line.start.x);
            g2d.setTransform(rotation);
    
            g2d.setColor(Color.BLACK);
            g2d.drawRect(line.end.x,line.end.y,5,5);
        }    
    
    }
    public void drawLine(Graphics g)
    {
        g.setColor(Color.BLACK);
        if(currentLine!=null)g.drawLine(pressX, pressY, releaseX, releaseY);
        for(Line line:Lines)
        {
            g.drawLine(line.start.x, line.start.y,line.end.x, line.end.y);
        }
    }
    public void drawports(Graphics g)
    {
        for(Point port:ports)
            g.fillRect((int)port.getX(), (int)port.getY(), 3, 3);
    }
    public void setSelectedbtn(Button btn)
    {
        selectedButton = btn;
        lastObj=null;
    }
    public static void group()
    {
        int minx=width+1,miny=height+1,maxx=0,maxy=0;
        for(Obj obj:selectedObjs)
        {
            if(obj.pos[0].getX()<minx) minx = (int)obj.pos[0].getX();
            if(obj.pos[0].getY()<miny) minx = (int)obj.pos[0].getY();
            if(obj.pos[2].getX()>maxx) maxx = (int)obj.pos[0].getX();
            if(obj.pos[2].getY()>maxy) maxy = (int)obj.pos[0].getY();
        }
        Obj group = new group(selectedObjs,minx,miny,maxx,maxy);
        objs.add(group);
    }
}
