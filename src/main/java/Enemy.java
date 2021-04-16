import java.util.ArrayList;

public interface Enemy {



    void display();

    void finishAttack();

    boolean draw();

    void attack(Player p);

    void move(Player p,boolean ups);

    void hit(Player p);

    void createAttackZone();

    float getAngle();
}
