package Button;
import javax.swing.ImageIcon;
import UI.canva;
import Shape.Obj;
public class select extends Mode{
    public select(ImageIcon icon,canva canva)
    {
        super(ButtonType.select,icon,canva);
    }
    @Override
    public void Clicked(int x,int y)
    {
        canva.addport(x,y,true);
        if(UI.canva.selectedObjsNum==0)UI.canva.notmove=true;
    }
    @Override
    public void Pressed(int x,int y)//for group or move
    {
        if(!UI.canva.notmove)canva.addport(x,y,false);//move
        else canva.addport(x,y,true);//group
        canva.selectAreaStartX=x;
        canva.selectAreaStartY=y;
        canva.pressX=x;
        canva.pressY=y;
    }
    @Override
    public void Dragged(int startx,int starty,int endx,int endy)
    {
        canva.selectAreaStartX=startx;
        canva.selectAreaStartY=starty;
        canva.selectAreaEndX=endx;
        canva.selectAreaEndY=endy;
        canva.addport();
    }
    @Override
    public void Released(int startx,int starty,int endx,int endy,Obj firObj,Obj secObj,int startport,int endport)
    {
        canva.selectAreaStartX=startx;
        canva.selectAreaStartY=starty;
        canva.selectAreaEndX=endx;
        canva.selectAreaEndY=endy;
        canva.addport();

    }
}
