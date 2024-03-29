import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

import static processing.core.PConstants.*;
//laver spilleren
public class Player extends Collision {

ImageLoader imgLoad;

PVector position = new PVector();
PVector velocity = new PVector();

ArrayList<AttackZone> attackZoneArray = new ArrayList<>();

PApplet p;

ArrayList<PImage> currentImages;

InfoBar infoBar = new InfoBar();

int playerWidth = 16*4;
int playerHeight= 48*4;
int counter = 0;
int attackNumber = 0;
int scale = 1;
int maxHealth = 100;
int health = 100;
int dashCoolDown =0;


float specialPower = 0;
boolean specialMode = false;
boolean blocking = false;
float coolDown = 0;
float frame = 0;
float timer;
int stun =0;
int parry = 0;

boolean dash = false;
boolean dead = false;
boolean down,up,left,right = false;
boolean ready = true;
boolean attackZones = false;
boolean continueAttack = false;
boolean damage =false;
boolean moveAble = true;
boolean check = false;
boolean specialFinish = false;

Player(PApplet p,ImageLoader imgLoad){
    this.p = p;
position.set(100,this.p.height/2);
this.imgLoad = imgLoad;
currentImages = imgLoad.movement;
}

void changeSprites() {

    if(blocking) {
        if (currentImages != imgLoad.block) {
        currentImages = imgLoad.block;
        frame = 0;
    }}else{
    if(stun>0) {
        currentImages = imgLoad.stun;
        frame = 0;
    }
        else{
        if (velocity.x > 0 && attackZones == false)
            scale = 1;
        if (velocity.x < 0 && attackZones == false)
            scale = -1;

        if (attackZones == true) {
            switch (attackZoneArray.get(0).attackType) {
                case 1: {
                    if (currentImages != imgLoad.punchCombo) {
                        currentImages = imgLoad.punchCombo;
                        frame = 0;
                    }

                }
                break;
                case 2: {
                    if (currentImages != imgLoad.kickCombo) {
                        currentImages = imgLoad.kickCombo;
                        frame = 0;
                    }

                }
                break;
            }
        } else if (velocity.x == 0 && velocity.y == 0) {
            if (currentImages != imgLoad.idle) {
                currentImages = imgLoad.idle;
                frame = 0;
            }
        } else {
            if (currentImages != imgLoad.movement) {
                currentImages = imgLoad.movement;
                frame = 0;
            }
        }
    }}
}

void changePosition(){




    float temp = !ready||blocking?  (float)0.5 :5 ;



    if(dash) {
    temp =100;
    dash = false;
    }
    if(stun >0)
        temp = 0;
    position.add(velocity.x*temp,velocity.y*temp);
    position.x =p.constrain(position.x,0,p.width-playerWidth/2);
    position.y=  p.constrain(position.y,0,p.height-playerHeight/2);
}

void display(){

    infoBar.displaySpecialBar(specialPower,50,120,1,50,"Special Power",122,0,122,p);
    infoBar.displaySpecialBar(health,50,50,1,50,"Health",0,122,0,p);
    p.imageMode(3);
    changeSprites();
    p.pushMatrix();
    p.translate(position.x,position.y);
    p.scale(scale,1);
    System.out.println(frame);
    p.image(currentImages.get((int)frame),0,0,playerWidth*2,playerHeight);
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
    if(counter >= 60||stun>0){
        if (continueAttack == false) {
            attackZones = false;
           // System.out.println("Bruh");
            coolDown = 25+attackNumber*10/attackZoneArray.get(0).attackType;
            attackNumber = 0;
            moveAble = true;
            specialFinish = false;
            attackZoneArray.clear();

        }else{

            attackNumber ++;
            if(attackNumber == 2&&specialFinish)
                attackZoneArray.get(0).special = true;
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

//System.out.println(specialFinish);

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
        dashCoolDown--;
        stun --;
        parry --;
        if (stun < 0 )
            stun=0;
        if( specialPower > 100)
                specialPower = 100;

        if(specialMode)
            specialPower-= 0.1;
        if (specialPower<0)
            specialMode= false;
        if(health> 100 )
            health =100;

}



void createAttackZone(int attackType,boolean special){
//0 = punch
    for(int i = 0;i< (special?2:1);i++) {
        attackZoneArray.add(0, new AttackZone(attackType, p, position, playerWidth, playerHeight, scale,i,true));
    }
    attackZones = true;



}



    public void hit(ArrayList<Enemy> enemyArrayList,ArrayList<Drops> drops) {
        timer+=1;
        p.rectMode(3);

        //p.rect(position.x-(playerWidth/2*scale),position.y,playerWidth,playerHeight);
        for(int i2=0;i2<enemyArrayList.size();i2++){
        Enemy s=enemyArrayList.get(i2);
        for(int i=0; i<s.getAttackZoneArray().size();i++)
            if (s.getAttackZones()) {
                if (collision(s.getAttackZoneArray().get(i).zonePosition.x, s.getAttackZoneArray().get(i).zonePosition.y, s.getAttackZoneArray().get(i).zoneWidth, s.getAttackZoneArray().get(i).zoneHeight, position.x-(playerWidth/2*scale), position.y, playerWidth, playerHeight)) {
                    if (s.getDamage() == true&&timer>=60) {
                        switch( (s.getAttackZoneArray().get(i).attackType)){
                            case 1:{
                                if(!blocking ||s.getScale() ==scale||s.getUnBlockable()) {
                                    health -= 10;
                                    stun += 40;
                                    blocking = false;
                                }else if (parry>0){
                                s.parry();
                                }else
                                    health -= 5;
                                timer = 0;
                            }break;

                            case 2:{
                                if(!blocking ||s.getScale() ==scale||s.getUnBlockable()) {
                                    health -= 5;
                                    stun += 30;
                                    position.add(-50 * scale, 0);
                                    blocking = false;
                                }else if (parry>0){
                                        s.parry();
                                    }else
                                    health -= 2.5;
                                    timer = 0;


                            }break;



                        }


                    }
                }
            }
        if(health<=0){
            dead = true;

        }
    }
        for(int i = 0;drops.size()>i;i++)
if(collision(drops.get(i).x,drops.get(i).y,drops.get(i).width,drops.get(i).height,position.x-(playerWidth/2*scale),position.y,playerWidth,playerHeight)) {
    drops.get(i).collect();
    drops.remove(i);
}
}

void controls(char key, int keyCode,  boolean pressed){
   velocity.set(0,0);
    if (key != p.CODED)
    switch(key){
        case 'z':{}
        case 'j':{
            if (check)
            if(ready && pressed&&stun <1&&!blocking) {
                check = false;
                ready = false;
                createAttackZone(1,specialMode);
                attackNumber = 0;
            } else if( attackNumber< 2 && continueAttack == false){
                check = false;
                continueAttack=true;
            }
            if (pressed == false)
                check = true;
        }break;
        case 'x':{}
        case 'k':{
            if (check)
                if(ready && pressed&&stun <1) {
                    check = false;
                    ready = false;
                    createAttackZone(2,specialMode);
                    attackNumber = 0;
                } else if( attackNumber< 2 && continueAttack == false){
                    check = false;
                    continueAttack=true;
                }
            if (pressed == false)
                check = true;
        }break;
        case 'c':{}
        case 'l':{
            if (check){
                check = false;
            if(attackNumber ==1&&specialPower >10&&specialFinish==false) {
                specialFinish = true;
specialPower-=10;
                if (attackNumber < 2 && continueAttack == false)
                    continueAttack = true;
            }
            }
            if (pressed == false)
                check = true;
        }break;


        case 'u':{}
        case 'v':{
            if(!attackZones&&stun<1) {
                blocking = true;
                if(parry<=0)
            parry = 10;
            }
            if (pressed == false)
                blocking = false;
        }break;

        case'i':{}
        case' ' :{
            if (pressed)
if(!attackZones&&!blocking&&stun<1&&dashCoolDown<0) {
    dash = true;
    dashCoolDown = 150;
}
        }break;

        case 'b':{}
        case 'o':{
            if(specialPower ==100){
               specialMode = true;
            }

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



    velocity.set(((((right)?1:0) +((left)?-1:0))) ,((((up)?-1:0) +((down)?1:0))));
}






}
