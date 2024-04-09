package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
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
    public static ArrayList<Obj> objs = new ArrayList<>();
    ArrayList<Obj> connecList = new ArrayList<>();
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
            ports.clear();
            System.out.println("Dragged");
            if(selectedButton!=null)
            {
                if(selectedButton.getName().equals("select"))
                {
                    if(notmove) //group
                    {
                        selectedButton.Dragged(selectAreaStartX,selectAreaStartY,e.getX(),e.getY());
                        repaint();
                    }
                    else //move
                    {
                        lastObj.move(e.getX(), e.getY());
                        repaint();
                    }
                }
                else if(lastObj!=null)//draw line
                {
                    currentLine.startport = lastObj.findport(pressX,pressY);
                    pressX = (int)lastObj.connectports[currentLine.startport].getX();
                    pressY = (int)lastObj.connectports[currentLine.startport].getY();
                    selectedButton.Dragged(pressX,pressY,e.getX(),e.getY());
                    repaint();
                }
            }            
        }
        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Released");
            if(selectedButton!=null)
            {
                if(notmove&&selectedButton.getName().equals("select"))
                {
                    ports.clear();
                    selectedObjs.clear();
                    selectedButton.Released(selectAreaStartX,selectAreaStartY,e.getX(),e.getY(),connectObj,lastObj,0,0);
                }
                else if(lastObj!=null) 
                {
                    if(selectedButton.getName().equals("select")&&selectedObjsNum==1)//Obj move
                    {
                        lastObj.move(e.getX(), e.getY());
                        lastObj=null;                     
                    }
                    else//draw line
                    {
                        ports.clear();
                        addport(e.getX(),e.getY(),true);
                        if(connectObj!=null)//上一個不為null
                        {
                            currentLine.endport = lastObj.findport(e.getX(),e.getY());
                            releaseX = (int)lastObj.connectports[currentLine.endport].getX();
                            releaseY = (int)lastObj.connectports[currentLine.endport].getY();
                            selectedButton.Released(pressX,pressY,releaseX,releaseY,connectObj,lastObj,currentLine.startport, currentLine.endport);
                            if(currentLine!=null)
                            {
                                Lines.add(currentLine);
                                if(!connecList.contains(currentLine.firstObj)) connecList.add(currentLine.firstObj);
                                if(!connecList.contains(currentLine.secObj)) connecList.add(currentLine.secObj);
                                currentLine=null;
                                connectObj=null;
                                lastObj=null;
                            }
                        }
                        //selectedObjs.clear();
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
        currentLine=null;
        connectObj=null;
        lastObj=null;
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
            if(connectObj.equals(lastObj)&&!selectedButton.getName().equals("select"))
                connectObj=null;
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
        //for(Obj obj:objs)obj.repaint();
        drawports(g);
        drawLine(g);
        drawarrow(g);

    }
    public void drawarrow(Graphics g)
    {
        for(Line line:Lines)
        {
            int endy=(int)line.secObj.connectports[line.endport].getY();
            int endx = (int)line.secObj.connectports[line.endport].getX();
            int starty=(int)line.firstObj.connectports[line.startport].getY();
            int startx = (int)line.firstObj.connectports[line.startport].getX();
            //int m = (endy-starty)/(endx-startx);
            double angle = Math.atan2(endy-starty,endx-startx);
            Polygon arrow;
            if(line.getName().equals("Lgeneration"))
            {
                angle-=Math.toRadians(20);
                arrow = new Polygon();
                arrow.addPoint(0, 5);
                arrow.addPoint(10, 0);
                arrow.addPoint(0, -5);
            }
            else if(line.getName().equals("Lcomposition"))
            {
                angle-=Math.toRadians(45);
                arrow = new Polygon();
                arrow.addPoint(0, 0);
                arrow.addPoint(10, 0);
                arrow.addPoint(10, 10);
                arrow.addPoint(0, 10);
            }
            else continue;
            AffineTransform rotation = AffineTransform.getRotateInstance(angle,0, 0);
            Polygon rotatedTriangle = new Polygon();
            for (int i = 0; i < arrow.npoints; i++) {
                Point p = new Point(arrow.xpoints[i], arrow.ypoints[i]);
                rotation.transform(p, p);
                rotatedTriangle.addPoint(p.x+endx, p.y+endy);
            }
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(rotatedTriangle);
        }    
    
    }
    public void drawLine(Graphics g)
    {
        g.setColor(Color.BLACK);
        if(currentLine!=null) g.drawLine(pressX, pressY, releaseX, releaseY);
        int startx,starty,endx,endy;
        for(Line line:Lines)
        {
            startx = (int)line.firstObj.connectports[line.startport].getX();
            starty = (int)line.firstObj.connectports[line.startport].getY();
            endx = (int)line.secObj.connectports[line.endport].getX();
            endy = (int)line.secObj.connectports[line.endport].getY();
            g.drawLine(startx, starty,endx, endy);
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
    public void group()
    {
        int minx,miny,maxx=0,maxy=0;
        minx = (int)selectedObjs.get(0).pos[0].getX();
        miny = (int)selectedObjs.get(0).pos[0].getY();
        System.out.println(selectedObjs.size());
        for(Obj obj:selectedObjs)
        {
            for(Point point:obj.pos)
            {
                if(point.getX()<minx) minx = (int)point.getX();
                if(point.getY()<miny) miny = (int)point.getY();
                if(point.getX()>maxx) maxx = (int)point.getX();
                if(point.getY()>maxy) maxy = (int)point.getY();
            }
        }
        Obj group = new group(selectedObjs,minx,miny,maxx,maxy);
        for(Obj obj:selectedObjs)
        {
            objs.remove(obj);
            remove(obj);
        }
        group.repaint();
        objs.add(group);
        add(group);
        revalidate();
        repaint();
    }
    public void ungroup()
    {
        objs.remove(lastObj);
        remove(lastObj);
        for(Obj component:lastObj.member)
        {
            component.reset(-(int)lastObj.pos[0].getX(),-(int)lastObj.pos[0].getY());
            add(component);
            objs.add(component);
            lastObj.member.remove(component);
        }
        revalidate();
        repaint();
    }
}
