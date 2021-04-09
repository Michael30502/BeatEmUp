import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.*;

public class Player {


PVector position = new PVector();
PVector velocity = new PVector();

PApplet p;
int playerWidth = 50;
int playerHeight=100;
int counter = 0;
int attackNumber = 0;

boolean down,up,left,right = false;
boolean ready = true;
boolean attackZones = false;
boolean continueAttack = false;
boolean damage =false;
boolean moveAble = true;
boolean check = false;


Player(PApplet p){
    this.p = p;
position.set(100,this.p.height/2);

}


void changePosition(){
    float temp = ready?  5 : (float)0.5;
    position.add(velocity.x*temp,velocity.y*temp);
    position.x =p.constrain(position.x,0,p.width-playerWidth);
    position.y=  p.constrain(position.y,0,p.height-playerHeight);
}

void display(){

    p.rect(position.x,position.y,playerWidth,playerHeight);

}

void finishAttack(){
    System.out.println(counter);
    if(counter >= 50){
        if (continueAttack == false) {
            ready = true;
            attackZones = false;
            attackNumber= 0;
            counter = 0;
            moveAble = true;
            damage = false;

        }else{
            counter = 0;
            attackNumber ++;
            continueAttack = false;
            damage = false;

        }
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
            }else{



            }


        }
       // if(AttackZone)
       // p.rect(position.x+playerWidth,position.y+playerHeight,position.x+attackZonePos.x,position.y,attackZonePos.y);
    }



void createAttackZone(int attackType){
//1 = punch
    AttackZone attackZone = new AttackZone(attackType,p,position,playerWidth,playerHeight);
    attackZone.displayAttackZone();
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
                velocity.set(0,0);
            } else if( attackNumber<= 5 && continueAttack == false){
                check = false;
                continueAttack=true;
                System.out.println("Bruh");
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

p.println("bruh");
    velocity.set((((right)?1:0) +((left)?-1:0)) ,(((up)?-1:0) +((down)?1:0)));
}



}
