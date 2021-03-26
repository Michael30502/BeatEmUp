import processing.core.PApplet;

public class BeatEmUp extends PApplet {

    public static void main(String[] args ) {
        PApplet.main("BeatEmUp");
    }

    Player player = new Player(this);


    @Override
    public void setup() {
        super.setup();


    }

    @Override
    public void settings() {
        super.settings();
        size(1000,1000);
    }

    @Override
    public void draw() {
        clear();
    player.draw();


    }
    public void keyPressed(){

        player.controls(key,keyCode,true);
}

public void keyReleased(){

    player.controls(key,keyCode,false);

}


}


