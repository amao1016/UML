package UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import Button.Button;
import Shape.Obj;
public class canva extends JPanel implements MouseListener{
    private UI ui;
    Button selectedButton;
    ArrayList<Obj> objs = new ArrayList<>();
    public canva(UI ui)
    {
        this.ui=ui;
        addMouseListener(this);
    }

    protected void paintComponent(Graphics g)//Jcomponents的mothod, JPanel屬Jcomponents
    {
        super.paintComponent(g);

    }
    public void setSelectedbtn(Button btn)
    {
        selectedButton = btn;
    }
    @Override
    public void mouseClicked(MouseEvent e)//按下
    {
        selectedButton.mouseClicked(e.getX(), e.getY());
        System.out.println(selectedButton.getName());
        System.out.println(e.getX());
        System.out.println(e.getY());
        //selectedButton(e.getX(),e.getY());
    }
    @Override
    public void mousePressed(MouseEvent e)//拖曳
    {

    }
    @Override
    public void mouseReleased(MouseEvent e)//放開
    {

    }
    @Override
    public void mouseEntered (MouseEvent e){}//not used
    @Override
    public void mouseExited(MouseEvent e){}//not used
}
