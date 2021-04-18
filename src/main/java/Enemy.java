import java.util.ArrayList;
//står får alle funktionerne alle fjender skal have. Alle fjender implementerer dette interface.
public interface Enemy {


    void changeSprites();

    void display();

    void finishAttack();

    boolean draw();

    void attack(Player p);

    void move(Player p,boolean ups);

    void hit(Player p,ArrayList<Drops> drops);
    void avoid(Player p);
    void createAttackZone();

    float getDistance();

    ArrayList<AttackZone> getAttackZoneArray();

    boolean getAttackZones();

    boolean getDamage();

    int getScale();

    boolean getUnBlockable();

    void parry();

}
