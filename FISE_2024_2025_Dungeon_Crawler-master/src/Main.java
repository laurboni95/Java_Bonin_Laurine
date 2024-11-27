import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame; // Fenêtre principale du jeu

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{
        // Initialisation de la fenêtre de jeu
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(910,740); // taille de la fenêtre de jeu
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Création d'un sprite dynamique pour le héros
        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);


        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update()); // Met à jour l'affichage toutes les 50 ms
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update()); // Met à jour la logique du jeu toutes les 50 ms
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update()); // Met à jour la physique du jeu toutes les 50 ms
        // Démarrage des timers pour lancer les mises à jour régulières
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        // Charger le niveau à partir d'un fichier texte qui est la map du jeu
        Playground level = new Playground("./data/level1.txt");
        // Ajout des sprites et des objets
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main(String[] args) throws Exception {
        // Création et affichage de l'écran de lancement
        GameLauncher gameLauncher = new GameLauncher();
        gameLauncher.waitForStart(); // Attendre que l'utilisateur commence le jeu après clic sur le bouton
    }

}
