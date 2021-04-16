import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Main extends PApplet {

    public static void main(String[] args ) {
        PApplet.main("Main");
    }



BeatEmUp beatEmUp;
    ImageLoader imgLoad = new ImageLoader(this);

    @Override
    public void setup() {
        super.setup();
        imgLoad.loadImage();
        beatEmUp = new BeatEmUp(this,imgLoad);
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


    }
    public void keyPressed(){

        beatEmUp.keyPressed(key,keyCode);
}

public void keyReleased(){

    beatEmUp.keyReleased(key,keyCode);

}


}


