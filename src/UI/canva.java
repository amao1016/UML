package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import Button.Button;
import Button.ButtonType;
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
            if(selectedButton==null)return;

            ports.clear();
            selectedObjs.clear();
            System.out.println("Clicked");
            selectedButton.Clicked(e.getX(),e.getY());
            repaint();
        }
        @Override
        public void mousePressed(MouseEvent e)
        {
            if(selectedButton==null)return;

            ports.clear();
            selectedObjs.clear();
            System.out.println("Pressed");
            
            selectedButton.Pressed(e.getX(),e.getY());
        }
        @Override
        public void mouseDragged(MouseEvent e)
        {
            ports.clear();
            System.out.println("Dragged");
            if(selectedButton==null)return;
            if(selectedButton.getType()==ButtonType.select)
            {
                if(notmove) //group
                {
                    selectedButton.Dragged(selectAreaStartX,selectAreaStartY,e.getX(),e.getY());
                    repaint();
                }
                else //move
                {
                    lastObj.move(e.getX()-pressX, e.getY()-pressY);
                    pressX=e.getX();
                    pressY=e.getY();
                    repaint();
                }
            }
            else if(lastObj!=null&&lastObj.componentNum==1)//draw line
            {
                currentLine.startport = lastObj.findport(pressX,pressY);
                pressX = (int)lastObj.connectports[currentLine.startport].getX();
                pressY = (int)lastObj.connectports[currentLine.startport].getY();
                selectedButton.Dragged(pressX,pressY,e.getX(),e.getY());
                repaint();
            }
                        
        }
        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Released");
            if(selectedButton==null)return;
            
            if(notmove&&selectedButton.getType()==ButtonType.select)
            {
                ports.clear();
                selectedObjs.clear();
                selectedButton.Released(selectAreaStartX,selectAreaStartY,e.getX(),e.getY(),connectObj,lastObj,0,0);
            }
            else if(lastObj!=null) 
            {
                if(selectedButton.getType()==ButtonType.select &&selectedObjsNum==1)//Obj move
                {
                    lastObj.move(e.getX()-pressX, e.getY()-pressY);
                    lastObj=null;                     
                }
                else 
                {
                    ports.clear();
                    addport(e.getX(),e.getY(),true);
                    if(connectObj!=null&& lastObj.componentNum==1)//上一個不為null
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
    public void addport(int x,int y,boolean drawport)//line to Obj,show port
    {
        selectedObjsNum=0;
        connectObj = lastObj;

        for(Obj obj:objs)
        {
            if(obj.insideObj(x,y))
            {
                if(drawport&&obj instanceof group==false)
                {
                    for(Point connect:obj.connectports)
                    ports.add(connect);
                }
                //selectedObjs.add(obj);
                selectedObjsNum++;
                lastObj = obj;
                break;
            }
        }
        if(connectObj!=null)
        {
            if((connectObj.equals(lastObj)&&selectedButton.getType()!=ButtonType.select)||connectObj instanceof group)
                connectObj=null;
            
        }
        if(selectedObjsNum==0)notmove=true;
    }
    public void addport()//select
    {
        selectedObjsNum=0;
        for(Obj obj:objs)
        {
            if(obj.ifselected(selectAreaStartX,selectAreaStartY,selectAreaEndX,selectAreaEndY))
            {
                if(obj instanceof group==false)
                {
                    for(Point connect:obj.connectports) ports.add(connect);
                }
                selectedObjs.add(obj);
                selectedObjsNum++;
            }
        }
        if(selectedObjsNum>=1)lastObj = selectedObjs.getLast();

    }
    protected void paintComponent(Graphics g)//Jcomponents的mothod, JPanel屬Jcomponents
    {
        super.paintComponent(g);
        drawports(g);
        drawLine(g);
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
            double angle = Math.atan2(endy-starty,endx-startx);
            g.drawLine(startx, starty,endx, endy);
            line.drawarrow(g,angle,endx,endy);
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
        ports.clear();
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
            //remove(obj);
        }
        group.repaint();
        objs.add(group);
        add(group);
        //revalidate();因為沒有刪掉所以不需要用這個
        repaint();
    }
    public void ungroup()
    {
        ports.clear();
        objs.remove(lastObj);
        remove(lastObj);
        for(Obj component:lastObj.member)
        {
            objs.add(component);
        }
        repaint();
    }
}
