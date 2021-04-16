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
    boolean ups = false;

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
            Enemy nEnemy =  enemyList.get(i);
            nEnemy.display();
            ups = false;
            nEnemy.hit(player);
            nEnemy.attack(player);
        for(int i2 = 0; i2<enemyList.size();++i2 ) {
            if ((nEnemy.getAngle() > enemyList.get(i2).getAngle())) {
                ups = true;


            }

        }
            println(ups);
          println(nEnemy.getAngle());
            if(i+1<enemyList.size())
            println(enemyList.get(i+1).getAngle());

            if(nEnemy.draw()) {
                enemyList.remove(i);
                i = i-1;
            }
            nEnemy.move(player, ups);




        //println(enemyList.size());
        enemySpawnManager.spawnEnemy();

    }}
    public void keyPressed(){

        player.controls(key,keyCode,true);
}

public void keyReleased(){

    player.controls(key,keyCode,false);

}


}


