import processing.core.PApplet;
import processing.core.PVector;

public class NEnemy extends Collision implements Enemy{

PApplet p;
PVector position = new PVector();
PVector velocity = new PVector(0,0);
float sizeX = 50,sizeY=50;
NEnemy(PApplet p){
    this.p = p;
    position.set(600,600);
}
    @Override
    public void display() {
        p.rect(position.x,position.y,sizeX,sizeY);
    }

    @Override
    public void attack(Player s) {

    }

    @Override
    public void move(Player s) {
      float diffX = s.position.x -position.x;
      float diffY = s.position.y - position.y;

        float angle = (float)Math.atan2(diffY, diffX);
        velocity.x = (float) (1 * Math.cos(angle));
        velocity.y = (float) (1 * Math.sin(angle));


       p.println(s.position.x);
    position.add(velocity);

    }
}
