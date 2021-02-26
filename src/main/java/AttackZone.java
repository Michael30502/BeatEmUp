import processing.core.PApplet;
import processing.core.PVector;

public class AttackZone {

    int attackType;
    PApplet p;
    PVector position;
    int playerWidth;
    AttackZone(int attackType, PApplet p, PVector position,int playerWidth){
    this.attackType = attackType;
    this.p = p;
    this.position = position;
    this.playerWidth = playerWidth;
    }

    void createAttackZone(){
        p.rectMode(3);
    p.rect(position.x+playerWidth,position.y,20,20);


    }



}
