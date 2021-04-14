import java.util.ArrayList;

public interface Enemy {

    ArrayList<AttackZone> attackZoneArrayList= new ArrayList<>();

    void display();

    void draw();

    void finishAttack();

    void attack(Player p);

    void move(Player p);

    void hit(Player p);

    void createAttackZone();
}
