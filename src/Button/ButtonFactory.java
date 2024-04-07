package Button;
import javax.swing.ImageIcon;

import UI.canva;
public class ButtonFactory {
    public static Button createButton(String buttonName, ImageIcon icon,canva canva) {
        switch (buttonName) {
            case "select":
                return new select(icon,canva);
            case "AssociationLine":
                return new association(icon,canva);
            case "GenerationLine":
                return new generation(icon,canva);
            case "CompositionLine":
                return new composition(icon,canva);
            case "Class":
                return new Class(icon,canva);
            case "UseCase":
                return new useClass(icon,canva);
            default:
                return null;
        }
    }
}
