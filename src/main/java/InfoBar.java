import processing.core.PApplet;
import processing.core.PVector;
// laver health baren og special move baren
public class InfoBar {





    void displayHealthBar(float health, PVector position, float width, PApplet p,float maxHealth){
        p.fill(0,122,0);
        p.rectMode(3);
        p.rect(0-width/2,100,100*health/maxHealth,20);
        p.fill(255,255,255);

    }

    void displaySpecialBar(float specialPower,int x,int y,int width,int height,String text,int r,int g,int b,PApplet p){
        p.rectMode(0);
        p.fill(0,0,0);
        p.rect(x,y,100*width,height);
        p.fill(r,g,b);
        p.rect(x,y,specialPower*width,height);
        p.fill(255,255,255);
        p.textAlign(3,3);
        p.textSize(10);
        p.text(text +": "+(int)specialPower+" %",x+width*50,y+height/2);



    }


}
