import processing.core.PApplet;
import processing.core.PVector;

public class HealthBar {





    void displayHealthBar(float health, PVector position, float width, PApplet p){
        p.fill(0,122,0);
        p.rectMode(3);
        p.rect(0-width/2,100,health,20);
        p.fill(255,255,255);



    }

}
