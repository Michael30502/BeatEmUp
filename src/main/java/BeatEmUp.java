import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class BeatEmUp extends PApplet {

    public static void main(String[] args ) {
        PApplet.main("BeatEmUp");
    }

    ImageLoader imgLoad = new ImageLoader(this);
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    Player player = new Player(this,imgLoad);

    EnemySpawnManager enemySpawnManager;


    @Override
    public void setup() {
        super.setup();
        imgLoad.loadImage();
        enemySpawnManager = new EnemySpawnManager(this, enemyList);
        enemySpawnManager.spawnEnemy();


    }

    @Override
    public void settings() {
        super.settings();
        size(1000,1000);
    }

    @Override
    public void draw() {
        clear();
      //  player.hit();
        player.draw();

        for(int i = 0; i<enemyList.size();++i ) {
            NEnemy nEnemy = (NEnemy) enemyList.get(i);
            nEnemy.display();
            nEnemy.move(player);
            nEnemy.hit(player);
          if(nEnemy.angleLeft< ((NEnemy) enemyList.get(i)).angleLeft||nEnemy.angleRight<((NEnemy) enemyList.get(i)).angleRight){



          }
            println(((NEnemy) enemyList.get(i)).angleLeft);
            if(nEnemy.dead){
                enemyList.remove(i);
                i = i-1;
            }
        }

        println(enemyList.size());
        enemySpawnManager.spawnEnemy();

    }
    public void keyPressed(){

        player.controls(key,keyCode,true);
}

public void keyReleased(){

    player.controls(key,keyCode,false);

}


}


