import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class NEnemy extends Collision implements Enemy{


    PApplet p;
PVector position = new PVector();
PVector velocity = new PVector(0,0);
    boolean dead = false;

ArrayList<AttackZone> attackZoneArrayList = new ArrayList<>();
ArrayList<PImage> currentImages;


float sizeX = 50,sizeY=100;
    float angleRight;
    float angleLeft;
    int health = 100;
    int timer = 30;
    int scale = -1;
    int frame = 0;
    int counter = 0;
    int attackNumber;
    int maxAttacks;
	



    boolean damage = false;
    boolean attackZones = false;


NEnemy(PApplet p,PVector position){

    this.position = position;
    this.p = p;


}

public void draw(){



}

    @Override
    public void display() {


        p.pushMatrix();
        p.rectMode(3);
        p.translate(position.x,position.y);
        p.scale(scale,1);
        p.rect(0,0,sizeX,sizeY);
        p.popMatrix();
        for(int i =0; i< attackZoneArrayList.size();i++)
            attackZoneArrayList.get(i).displayAttackZone(false,velocity,null,0);
        frame++;
        p.rect(position.x,position.y,sizeX,sizeY);
        if(attackZoneArrayList.size()>0)
        attackZoneArrayList.get(0).displayAttackZone(damage,velocity,currentImages,0);
    }

    @Override
    public void attack(Player s) {


    if(velocity.x == 0 && velocity.y == 0&&attackZones==false){
        createAttackZone();
        attackZones = true;
	}






    }

    @Override
    public void createAttackZone() {
        attackZoneArrayList.add(new AttackZone(1,p,position,(int)sizeX,(int)sizeY,scale,0,false));
attackZones = true;

    }

    @Override
    public void move(Player s) {
		

      float diffXRight = s.position.x+s.playerWidth/2 -position.x;
      float diffY = s.position.y - position.y;
      float diffXLeft= s.position.x-s.playerWidth/2 -position.x;


      angleRight = (float) Math.atan2(diffY, diffXRight);
      angleLeft = (float) Math.atan2(diffY, diffXLeft);
        if(angleRight<angleLeft) {
            velocity.x = (float) (1 * Math.cos(angleRight));
            velocity.y = (float) (1 * Math.sin(angleRight));
        }
        if(angleRight>angleLeft) {
            velocity.x = (float) (1 * Math.cos(angleLeft));
            velocity.y = (float) (1 * Math.sin(angleLeft));
        }

            if(collisionBetweenEnemyAndPlayer1(s.position.x, s.position.y, s.playerWidth+20, s.playerHeight, position.x, position.y, sizeX, sizeY)) {
                velocity = new PVector(0, 0);
                p.println("true");
            }

        /*
        if(s.scale==-1){
            if (collisionBetweenEnemyAndPlayer2(s.position.x, s.position.y, s.playerWidth, s.playerHeight, position.x, position.y, sizeX, sizeY)) {
                velocity = new PVector(0, 0);
            }*/



   //    p.println(collisionBetweenEnemyAndPlayer(s.position.x,s.position.y,s.position.x+s.playerWidth,s.playerHeight,position.x,position.y,sizeX,sizeY));
    if(velocity.x < 0)
        scale = -1;
    if(velocity.x>0)
        scale=1;
        position.add(velocity);

    }

    public void finishAttack(){

        //  System.out.println(counter);
        if(counter >= 60){
            if (attackNumber<maxAttacks == false) {
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


        @Override
    public void hit(Player s) {
    timer+=1;
    for(int i=0; i<s.attackZoneArray.size();i++)
        if (s.attackZones) {
            if (collision(s.attackZoneArray.get(i).zonePosition.x, s.attackZoneArray.get(i).zonePosition.y, s.attackZoneArray.get(i).zoneWidth, s.attackZoneArray.get(i).zoneHeight, position.x, position.y, sizeX, sizeY)) {
                if (s.damage = true&&timer>=30) {
                    health -= 20;
                    timer=0;
                }
            }
        }
        if(health<=0){
            dead = true;
        }
    }
}
