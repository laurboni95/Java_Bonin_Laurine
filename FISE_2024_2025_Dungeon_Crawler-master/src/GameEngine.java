import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Actions lorsque le joueur appuie sur une touche
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> hero.setDirection(Direction.NORTH); // Déplace vers le haut si  on appuie sur la flèche du haut
            case KeyEvent.VK_DOWN -> hero.setDirection(Direction.SOUTH);// Déplace vers le bas si  on appuie sur la flèche du bas
            case KeyEvent.VK_LEFT -> hero.setDirection(Direction.WEST);// Déplace vers le gauche si  on appuie sur la flèche de gauche
            case KeyEvent.VK_RIGHT -> hero.setDirection(Direction.EAST);// Déplace vers le droite si  on appuie sur la flèche de droite
            case KeyEvent.VK_CONTROL -> hero.setRunning(true); // Active le mode course
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            hero.setRunning(false); // Désactive le mode course si la touche control est relachée
        }
    }

}
