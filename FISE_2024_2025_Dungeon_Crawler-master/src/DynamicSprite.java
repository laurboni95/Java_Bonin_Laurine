import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
//Classe concernant le héros
public class DynamicSprite extends SolidSprite{
    public Direction direction = Direction.EAST;// Direction du héros lorsque le jeu est lancé
    private double speed = 5; //Vitesse lorsque le héros marche
    private double runSpeed = 10; // Vitesse lorsque le héros court
    private double timeBetweenFrame = 250;// Temps entre chaque image de l'animation
    private boolean isWalking =true; //Le héros marche lors du lancement du jeu
    private boolean isRunning = false; // Le héros ne court pas lors du lancement du jeu
    private final int spriteSheetNumberOfColumn = 10;

    private int health = 100; //Vie au lancement du jeu
    private int maxHealth = 100; //Vie maximale

    // Constructeur du héros avec position, image, largeur et hauteur
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public void setRunning(boolean running) {
        this.isRunning = running; // Définit l'état de course
    }
    // Diminue la vie du joueur
    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) health = 0; //La vie du joueur ne peut pas être négative
    }

    public int getHealth() {
        return health;
    }
    // barre de vie du héros
    public void drawHealthBar(Graphics g) {
        int barWidth = (int) width; // la Largeur de la barre de vie est la même que celle du joueur
        int barHeight = 6;          // Hauteur de la barre

        // Position de la barre de vie
        int barX = (int) x;
        int barY = (int) y - 7;
        // Calcul de la largeur en fonction de la vie restante
        int healthWidth = (int) ((health / (double) maxHealth) * barWidth);

        // On créer la barre de vie
        g.setColor(Color.RED); // Vie perdue en rouge
        g.fillRect(barX, barY, barWidth, barHeight);
        g.setColor(Color.GREEN); //Vie du joueur en vert
        g.fillRect(barX, barY, healthWidth, barHeight);
    }
    // Vérifie si le déplacement est possible sans collision
        private boolean isMovingPossible(ArrayList<Sprite> environment){
        Rectangle2D.Double moved = new Rectangle2D.Double();
        double currentSpeed = isRunning ? runSpeed : speed;
        switch(direction){
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }
// Vérifie les collisions avec les autres sprites
        for (Sprite s : environment){
            if ((s instanceof SolidSprite) && (s!=this)){
                if (((SolidSprite) s).intersect(moved)){
                    return false;
                }
            }
        }
        return true;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }



    public void move(){
        double currentSpeed = isRunning ? runSpeed : speed; // Choix de la vitesse
        switch (direction){

            case NORTH -> {
                this.y-=currentSpeed;// Déplacement vers le haut
            }
            case SOUTH -> {
                this.y+=currentSpeed;// Déplacement vers le bas
            }
            case EAST -> {
                this.x+=currentSpeed;// Déplacement vers la droite
            }
            case WEST -> {
                 this.x-=currentSpeed;// Déplacement vers la gauche
            }
        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        int index= (int) (System.currentTimeMillis()/timeBetweenFrame%spriteSheetNumberOfColumn); //Permet de mettre en mouvement le joueur
        // Dessine le personnage à l'écran
        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (direction.getFrameLineNumber()*height),
                (int) ((index+1)*this.width), (int)((direction.getFrameLineNumber()+1)*this.height),null);
        // Dessine la barre de vie
        drawHealthBar(g);
    }

}
