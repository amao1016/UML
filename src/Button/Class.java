package Button;

import javax.swing.ImageIcon;
import UI.canva;
import Shape.Obj;
import Shape.ObjClass;
public class Class extends Mode{
    public Class(ImageIcon icon,canva canva)
    {
        super(ButtonType.Class,icon,canva);
    }
    @Override
    public void Clicked(int x,int y)
    {
        Obj useclass = new ObjClass(canva,x, y);
        canva.add(useclass);
        canva.objs.add(useclass);
        //canva.repaint();
    }
}
