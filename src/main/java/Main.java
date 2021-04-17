
import processing.core.PApplet;
import processing.data.Table;

public class Main extends PApplet {




    public static void main(String[] args ) {
        PApplet.main("Main");
    }

    public static BeatEmUp beatEmUp;
    public static MainMenu mainMenu;
    public static GameEndScreen gameEndScreen;

    Infoscreen infoscreen;
    Credits credits;
    HighScore highscore;
    Table scores;
    GameOver gameOver;
    ImageLoader imgLoad = new ImageLoader(this);

    @Override
    public void setup() {
        super.setup();
        imgLoad.loadImage();
        scores =loadTable("Scores.csv");
        infoscreen = new Infoscreen(this);
        beatEmUp = new BeatEmUp(this,imgLoad,scores);
        credits = new Credits(this);
        highscore = new HighScore(this,beatEmUp);
        gameEndScreen = new GameEndScreen(this,beatEmUp);
        gameOver = new GameOver(this);
        mainMenu = new MainMenu(this,beatEmUp,infoscreen,credits,highscore);


    }

    @Override
    public void settings() {
        super.settings();
        size(1000,1000);
    }

    @Override
    public void draw() {
        clear();
        background(255);
        if(beatEmUp.visible){
            beatEmUp.draw();
            if(beatEmUp.player.dead){
                rectMode(0);
                gameOver.visible = true;
                gameEndScreen.calBest = true;
                beatEmUp.player.dead = false;
            }
        }



        mainMenu.display();
        infoscreen.display();
        credits.display();
        highscore.display();
        gameEndScreen.display();
        highscore.calBest = true;
        gameOver.display();


    }

    public void keyPressed(){

        beatEmUp.keyPressed(key,keyCode);
}

public void keyReleased(){

    beatEmUp.keyReleased(key,keyCode);

}
    @Override
    public void mousePressed() {
        mainMenu.mousePressed(mouseX,mouseY);
        infoscreen.mousePressed(mouseX,mouseY);
        credits.mousePressed(mouseX,mouseY);
        highscore.mousePressed(mouseX,mouseY);
        gameEndScreen.mousePressed(mouseX,mouseY);
        gameOver.mousePressed(mouseX,mouseY);
    }

}


