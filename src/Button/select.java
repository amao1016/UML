package Button;
import javax.swing.ImageIcon;
import UI.canva;
public class select extends Button{
    private canva canva;
    public select(ImageIcon icon,canva canva)
    {
        super("select",icon);
        this.canva=canva;
    }
    @Override
    public void mouseClicked(int x,int y)
    {

    }
    @Override
    public void mousePressed(int x,int y)//for group
    {

    }
    @Override
    public void mousedReleased(int x,int y)//for group
    {

    }
}
