public interface Enemy {
    void display();

    void attack(Player p);

    void move(Player p);

    void hit(Player p);

    void createAttackZone();
}
