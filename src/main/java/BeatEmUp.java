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
    NEnemy nEnemy = new NEnemy(this,new PVector(500,500));



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
        for(int i = 0; i<enemyList.size();++i ) {
            NEnemy nEnemy = (NEnemy) enemyList.get(i);
            nEnemy.display();
            nEnemy.move(player);
            nEnemy.hit(player);
        }
        enemySpawnManager.spawnEnemy();

    }
    public void keyPressed(){

        player.controls(key,keyCode,true);
}

public void keyReleased(){

    player.controls(key,keyCode,false);

}


}


