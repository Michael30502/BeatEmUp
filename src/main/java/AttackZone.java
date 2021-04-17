import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class AttackZone {


    PApplet p;
    PVector position;
    PVector zonePosition = new PVector();
    PVector zoneVelocity = new PVector();
    int attackType;
    int width;
    int height;
    int scale;
    int zoneWidth = 50;
    int zoneHeight = 50;

    boolean stand = false;
    boolean playerAttackZone;

    AttackZone(int attackType, PApplet p, PVector position, int width, int height, int scale, int stand, boolean playerAttackZone) {
        this.attackType = attackType;
        this.p = p;
        this.position = position;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.playerAttackZone = playerAttackZone;
        zonePosition.set(position.x+scale*width/5, position.y - height / 5);
if(scale ==-1)
    zonePosition.x-=zoneWidth;
        if (stand == 1)
            this.stand = true;
    }


    void displayAttackZone(boolean damage, PVector velocity, ArrayList<PImage> currentImages, float frame) {
        if (stand)
            zoneVelocity.set(velocity.x * 6, velocity.y * 6);
        else
            zoneVelocity.set(velocity);

        zonePosition.add(zoneVelocity);


        if (damage)
            p.fill(255, 0, 0);
        p.rectMode(0);
        if (stand) {
            //Translator note "ｽﾀﾝﾄﾞﾎﾟﾜｱ" means stand power
            p.text("ｽﾀﾝﾄﾞﾎﾟﾜｱ", 20, 20);
            p.pushMatrix();
            p.translate(zonePosition.x-20 , zonePosition.y);
            p.scale(scale, 1);
            p.tint(152, 92, 163);
            p.image(currentImages.get((int) frame), 0, 0, width * 2, height);
            p.noTint();
            p.popMatrix();
        }


p.rectMode(0);
           // p.rect(zonePosition.x, zonePosition.y, zoneWidth, zoneHeight);


        p.fill(255, 255, 255);

    }
}