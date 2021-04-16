import processing.core.PVector;

import java.util.ArrayList;

public interface Enemy {

    ArrayList<AttackZone> attackZoneArrayList= new ArrayList<>();

    void display();

    boolean draw();

    void attack(Player p);

    void move(Player p,boolean ups);

    void hit(Player p);

    void createAttackZone();

    float getAngle();
}
