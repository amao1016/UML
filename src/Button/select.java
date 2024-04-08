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
        canva.addport(x,y);
    }
    @Override
    public void Pressed(int x,int y)//for group
    {

    }
    @Override
    public void Released(int x,int y)//for group
    {

    }
}
