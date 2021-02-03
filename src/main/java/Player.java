import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PConstants.*;

public class Player {


PVector position = new PVector();
PVector velocity = new PVector();
PApplet p;

Player(PApplet p){
    this.p = p;
position.set(100,this.p.height/2);

}


void changePosition(){


}


    void draw(){

        p.rect(position.x,position.y,10,10);
        changePosition();
    }

void controls(char key, int keyCode,  boolean pressed){
    if (key != p.CODED)
    switch(key){


        case 's': {
            velocity.add(0,-1);

        }break;

        case 'w': {
            velocity.add(0,1);

        }break;
        case 'a': {
            velocity.add(1,0);

        }break;
        case 'd': {
            velocity.add(-1,0);

        }break;


}
else{
    switch (keyCode){

        case DOWN: {
            velocity.add(0,-1);

        }break;

        case UP: {
            velocity.add(0,1);

        }break;
        case RIGHT: {
            velocity.add(1,0);

        }break;
        case LEFT: {
            velocity.add(-1,0);

        }break;

    }


    }

}



}
