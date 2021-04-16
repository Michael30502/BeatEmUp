import processing.core.PApplet;
import processing.core.PVector;

public class Button {
    //denne variable indholder pde bibloteket
        PApplet p;

        //dette er variablerne for x,y postionerne samt knappens høje og brede
        float x,y,w,h,size = 1;
        float rX,rY,rW,rh;
        // denne string indholder hvad der skal stå på knappen
        String text;
        //dette er den instieret interface som indholder funktionen
        Action a;
        //dette er farvene
        PVector textColor = new PVector(0,0,0);
        PVector btnColor = new PVector(255,255,255);

        Button(float x, float y,float w,float h,String text, PApplet p){


            this.p = p;
            rX = x;
            rY = y;
            rh = h;
            rW = w;
            this.text = text;

        }

        void addAction(Action a){

            this.a = a;
        }

        void  display(){
            p.textAlign(p.CENTER);
            p.fill(btnColor.x,btnColor.y,btnColor.z);
            p.rect(x,y,w,h);


            p.fill(textColor.x,textColor.y,textColor.z);
            p.text(text,x + w/8 +p.textWidth(text)/2,y+h/2);
        }

        void click(float mx, float my){
            float mouseX = mx;
            float mouseY = my;


            if (mouseX > x &&
                    mouseX < x + w &&
                    mouseY > y &&
                    mouseY < y + h) {


                a.execute();

            }
        }






}
