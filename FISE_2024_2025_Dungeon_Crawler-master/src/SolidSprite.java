import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite{
    /* Initialise un SolidSprite avec ses coordonnées, son image, et ses dimensions.
    Une boîte de collision est une zone virtuelle autour d'un objet qui est utilisée
    pour vérifier s'il entre en collision avec un autre objet dans l'environnement du jeu. */
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x,y,(double) width,(double) height);
    }
    // Vérifie si la boîte de collision du sprite intersecte une autre boîte de collision.
    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }
}