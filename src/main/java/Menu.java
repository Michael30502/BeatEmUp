import processing.core.PApplet;

import java.util.ArrayList;

public abstract class Menu {
    PApplet p;
    Boolean visible = false;
    public float size = 1;
    //listen med alle knapperne

    ArrayList<Button> btnList = new ArrayList<>();

    Menu(PApplet p){
        this.p = p;
    }

    void display(){
        if(visible){
            draw();
            if(btnList != null){
                for(int i = 0; i<btnList.size();++i){
                    btnList.get(i).display();
                }
            }

        }
    }
    abstract void draw();


    void mousePressed(float mx, float my) {
        if(visible){
            mousePushed();
            if(btnList != null){
                for(int i = 0; i<btnList.size();++i){
                    btnList.get(i).click(mx,my);
                }
            }
        }
    }

    abstract void mousePushed();


    void keyPressed(char key) {
        if(visible){

        }
    }





}
