import processing.core.PApplet;
import processing.core.PImage;

public class Drops {

    float x,y,width,height,function,widthMult,heightMult;
    Player p;

    Drops(float x,float y,float widthMult,float heightMult, float function,Player p){
        this.x = x;
        this.y = y;
        this.widthMult = widthMult;
        this.heightMult = heightMult;
        this.function = function;
        this.p =p;

    }


    void displayDrops(PImage dropImage){
        width = dropImage.width*widthMult;
        height = dropImage.height*heightMult;

        p.p.image(dropImage,x,y,width*widthMult,height*heightMult);


    }

    void collect(){
        switch ((int)function) {

            case 0: {
                p.health += 20;


            }

        }
    }






}
