import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhysicEngine implements Engine{
    // La classe PhysicEngine gère le mouvement des sprites dynamiques et leur interaction avec l'environnement
    // / Liste des sprites qui se déplacent
    private final ArrayList<DynamicSprite> movingSpriteList;
    //Liste des sprites représentant l'environnement (fixe)
    private ArrayList <Sprite> environment;

    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    @Override
    public void update() {
        // Déplacer chaque sprite mobile si le déplacement est possible
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);

        }
    }
}
