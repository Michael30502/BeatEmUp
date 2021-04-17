import processing.core.PApplet;
import processing.core.PVector;

public class PowerEnemy extends NEnemy implements Enemy {


    PowerEnemy(PApplet p, PVector position, ImageLoader imgLoad, float speed, int attackMode, float rTint, float gTint, float bTint, int health,boolean unBlockable) {
        super(p, position, imgLoad, speed, attackMode, rTint, gTint, bTint, health,unBlockable);
    }
}
