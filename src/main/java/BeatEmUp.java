import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;
//Står for at lave alt hvad der sker under selve spillet.
    public class BeatEmUp  {
PApplet p;
        TextField textField;
        Table scores;
        Boolean visible = false;
        ImageLoader imgLoad;
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
       Player player;
        int gameScore = 0;
        ArrayList<Drops> drops = new ArrayList<>();

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
//reseter spillet
            visible = false;
            enemyList.clear();
            player = new Player(p,imgLoad);
            enemySpawnManager = new EnemySpawnManager(p, enemyList,imgLoad);
            enemySpawnManager.spawnEnemy();
           gameScore = 0;
            p.rectMode(0);

        }


        public void draw() {
            //Tegner alt på skærmen når spillet er i gang
            p.clear();
            p.background(0,0,122);
          //  System.out.println(player.dead);

        //    System.out.println(player.dead);
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
                nEnemy.avoid(player);
                if(!player.dead)
                nEnemy.attack(player);
                for(int i2 = 0; i2<enemyList.size();++i2 ) {
                    if ((nEnemy.getDistance() > enemyList.get(i2).getDistance()&&nEnemy.getDistance()<400)) {
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
            //saver scoren fra den omgang af spillet.
        textField = new TextField();
        String name = textField.name;
        String score = String.valueOf(gameScore);
p.println(score);
        int rowC = scores.getRowCount();
        scores.setString(rowC, 0, name);
        scores.setString(rowC, 1, score);


        boolean success = p.saveTable(scores, "Scores.csv");
        System.out.println("done: " + success);

    }
        public void keyPressed(char key, int keyCode){

            player.controls(key,keyCode,true);
        }

        public void keyReleased(char key, int keyCode){

            player.controls(key,keyCode,false);

        }


    }




