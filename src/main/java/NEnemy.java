import processing.core.PApplet;
import processing.core.PVector;

public class NEnemy extends Collision implements Enemy{

PApplet p;
PVector position,velocity;
float sizeX,sizeY;
NEnemy(PApplet p){
    this.p = p;
}
    @Override
    public void display() {
        p.rect(position.x,position.y,sizeX,sizeY);
    }

    @Override
    public void attack(Player p) {

    }

    @Override
    public void move() {
    position.add(velocity);

    }
}
