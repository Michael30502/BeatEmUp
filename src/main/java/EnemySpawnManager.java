import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class EnemySpawnManager {

    PApplet p;
    ArrayList<Enemy> enemyList;

    EnemySpawnManager(PApplet p, ArrayList<Enemy> enemyList) {
        this.p = p;
        this.enemyList = enemyList;
    }

    void spawnEnemy() {
        if (enemyList.size() < 2) {

            for (int i = 0; i < p.random(5, 50); ++i) {

                if (Math.random() < 0.3) {

                    PVector pos = new PVector(p.random(p.width /2, p.width ),p.random(p.width-p.width/2,p.width-p.width/4));
                    enemyList.add(new NEnemy(p, pos));
                }
            }

        }

    }

}