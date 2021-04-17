import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class QuickEnemy extends NEnemy implements Enemy {


    QuickEnemy(PApplet p, PVector position, ImageLoader imgLoad, float speed, int attackMode, float rTint, float gTint, float bTint, int health) {
        super(p, position, imgLoad, speed, attackMode, rTint, gTint, bTint, health);
    }
}