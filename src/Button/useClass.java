package Button;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import Shape.ObjuseClass;
import UI.canva;
import Shape.Obj;
public class useClass extends Button{
    private canva canva;

    public useClass(ImageIcon icon,canva canva)
    {
        super("useClass",icon);
        this.canva=canva;
    }
    @Override
    public void mouseClicked(int x,int y)
    {
        Obj useclass = new ObjuseClass(x, y);
        canva.add(useclass);
        System.out.println("Hi");
        repaint();
    }
}
