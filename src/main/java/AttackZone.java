import processing.core.PApplet;
import processing.core.PVector;

public class AttackZone {

    int attackType;
    PApplet p;
    PVector position;
    int playerWidth;
    int playerHeight;
    AttackZone(int attackType, PApplet p, PVector position,int playerWidth,int playerHeight){
    this.attackType = attackType;
    this.p = p;
    this.position = position;
    this.playerWidth = playerWidth;
    this.playerHeight = playerHeight;
    }

    void createAttackZone(){
        p.rectMode(0);
    p.rect(position.x+playerWidth,position.y+(playerHeight/5),50,50);


    }



}
