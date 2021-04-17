import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class EnemySpawnManager {

    PApplet p;
    ArrayList<Enemy> enemyList;
    ImageLoader imgLoad;
    int wave=1;

    EnemySpawnManager(PApplet p, ArrayList<Enemy> enemyList,ImageLoader imgLoad) {
        this.p = p;
        this.enemyList = enemyList;
        this.imgLoad = imgLoad;
    }

    void spawnEnemy() {
        p.println(wave);

        if(wave==1&&enemyList.size()<2){
            for(int i = 0;i<6;i++){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new NEnemy(p, pos, imgLoad, 1,1,255,0,0,100));
            }
        }
        if(wave==2&&enemyList.size()<2){
            for(int i =0;i<6;i++){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new QuickEnemy(p, pos, imgLoad, 3,2,0,255,0,100));
            }
        }
        if(wave==3&&enemyList.size()<2){
            for(int i =0;i<3;i++){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new QuickEnemy(p, pos, imgLoad, 3,2,0,255,0,100));
                enemyList.add(new NEnemy(p, pos, imgLoad, 1,1,255,0,0,100));
            }

        }
        if(wave==4&&enemyList.size()<2){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new Boss(p, pos, imgLoad, 1,1,255,255,0,1000));
            }



        if (enemyList.size() < 2) {
            wave=wave+1;

        }


    }
}



