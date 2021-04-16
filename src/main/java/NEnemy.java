import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class NEnemy extends Collision implements Enemy{


    PApplet p;
PVector position;
PVector velocity = new PVector(0,0);


    boolean dead = false;

ArrayList<AttackZone> attackZoneArray = new ArrayList<>();
    ArrayList<Enemy> enemyList;

ImageLoader imgLoad;
    ArrayList<PImage> currentImages;
    HealthBar healthBar = new HealthBar();

    float sizeX = 16*4,sizeY=48*4;
    float angleRight;
    float angleLeft;

    int health = 100;
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

    boolean moveAble;
    boolean ready = true;
    boolean damage = false;
    boolean attackZones = false;
    float dist = 101;



NEnemy(PApplet p,PVector position,ImageLoader imgLoad){

    this.position = position;
    this.p = p;
    this.imgLoad = imgLoad;
    currentImages = imgLoad.movement;
}


public float getAngle(){
    return dist;


};

public boolean draw(){
    if(ready==false) {
        if (attackZones) {
            finishAttack();
        } else if (coolDown < 0)
            ready = true;
        else
            coolDown--;
    }
    if(dead)
        return true;
    else return false;

}
    @Override
    public void display() {


        p.pushMatrix();
        p.rectMode(3);
        p.translate(position.x,position.y);
        p.scale(scale,1);
        System.out.println(currentImages.size());
        p.tint(255,0,0);
        p.image(currentImages.get((int)frame),0,0,sizeX*2,sizeY);
        p.noTint();
        frame += 0.2;

        if(frame >= currentImages.size())
            frame =0;

        healthBar.displayHealthBar(health,position,sizeX,p);
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
        attackZoneArray.add(new AttackZone(1,p,position,(int)sizeX,(int)sizeY,scale,0,false));
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
            velocity.x = (float) (1 * Math.cos(angleRight));
            velocity.y = (float) (1 * Math.sin(angleRight));
        }
        if(angleRight>angleLeft) {
            velocity.x = (float) (1 * Math.cos(angleLeft));
            velocity.y = (float) (1 * Math.sin(angleLeft));
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
        position.add(velocity);

    }

    public void finishAttack(){

        //  System.out.println(counter);
        if(counter >= 60){
            if (attackNumber>=maxAttacks) {
                attackZones = false;
                System.out.println("Bruh");
                coolDown = 25+attackNumber*10;
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
    public void hit(Player s) {
    timer+=1;
    p.rectMode(p.CENTER);
    p.rect(position.x-(sizeX/2*scale),position.y,sizeX,sizeY);
    for(int i=0; i<s.attackZoneArray.size();i++)
        if (s.attackZones) {
            if (collision(s.attackZoneArray.get(i).zonePosition.x, s.attackZoneArray.get(i).zonePosition.y, s.attackZoneArray.get(i).zoneWidth, s.attackZoneArray.get(i).zoneHeight, position.x-(sizeX/2*scale), position.y, sizeX, sizeY)) {
                if (s.damage = true&&timer>=60) {
                    health -= 20;
                    timer=0;
                }
            }
        }
        if(health<=0){
            dead = true;
        }
    }

    @Override
    public boolean getAttackZones() {
        return attackZones;
    }

    @Override
    public boolean getDamage() {
        return damage;
    }

    @Override
    public ArrayList<AttackZone> getAttackZoneArray() {
        return attackZoneArray;
    }
}
