import processing.core.PApplet;
import processing.core.PVector;

public class Boss extends NEnemy implements Enemy {

    Boss(PApplet p, PVector position, ImageLoader imgLoad, float speed, int attackMode, float rTint, float gTint, float bTint, int health,boolean unBlockable) {
        super(p, position, imgLoad, speed, attackMode, rTint, gTint, bTint, health,unBlockable);
    }
}
