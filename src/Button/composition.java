package Button;
import javax.swing.ImageIcon;
import UI.canva;
public class composition extends Button{
    private canva canva;
    public composition(ImageIcon icon,canva canva)
    {
        super("composition",icon);
        this.canva = canva;
    }
    @Override
    public void mouseClicked(int x,int y)
    {

    }
    @Override
    public void mousePressed(int x,int y)
    {

    }
    @Override
    public void mousedReleased(int x,int y)
    {
        
    }
}
