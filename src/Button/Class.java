package Button;

import javax.swing.ImageIcon;
import UI.canva;
public class Class extends Button{
    private canva canva;
    public Class(ImageIcon icon,canva canva)
    {
        super("Class",icon);
        this.canva=canva;
    }
    @Override
    public void mouseClicked(int x,int y)
    {

    }

}
