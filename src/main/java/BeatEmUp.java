import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;

    public class BeatEmUp  {
PApplet p;
        TextField textField;
        Table scores;
        Boolean visible = false;
        ImageLoader imgLoad;
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
       Player player;
        int gameScore = 0;
        EnemySpawnManager enemySpawnManager;
        boolean ups = false;
        BeatEmUp(PApplet p,ImageLoader imgLoad,Table scores){
            this.p = p;
            this.imgLoad = imgLoad;
            this.scores = scores;
            enemySpawnManager = new EnemySpawnManager(p, enemyList,imgLoad);
            enemySpawnManager.spawnEnemy();
             player = new Player(p,imgLoad);
        }

        public void startUp() {
            textField = new TextField();
            visible = false;
            enemyList.clear();
            player = new Player(p,imgLoad);
            enemySpawnManager = new EnemySpawnManager(p, enemyList,imgLoad);
            enemySpawnManager.spawnEnemy();
            gameScore = 0;
            p.rectMode(0);

        }


        public void draw() {
            p.clear();
            p.text(gameScore,100,900);
            System.out.println(player.dead);
            if(!player.dead)
            player.draw();
            player.hit(enemyList);

            for(int i = 0; i<enemyList.size();++i ) {
                Enemy nEnemy =  enemyList.get(i);
                nEnemy.display();
                ups = false;
                nEnemy.hit(player);
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
                    gameScore++;
                }
                nEnemy.move(player, ups);




                //println(enemyList.size());
                enemySpawnManager.spawnEnemy();

    }


    }
    void getScores(){
        String name = textField.name;
        String score = String.valueOf(gameScore);

        int rowC = scores.getRowCount();
        scores.setString(rowC, 0, name);
        scores.setString(rowC, 1, score);


        boolean success = p.saveTable(scores, "/src/main/java/csv/Scores.csv");
        System.out.println("done: " + success);

    }
        public void keyPressed(char key, int keyCode){

            player.controls(key,keyCode,true);
        }

        public void keyReleased(char key, int keyCode){

            player.controls(key,keyCode,false);

        }


    }




