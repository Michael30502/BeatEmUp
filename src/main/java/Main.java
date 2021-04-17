import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Main extends PApplet {



    public static void main(String[] args ) {
        PApplet.main("Main");
    }


public static MainMenu mainMenu;

BeatEmUp beatEmUp;
Infoscreen infoscreen;

    ImageLoader imgLoad = new ImageLoader(this);

    @Override
    public void setup() {
        super.setup();
        imgLoad.loadImage();
        infoscreen = new Infoscreen(this);
        beatEmUp = new BeatEmUp(this,imgLoad);

        mainMenu = new MainMenu(this,beatEmUp,infoscreen);


    }

    @Override
    public void settings() {
        super.settings();
        size(1000,1000);
    }

    @Override
    public void draw() {

        if(beatEmUp.visible){
            beatEmUp.draw();
        }


        mainMenu.display();
        infoscreen.display();


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

    }

}


