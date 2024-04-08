package Button;
import java.awt.Point;

import javax.swing.ImageIcon;
import UI.canva;
import Shape.Lcomposition;
import Shape.Line;
public class composition extends Button{
    public composition(ImageIcon icon,canva canva)
    {
        super("composition",icon,canva);
    }
    @Override
    public void Pressed(int x,int y)
    {
        canva.currentLine = new Lcomposition();
        canva.currentLine.setStart(new Point(x,y));
        System.out.println(canva.currentLine.getName());
        canva.pressX=x;
        canva.pressY=y;
    }
    @Override
    public void Dragged(int startx,int starty,int endx,int endy)
    {
        canva.releaseX=endx;
        canva.releaseY=endy;
    }
    @Override
    public void Released(int startx,int starty,int endx,int endy)
    {
        Line line = new Lcomposition(startx,starty,endx,endy);
        canva.currentLine=line;
    }
}
