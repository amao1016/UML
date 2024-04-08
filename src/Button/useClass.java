package Button;

import javax.swing.ImageIcon;
import Shape.ObjuseClass;
import UI.canva;
import Shape.Obj;
public class useClass extends Button{

    public useClass(ImageIcon icon,canva canva)
    {
        super("useClass",icon,canva);
    }
    public void Clicked(int x,int y)
    {
        Obj useclass = new ObjuseClass(canva,x, y);
        canva.add(useclass);
        canva.objs.add(useclass);
        //canva.repaint();
    }
}
