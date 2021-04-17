import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class ImageLoader {

    PApplet p;

    ArrayList<PImage> punchCombo = new ArrayList<>();
    ArrayList<PImage> kickCombo = new ArrayList<>();
    ArrayList<PImage> movement = new ArrayList<>();
    ArrayList<PImage> idle = new ArrayList<>();
    ArrayList<PImage> stun = new ArrayList<>();
    PImage heart = new PImage();

    ImageLoader(PApplet p){
    this.p = p;


    }

    void loadImage(){
        heart = p.loadImage("Heart.png");
        heart.resize(heart.width*3,heart.height*3);
        for(int i = 1; i < 20;i++) {
            kickCombo.add(new PImage());
            kickCombo.set(i-1,p.loadImage("BasicKickCombo"+i+".png"));


        }
        for(int i = 1; i < 20;i++) {
            punchCombo.add(new PImage());
            punchCombo.set(i-1,p.loadImage("BasicCombo"+i+".png"));


        }
        for(int i = 1; i < 7;i++) {
            idle.add(new PImage());
            idle.set(i-1,p.loadImage("idle"+i+".png"));

        }
        for(int i = 1; i < 5;i++) {
            movement.add(new PImage());
            movement.set(i-1,p.loadImage("Walk"+i+".png"));

        }
        stun.add(new PImage());
        stun.set(0,p.loadImage("stun.png"));



    }


}
