public class Collision {

    boolean collision(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh) {
        if (px + pw > rx && px < rx + rw && py + ph > ry && py + ph < ry + rh) {
            return true;

        } else return false;

    }

    boolean collisionBetweenEnemyAndPlayer1(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh ) {

            if (px <= rx + rw && px > rx + (rw / 2) && py < ry + (rh / 2) && py + ph > ry || px + pw >= rx && rx + (rw / 2) > px + pw && py < ry + (rh / 2) && py + ph > ry) {
                return true;
            }return false;

    }
    boolean collisionBetweenEnemyAndPlayer2(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh ) {

        if (px - pw <= rx + rw && px - pw > rx + (rw / 2) && py < ry + (rh / 2) && py + ph > ry || px >= rx && rx + (rw / 2) > px && py < ry + (rh / 2) && py + ph > ry) {
            return true;
        } return false;


}
}



