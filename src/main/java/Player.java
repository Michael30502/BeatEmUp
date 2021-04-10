import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PConstants.*;

public class Player {

ImageLoader imgLoad;

PVector position = new PVector();
PVector velocity = new PVector();
AttackZone attackZone;
PApplet p;

ArrayList<PImage> currentImages;

int playerWidth = 16*4;
int playerHeight= 48*4;
int counter = 0;
int attackNumber = 0;
int scale = 1;

float coolDown = 0;
float frame = 0;

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
        switch (attackZone.attackType) {
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
    float temp = ready?  5 : (float)0.5;

    

position.add(velocity.x*temp,velocity.y*temp);
    position.x =p.constrain(position.x,0,p.width-playerWidth);
    position.y=  p.constrain(position.y,0,p.height-playerHeight);
}

void display(){
changeSprites();
p.pushMatrix();
    p.translate(position.x,position.y);
    p.scale(scale,1);
    p.image(currentImages.get((int)frame),0,0,playerWidth*2,playerHeight);
    p.popMatrix();
    frame+= 0.1;
if(frame> currentImages.size()-1){
    frame=0;
}


if(attackZones)
    attackZone.displayAttackZone(damage);
}

void finishAttack(){
    System.out.println(counter);
    if(counter >= 60){
        if (continueAttack == false) {
            attackZones = false;
            System.out.println("Bruh");
            coolDown = 25+attackNumber*10;
            attackNumber = 0;
            moveAble = true;


        }else{

            attackNumber ++;
            continueAttack = false;


        }
        damage = false;
        counter = 0;
    }
    else{
        counter ++;
        if(counter >= 25) {
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
p.println(coolDown);

        }
       // if(AttackZone)
       // p.rect(position.x+playerWidth,position.y+playerHeight,position.x+attackZonePos.x,position.y,attackZonePos.y);
    }



void createAttackZone(int attackType){
//1 = punch
    attackZone = new AttackZone(attackType,p,position,playerWidth,playerHeight,scale);
    attackZones = true;

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
                createAttackZone(1);
                attackNumber = 1;
            } else if( attackNumber<= 3 && continueAttack == false){
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

    velocity.set((((right)?1:0) +((left)?-1:0)) ,(((up)?-1:0) +((down)?1:0)));
}






}
