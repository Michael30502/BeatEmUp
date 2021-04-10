import processing.core.PApplet;
import processing.core.PVector;

public class AttackZone {

    int attackType;
    PApplet p;
    PVector position;
    int playerWidth;
    int playerHeight;
    int scale;

    AttackZone(int attackType, PApplet p, PVector position,int playerWidth,int playerHeight,int scale ){
    this.attackType = attackType;
    this.p = p;
    this.position = position;
    this.playerWidth = playerWidth;
    this.playerHeight = playerHeight;

    }

    void createAttackZone(){



    }

    void displayAttackZone(boolean damage){
       if(damage)
        p.fill(255,0,0);
        p.rectMode(0);
        p.rect(position.x+playerWidth,position.y+(playerHeight/5),50,50);
        p.fill(255,255,255);
    }



}
