import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PConstants.*;

public class Player extends Collision {

ImageLoader imgLoad;

PVector position = new PVector();
PVector velocity = new PVector();

ArrayList<AttackZone> attackZoneArray = new ArrayList<>();

PApplet p;

ArrayList<PImage> currentImages;

HealthBar healthBar = new HealthBar();

int playerWidth = 16*4;
int playerHeight= 48*4;
int counter = 0;
int attackNumber = 0;
int scale = 1;
int health = 100;
float coolDown = 0;
float frame = 0;
float timer;


boolean dead;
boolean down,up,left,right = false;
boolean ready = true;
boolean attackZones = false;
boolean continueAttack = false;
boolean damage =false;
boolean moveAble = true;
boolean check = false;


Player(PApplet p,ImageLoader imgLoad){
    this.p = p;
position.set(100,this.p.height/2);
this.imgLoad = imgLoad;
currentImages = imgLoad.movement;
}

void changeSprites() {
    if (velocity.x > 0 && attackZones == false)
        scale = 1;
    if (velocity.x < 0 && attackZones == false)
        scale = -1;

    if (attackZones == true) {
        switch (attackZoneArray.get(0).attackType) {
            case 1: {
                if (currentImages != imgLoad.punchCombo){
                currentImages = imgLoad.punchCombo;
                frame = 0;}

            }
            break;
            case 2: {
                frame = 0;

            }
            break;
        }
    } else if (velocity.x == 0 && velocity.y == 0) {
        if (currentImages != imgLoad.idle){
        currentImages = imgLoad.idle;
        frame = 0;}
    } else {
        if (currentImages != imgLoad.movement){
        currentImages = imgLoad.movement;
        frame = 0;}
    }
}

void changePosition(){


    

position.add(velocity.x,velocity.y);
    position.x =p.constrain(position.x,0,p.width-playerWidth);
    position.y=  p.constrain(position.y,0,p.height-playerHeight);
}

void display(){


    p.imageMode(3);
changeSprites();
p.pushMatrix();
    p.translate(position.x,position.y);
    p.scale(scale,1);
    p.image(currentImages.get((int)frame),0,0,playerWidth*2,playerHeight);
    healthBar.displayHealthBar(health,position,playerWidth,p);
    p.popMatrix();
    frame+= 0.1;
if(frame> currentImages.size()-1){
    frame=0;
}


if(attackZones) {
    for(int i =0;i< attackZoneArray.size();i++) {
        attackZoneArray.get(i).displayAttackZone(damage, velocity,currentImages,frame);

    }
}
}

void finishAttack(){
  //  System.out.println(counter);
    if(counter >= 60){
        if (continueAttack == false) {
            attackZones = false;
            System.out.println("Bruh");
            coolDown = 25+attackNumber*10;
            attackNumber = 0;
            moveAble = true;
            attackZoneArray.clear();

        }else{

            attackNumber ++;
            continueAttack = false;


        }
        damage = false;
        counter = 0;
    }
    else{
        counter ++;
        if(counter >= 30) {
            damage = true;

        }
    }

}

    void draw(){


        changePosition();
        display();

        if(ready==false){
            if(attackZones) {
finishAttack();
            }else if (coolDown <0)
                ready = true;
            else
                coolDown--;


        }

    }



void createAttackZone(int attackType,boolean stand){
//0 = punch
    for(int i = 0;i< (stand?2:1);i++) {
        attackZoneArray.add(0, new AttackZone(attackType, p, position, playerWidth, playerHeight, scale,i,true));
    }
    attackZones = true;



}



    public void hit(Enemy s) {
        timer+=1;

        for(int i=0; i<s.getAttackZoneArray().size();i++)
            if (s.getAttackZones()) {
                if (collision(s.getAttackZoneArray().get(i).zonePosition.x, s.getAttackZoneArray().get(i).zonePosition.y, s.getAttackZoneArray().get(i).zoneWidth, s.getAttackZoneArray().get(i).zoneHeight, position.x, position.y, playerWidth, playerHeight)) {
                    if (s.getDamage() == true&&timer>=60) {
                        health -= 20;
                        timer=0;
                    }
                }
            }
        if(health<=0){
            dead = true;
        }
    }

void controls(char key, int keyCode,  boolean pressed){
   velocity.set(0,0);
    if (key != p.CODED)
    switch(key){

        case 'j':{
            if (check)
            if(ready && pressed) {
                check = false;
                ready = false;
                createAttackZone(1,false);
                attackNumber = 0;
            } else if( attackNumber< 2 && continueAttack == false){
                check = false;
                continueAttack=true;

            }
            if (pressed == false)
                check = true;
        }break;

        case 's': {
            if((pressed) &&(moveAble))
            down=true;
            else{
                down=false;}

        }break;

        case 'w': {
            if((pressed) &&(moveAble))
            up = true;
            else
                up=false;

        }break;
        case 'a': {
            if((pressed) &&(moveAble))
            left=true;
            else
                left=false;

        }break;
        case 'd': {
            if((pressed) &&(moveAble))
            right=true;
            else
                right=false;

        }break;


}
else{
    switch (keyCode){

        case DOWN: {
            if((pressed) &&(moveAble))
            down=true;
            else{
                down=false;}

        }break;

        case UP: {
            if((pressed) &&(moveAble))
                up=true;
            else{
                up=false;}

        }break;
        case RIGHT: {
            if((pressed) &&(moveAble))
                right=true;
            else{
                right=false;}


        }break;
        case LEFT: {
            if((pressed) &&(moveAble))
                left=true;
            else{
                left=false;}


        }break;

    }


    }

//p.println("bruh");
    float temp = ready?  5 : (float)0.5;
    velocity.set(((((right)?1:0) +((left)?-1:0))*temp) ,((((up)?-1:0) +((down)?1:0))*temp));
}






}
