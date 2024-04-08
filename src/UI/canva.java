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
            selectedButton.Clicked(e.getX(),e.getY());
            repaint();

        }
        @Override
        public void mousePressed(MouseEvent e)
        {
            ports.clear();
            selectedObjs.clear();
            System.out.println("Pressed");
            selectedButton.Pressed(e.getX(),e.getY());
        }
        @Override
        public void mouseDragged(MouseEvent e)
        {
            System.out.println("Dragged");
            selectedButton.Dragged(pressX,pressY,e.getX(),e.getY());
            //if(currentLine!=null) repaint();
            repaint();
        }
        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Released");
            selectedButton.Released(pressX,pressY,e.getX(),e.getY());
            if(currentLine!=null)
            {
                Lines.add(currentLine);
                currentLine=null;
            }
            repaint();
            

        }

    }
    public void addport(int x,int y)
    {
        selectedObjsNum=0;
        for(Obj obj:objs)
        {
            if(obj.insideObj(x,y))
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
