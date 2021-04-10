import processing.core.PApplet;
import processing.core.PVector;

public class AttackZone {


    PApplet p;
    PVector position;
    PVector zonePosition = new PVector();

    int attackType;
    int playerWidth;
    int playerHeight;
    int scale;
    int zoneWidth = 50;
    int zoneHeight = 50;

    boolean stand =false;

    AttackZone(int attackType, PApplet p, PVector position,int playerWidth,int playerHeight,int scale ,int stand){
    this.attackType = attackType;
    this.p = p;
    this.position = position;
    this.playerWidth = playerWidth;
    this.playerHeight = playerHeight;
    this.scale = scale;
    zonePosition.set(position.x+playerWidth*scale,position.y+(playerHeight/5));
    zoneWidth *= scale;
    if(stand ==1)
        this.stand =true;
    }

    void createAttackZone(){



    }

    void displayAttackZone(boolean damage,PVector velocity){

        zonePosition.add(velocity);
if(stand){
    zonePosition.add(velocity);
    zonePosition.add(velocity);
    zonePosition.add(velocity);
    zonePosition.add(velocity);
    zonePosition.add(velocity);
    zonePosition.add(velocity);
}

       if(damage)
        p.fill(255,0,0);
        p.rectMode(0);
        p.rect(zonePosition.x, zonePosition.y,zoneWidth,zoneHeight);
        p.fill(255,255,255);
    }



}
