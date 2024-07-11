package Button;
import javax.swing.ImageIcon;

import UI.canva;
public class ButtonFactory {
    public static Mode createButton(ButtonType buttonName, ImageIcon icon,canva canva) {
        switch (buttonName) {
            case select:
                return new select(icon,canva);
            case association:
                return new association(icon,canva);
            case generation:
                return new generation(icon,canva);
            case composition:
                return new composition(icon,canva);
            case Class:
                return new Class(icon,canva);
            case useClass:
                return new useClass(icon,canva);
            default:
                return null;
        }
    }
}
