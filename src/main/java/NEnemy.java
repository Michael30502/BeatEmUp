import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
//står for at lave fjenderne de andre fjender nedarver fra denne klasse
public class NEnemy extends Collision implements Enemy{


    PApplet p;
PVector position = new PVector();
PVector velocity = new PVector(0,0);


    boolean dead = false;

ArrayList<AttackZone> attackZoneArray = new ArrayList<>();
    ArrayList<Enemy> enemyList;

ImageLoader imgLoad;
    ArrayList<PImage> currentImages;
    InfoBar infoBar = new InfoBar();

    float sizeX = 16*4,sizeY=48*4;
    float angleRight;
    float angleLeft;
    int maxHealth;
    int health;
    int timer = 30;
    int scale = -1;
    float diffXRight;
    float diffY;
    float diffXLeft;
    float frame = 0;
    int counter = 0;
    int attackNumber;
    int coolDown;
    int maxAttacks = 3;
    int stun =0;
    float speed;
    boolean moveAble;
    boolean ready = true;
    boolean damage = false;
    boolean attackZones = false;
    boolean unBlockable = false;
    float dist = 101;
    int attackMode;
    float rTint,gTint,bTint;


NEnemy(PApplet p,PVector position,ImageLoader imgLoad, float speed, int attackMode,float rTint,float gTint, float bTint,int health,boolean unBlockable){
    this.speed = speed;
    this.attackMode = attackMode;
    this.position.x = position.x;
    this.position.y = position.y;
    this.p = p;
    this.imgLoad = imgLoad;
    this.rTint = rTint;
    this.gTint = gTint;
    this.bTint = bTint;
    this.health = health;
    this.maxHealth = health;
    this.unBlockable = unBlockable;
    currentImages = imgLoad.movement;
}


public float getDistance(){
    return dist;


}

    public void changeSprites() {
    if(stun >0) {
        frame = 0;
        currentImages = imgLoad.stun;
    }
        else {

        if (velocity.x > 0 && attackZones == false&&stun <1)
            scale = 1;
        if (velocity.x < 0 && attackZones == false&&stun <1)
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
                    if(currentImages != imgLoad.kickCombo){
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


    }
    }

public boolean draw(){
    changeSprites();
    if(ready==false) {
        if (attackZones) {
            finishAttack();
        } else if (coolDown < 0)
            ready = true;
        else
            coolDown--;



    }

    stun -=1*speed*0.5;
    if (stun < 0 )
        stun=0;

    if(dead)
        return true;
    else return false;


}
    @Override
    public void display() {


        p.pushMatrix();
        p.imageMode(3);
        p.translate(position.x,position.y);
        p.scale(scale,1);
        p.tint(rTint,gTint,bTint);
        p.image(currentImages.get((int)frame),0,0);
        p.noTint();
        frame += 0.1;

        if(frame >= currentImages.size())
            frame =0;

        infoBar.displayHealthBar(health,position,sizeX,p,maxHealth);
        p.popMatrix();
        if(attackZoneArray.size()>0)
        for(int i = 0; i< attackZoneArray.size(); i++)
            attackZoneArray.get(i).displayAttackZone(damage,velocity,currentImages,0);

    }

    @Override
    public void attack(Player s) {


    if(dist<100 &&attackZones==false&&ready){
        createAttackZone();
        attackZones = true;
        ready = false;
	}






    }

    @Override
    public void createAttackZone() {
        attackZoneArray.add(new AttackZone(attackMode,p,position,(int)sizeX,(int)sizeY,scale,0,false));
attackZones = true;
attackNumber ++;

    }

    @Override
    public void move(Player s,boolean ups) {

        angleRight = (float) Math.atan2(diffY, diffXRight);
        angleLeft = (float) Math.atan2(diffY, diffXLeft);
       diffXRight = s.position.x+s.playerWidth -position.x;
       diffY = s.position.y - position.y;
      diffXLeft= s.position.x -position.x;
      dist = PVector.dist(position,s.position);


        if(angleRight<angleLeft) {
            velocity.x = (float) (speed * Math.cos(angleRight));
            velocity.y = (float) (speed * Math.sin(angleRight));
        }
        if(angleRight>angleLeft) {
            velocity.x = (float) (speed * Math.cos(angleLeft));
            velocity.y = (float) (speed * Math.sin(angleLeft));
        }
        if(ups)
            velocity.set(0,0);
            if(collisionBetweenEnemyAndPlayer1(s.position.x, s.position.y, s.playerWidth+20, s.playerHeight, position.x, position.y, sizeX, sizeY)) {
                velocity = new PVector(0, 0);
            }

        /*
        if(s.scale==-1){
            if (collisionBetweenEnemyAndPlayer2(s.position.x, s.position.y, s.playerWidth, s.playerHeight, position.x, position.y, sizeX, sizeY)) {
                velocity = new PVector(0, 0);
            }*/



   //    p.println(collisionBetweenEnemyAndPlayer(s.position.x,s.position.y,s.position.x+s.playerWidth,s.playerHeight,position.x,position.y,sizeX,sizeY));
  if(!attackZones   ){
    if(velocity.x < 0)
        scale = -1;
    if(velocity.x>0)
        scale=1;}
  if(stun>0)
      velocity.set(0,0);
        position.add(velocity);

    }

    public void finishAttack(){

        //  System.out.println(counter);
        if(counter >= 60||stun>0){
            if (attackNumber>=maxAttacks) {
                attackZones = false;
              //  System.out.println("Bruh");
                coolDown = 50+attackNumber*10;
                attackNumber = 0;
                moveAble = true;
                attackZoneArray.clear();

            }else{

                attackNumber ++;



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


        @Override
    public void hit(Player s,ArrayList<Drops> drops) {
    timer+=1;
    p.rectMode(p.CENTER);
  //  p.rect(position.x-(sizeX/2*scale),position.y,sizeX,sizeY);
         //   p.rect();
    for(int i=0; i<s.attackZoneArray.size();i++)
        if (s.attackZones) {
            if (collision(s.attackZoneArray.get(i).zonePosition.x, s.attackZoneArray.get(i).zonePosition.y, s.attackZoneArray.get(i).zoneWidth, s.attackZoneArray.get(i).zoneHeight, position.x-(sizeX/2*scale), position.y, sizeX, sizeY)) {
                if (s.damage = true&&timer>=60) {
                    switch( (s.attackZoneArray.get(i).attackType)) {

                        case 1: {
                            health -= 30;
                            s.specialPower+=2;
                            timer = 0;
                            stun += 60;


                        }break;

                        case 2:{
                            health -= 10;
                            s.specialPower+=5;
                            timer = 0;
                            stun += 50;
                            position.add(-50*scale,0);

                        }break;
                    }
                }
            }
        }
        if(health<=0){
            dead = true;
            if (p.random(0,101)<20)
                drops.add(new Drops(position.x,position.y,1,1,0,s));

        }
    }

    public void parry(){
    stun += 50;

    }

    @Override
    public void avoid(Player p) {

    }

    @Override
    public boolean getAttackZones() {
        return attackZones;
    }

    @Override
    public boolean getDamage() {
        return damage;
    }

    public int getScale(){return scale;}

    @Override
    public ArrayList<AttackZone> getAttackZoneArray() {
        return attackZoneArray;
    }

    @Override
    public boolean getUnBlockable() {
        return unBlockable;
    }


}
