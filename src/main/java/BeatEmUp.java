import processing.core.PApplet;
import processing.core.PVector;


import javax.swing.*;
import java.util.ArrayList;

    public class BeatEmUp  {
PApplet p;

        Boolean visible = false;
        ImageLoader imgLoad;
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        ArrayList<Drops> drops = new ArrayList<>();
        Player player;

        EnemySpawnManager enemySpawnManager;
        boolean ups = false;
        BeatEmUp(PApplet p,ImageLoader imgLoad){
            this.p = p;
            this.imgLoad = imgLoad;
            enemySpawnManager = new EnemySpawnManager(p, enemyList,imgLoad);
            enemySpawnManager.spawnEnemy();
             player = new Player(p,imgLoad);
        }

        public void StartUp() {



        }


        public void draw() {
            p.clear();
            p.background(0,0,122);
          //  System.out.println(player.dead);
            if(!player.dead)
            player.draw();
            player.hit(enemyList,drops);

            for(int i = 0; i<drops.size();i++)
                drops.get(i).displayDrops(imgLoad.heart);

            for(int i = 0; i<enemyList.size();++i ) {
                Enemy nEnemy =  enemyList.get(i);
                nEnemy.display();
                ups = false;
                nEnemy.hit(player,drops);
                if(!player.dead)
                nEnemy.attack(player);
                for(int i2 = 0; i2<enemyList.size();++i2 ) {
                    if ((nEnemy.getAngle() > enemyList.get(i2).getAngle())) {
                        ups = true;


                    }

                }
               // p.println(ups);
              //  p.println(nEnemy.getAngle());


                if(nEnemy.draw()) {
                    enemyList.remove(i);
                    i = i-1;
                }
                nEnemy.move(player, ups);




                //println(enemyList.size());
                enemySpawnManager.spawnEnemy();

    }
            if (player.dead ){
                p.textAlign(p.CENTER);
                p.textSize(70);
                p.text("Du er færdig i branchen!", p.width / 2, p.height / 2);
                p.textSize(12);
                p.textAlign(p.LEFT);
            }

    }

        public void keyPressed(char key, int keyCode){

            player.controls(key,keyCode,true);
        }

        public void keyReleased(char key, int keyCode){

            player.controls(key,keyCode,false);

        }


    }




