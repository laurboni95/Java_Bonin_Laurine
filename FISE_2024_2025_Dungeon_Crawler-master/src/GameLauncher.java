import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameLauncher extends JPanel {

    private JButton startButton; // Bouton pour démarrer
    private boolean gameStarted; // indique si le jeu a démarré

    public GameLauncher() {
        this.setLayout(new BorderLayout());
        startButton = new JButton("PLAY"); // Nom bouton
        startButton.setFont(new Font("Comic", Font.PLAIN, 20)); //Taille et police du bouton
        this.add(startButton, BorderLayout.CENTER);  // Ajoute le bouton au centre de la fenêtre

        // Mettre à jour la fenêtre principale
        JFrame frame = new JFrame("Start Game");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setVisible(true);

        // Détecte les clics sur le bouton pour démarrer le jeu
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameStarted = true;
                frame.setVisible(false);  // Masquer la fenêtre de démarrage
                startGame();              // Démarrer le jeu
            }
        });

        // vérifier si le bouton a été cliqué
        gameStarted = false;
    }

    public void waitForStart() {
        // Attendre que l'utilisateur clique sur le bouton pour démarrer le jeu
        while (!gameStarted) {
            try {
                Thread.sleep(100);  // Vérifier toutes les 100 ms si le jeu a démarré
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGame() {
        // Initialiser et démarrer le jeu ici
        try {
            Main main = new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
