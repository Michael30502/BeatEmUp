import processing.core.PApplet;

public class BeatEmUp extends PApplet {

    public static void main(String[] args ) {
        PApplet.main("BeatEmUp");
    }

    ImageLoader imgLoad = new ImageLoader(this);

    Player player = new Player(this,imgLoad);
    NEnemy nEnemy = new NEnemy(this);



    @Override
    public void setup() {
        super.setup();
        imgLoad.loadImage();


    }

    @Override
    public void settings() {
        super.settings();
        size(1000,1000);
    }

    @Override
    public void draw() {
        clear();
        nEnemy.display();
        nEnemy.move(player);
    player.draw();


    }
    public void keyPressed(){

        player.controls(key,keyCode,true);
}

public void keyReleased(){

    player.controls(key,keyCode,false);

}


}


