import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class ImageLoader {

    PApplet p;

    ArrayList<PImage> punchCombo = new ArrayList<>();

    ImageLoader(PApplet p){
    this.p = p;


    }

    void loadImage(){
        for(int i = 1; i < 21;i++) {
            punchCombo.add(new PImage());
            punchCombo.set(i-1,p.loadImage("BasicPunchCombo"+i+".png"));


        }


    }


}
