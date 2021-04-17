import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class EnemySpawnManager {

    PApplet p;
    ArrayList<Enemy> enemyList;
    ImageLoader imgLoad;
    int wave=2;
    int multipler = 1;
boolean check = false;
    EnemySpawnManager(PApplet p, ArrayList<Enemy> enemyList,ImageLoader imgLoad) {
        this.p = p;
        this.enemyList = enemyList;
        this.imgLoad = imgLoad;
    }

    void spawnEnemy() {
        p.println(wave);

        if (check)
            if (enemyList.size() < 1) {
                wave=wave+1;
            check = false;
            if (wave> 4)
                wave=1;
            multipler+= 0.5;
            }

        if(wave==1&&enemyList.size()<1){
            for(int i = 0;i<6;i++){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new NEnemy(p, pos, imgLoad, 1*multipler,1,255,0,0,100*multipler,false));
                check = true;

            }
        }
        if(wave==2&&enemyList.size()<1){
            for(int i =0;i<6;i++){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new QuickEnemy(p, pos, imgLoad, 3*multipler,2,0,255,0,100*multipler,false));
                check = true;
            }
        }
        if(wave==3&&enemyList.size()<1){
            for(int i =0;i<3;i++){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new QuickEnemy(p, pos, imgLoad, 3*multipler,2,0,255,0,100*multipler,false));
                enemyList.add(new PowerEnemy(p, pos, imgLoad, 1*multipler,1,122,122,122,200*multipler,true));
                check = true;
            }

        }
        if(wave==4&&enemyList.size()<1){
                PVector pos = new PVector(p.random(p.width , p.width+400), p.random(0, p.height));
                enemyList.add(new Boss(p, pos, imgLoad, 3*multipler,1,255,255,0,1000*multipler,false));
                check = true;
            }






    }
}



