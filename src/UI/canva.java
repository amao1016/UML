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

import javax.swing.JPanel;
import Button.Button;
import Shape.Obj;
import Shape.Line;
public class canva extends JPanel{
    public int selectAreaStartX=-1,selectAreaStartY=-1,selectAreaEndX=-1,selectAreaEndY=-1;
    public int pressX,pressY;
    public int releaseX,releaseY;
    public Line currentLine;
    public ArrayList<Obj> selectedObjs = new ArrayList<>();
    public static int selectedObjsNum;
    public static Obj lastObj;
    private Obj connectObj;
    private UI ui;
    public ArrayList<Point> ports=new ArrayList<>();
    private ArrayList<Line> Lines = new ArrayList<>();
    Button selectedButton;
    public ArrayList<Obj> objs = new ArrayList<>();
    boolean mouseE=false;
    public canva(UI ui)
    {
        setLayout(null);
        this.ui=ui;
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
            if(lastObj!=null&&selectedButton!=null)
            {
                if(selectedButton.getName().equals("select"))
                {
                    //System.out.print("hi");
                    lastObj.move(e.getX(), e.getY());
                    lastObj.repaint();
                }
                else{
                    Point p = lastObj.findport(pressX,pressY);
                    pressX = (int)p.getX();
                    pressY = (int)p.getY();
                    selectedButton.Dragged(pressX,pressY,e.getX(),e.getY());
                    repaint();
                }
            }
                //if(currentLine!=null) repaint();
            //repaint();
        }
        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Released");
            if(selectedButton!=null)
            {
                if(lastObj!=null) 
                {
                    if(selectedButton.getName().equals("select"))//Obj move
                    {
                        lastObj.move(e.getX(), e.getY());
                        lastObj.repaint();
                        lastObj=null;
                    }
                    else
                    {
                        //ports.clear();
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
        if(connectObj.equals(lastObj)&&!selectedButton.getName().equals("select"))connectObj=null;
    }
    public void addport()
    {
        selectedObjsNum=0;

        for(Obj obj:objs)
        {
            if(obj.ifselected(selectAreaStartX,selectAreaStartY,selectAreaEndX,selectAreaEndY))
            {
                System.out.println("yes");
                for(Point connect:obj.connectports)
                ports.add(connect);
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
    }
}
