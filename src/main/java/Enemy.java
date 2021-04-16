import processing.core.PVector;

import java.util.ArrayList;

public interface Enemy {


    void display();

    void draw();

    void finishAttack();

    void attack(Player p);

    void move(Player p);

    void hit(Player p);

    void createAttackZone();


}
