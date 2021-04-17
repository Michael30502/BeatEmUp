public class Collision {

    boolean collision(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh) {
        if (px + pw > rx-rw/2 && px < rx + rw/2 && py + ph > ry-rh/2 && py + ph < ry + rh/2) {
            return true;

        } else return false;

    }

    boolean collisionBetweenEnemyAndPlayer1(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh ) {

            if (((rx-rw/2 < px)&&(rx-rw/2>px-pw)&&(ry<py+ph/2)&&(ry>py-ph/2))) {
                return true;
            }return false;

    }
    boolean collisionBetweenEnemyAndPlayer2(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh ) {

        if (px - pw <= rx + rw && px - pw > rx + (rw / 2) && py < ry + (rh / 2) && py + ph > ry || px >= rx && rx + (rw / 2) > px && py < ry + (rh / 2) && py + ph > ry) {
            return true;
        } return false;


}
}



