import processing.core.PApplet;
import processing.core.PVector;
//står for alt hvad der har med knapperne at gøre
public class Button {

        PApplet p;



        float rX,rY,rW,rH;

        String text;

        Action a;

        PVector textColor = new PVector(0,0,0);
        PVector btnColor = new PVector(255,255,255);

        Button(float rX, float rY,float rW,float rH,String text, PApplet p){


            this.p = p;
            this.rX = rX;
            this.rY = rY;
            this.rH = rH;
            this.rW = rW;
            this.text = text;

        }

        void addAction(Action a){

            this.a = a;
        }

        void  display(){
            p.textAlign(p.CENTER);
            p.fill(btnColor.x,btnColor.y,btnColor.z);
            p.rect(rX,rY,rW,rH);


            p.fill(textColor.x,textColor.y,textColor.z);
            p.text(text,rX + rW/8 +p.textWidth(text)/2,rY+rH/2);
        }

        void click(float mx, float my){
            float mouseX = mx;
            float mouseY = my;


            if (mouseX > rX &&
                    mouseX < rX + rW &&
                    mouseY > rY &&
                    mouseY <rY + rH) {

if(a!= null)
                a.execute();

            }
        }






}
