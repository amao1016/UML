package UI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

import javax.swing.JPanel;
import Button.Button;
import Shape.Obj;
public class canva extends JPanel{
    private UI ui;
    public ArrayList<Point> ports=new ArrayList<>();
    Button selectedButton;
    public ArrayList<Obj> objs = new ArrayList<>();
    boolean mouseE=false;
    public canva(UI ui)
    {
        setLayout(null);
        this.ui=ui;
        addMouseListener(new canvaListener());

    }
    class canvaListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            ports.clear();
            System.out.println("Clicked");
            selectedButton.Clicked(e.getX(),e.getY());
            repaint();

        }
        @Override
        public void mousePressed(MouseEvent e)
        {
            System.out.println("Pressed");
            selectedButton.Pressed(e.getX(),e.getY());
        }
        @Override
        public void mouseDragged(MouseEvent e)
        {
            System.out.println("Dragged");
            selectedButton.Dragged(e.getX(),e.getY());

        }
        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Released");
            selectedButton.Released(e.getX(),e.getY());

        }

    }
    
    protected void paintComponent(Graphics g)//Jcomponents的mothod, JPanel屬Jcomponents
    {
        super.paintComponent(g);
        drawports(g);

    }
    public void addport(int x,int y)
    {
        for(Obj obj:objs)
        {
            if(obj.insideObj(x,y))
            {
                System.out.println("yes");
                for(Point connect:obj.connectports)
                ports.add(connect);
            }
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
