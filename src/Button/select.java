package Button;
import javax.swing.ImageIcon;
import UI.canva;
import Shape.Obj;
public class select extends Button{
    public select(ImageIcon icon,canva canva)
    {
        super("select",icon,canva);
    }
    @Override
    public void Clicked(int x,int y)
    {
        canva.addport(x,y,true);
    }
    @Override
    public void Pressed(int x,int y)//for group or move
    {
        canva.addport(x,y,false);
        canva.selectAreaStartX=x;
        canva.selectAreaStartY=y;
        canva.pressX=x;
        canva.pressY=y;
    }
    @Override
    public void Dragged(int startx,int starty,int endx,int endy)//for group
    {
        if(startx >= endx) startx = swap(endx,endx=startx);
        if(starty >= endy) starty = swap(endy,endy=starty);
        canva.selectAreaStartX=startx;
        canva.selectAreaStartY=starty;
        canva.selectAreaEndX=endx;
        canva.selectAreaEndY=endy;
        canva.addport();
    }
    @Override
    public void Released(int startx,int starty,int endx,int endy)//for group
    {
        if(startx >= endx) startx = swap(endx,endx=startx);
        if(starty >= endy) starty = swap(endy,endy=starty);
        canva.selectAreaStartX=startx;
        canva.selectAreaStartY=starty;
        canva.selectAreaEndX=endx;
        canva.selectAreaEndY=endy;
        canva.addport();

    }
    int swap(int a,int b)
    {
        return a;
    }
}
