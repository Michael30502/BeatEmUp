import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.*;

public class Player {


PVector position = new PVector();
PVector velocity = new PVector();

PApplet p;
int playerWidth = 10;
int playerHeight=10;
boolean down,up,left,right = false;
boolean ready = true;
boolean attackZones = false;

Player(PApplet p){
    this.p = p;
position.set(100,this.p.height/2);

}


void changePosition(){

position.add(velocity);
p.println(p.width);
    position.x =p.constrain(position.x,0,p.width-playerWidth);
    position.y=  p.constrain(position.y,0,p.height-playerHeight);
}


    void draw(){

        changePosition();
        p.rect(position.x,position.y,playerWidth,playerHeight);
        if(ready==false){
            if(attackZones) {

            }else{



            }


        }
       // if(AttackZone)
       // p.rect(position.x+playerWidth,position.y+playerHeight,position.x+attackZonePos.x,position.y,attackZonePos.y);
    }



void createAttackZone(int attackType){
//1 = punch
    AttackZone attackZone = new AttackZone(attackType,p,position,playerWidth);
    attackZone.createAttackZone();
    attackZones = true;

}





void controls(char key, int keyCode,  boolean pressed){
   velocity.set(0,0);
    if (key != p.CODED)
    switch(key){

        case 'j':{
            if(ready) {
                ready = false;
                createAttackZone(1);

            }
        }

        case 's': {
            if((pressed) &&(ready))
            down=true;
            else{
                down=false;}

        }break;

        case 'w': {
            if((pressed) &&(ready))
            up = true;
            else
                up=false;

        }break;
        case 'a': {
            if((pressed) &&(ready))
            left=true;
            else
                left=false;

        }break;
        case 'd': {
            if((pressed) &&(ready))
            right=true;
            else
                right=false;

        }break;


}
else{
    switch (keyCode){

        case DOWN: {
            if((pressed) &&(ready))
            down=true;
            else{
                down=false;}

        }break;

        case UP: {
            if((pressed) &&(ready))
                up=true;
            else{
                up=false;}

        }break;
        case RIGHT: {
            if((pressed) &&(ready))
                right=true;
            else{
                right=false;}


        }break;
        case LEFT: {
            if((pressed) &&(ready))
                left=true;
            else{
                left=false;}


        }break;

    }


    }
    velocity.set(((right)?1:0) +((left)?-1:0),(((up)?-1:0) +((down)?1:0)));
}



}
