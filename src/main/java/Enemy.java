import java.util.ArrayList;

public interface Enemy {


    void changeSprites();

    void display();

    void finishAttack();

    boolean draw();

    void attack(Player p);

    void move(Player p,boolean ups);

    void hit(Player p,ArrayList<Drops> drops);

    void createAttackZone();

    float getAngle();

    ArrayList<AttackZone> getAttackZoneArray();

    boolean getAttackZones();

    boolean getDamage();

    int getScale();


}
