public class Collision {

    boolean collision(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh) {
        if (px + pw > rx && px < rx + rw && py + ph > ry && py + ph < ry + rh) {
            return true;

        } else return false;

    }

    boolean collisionBetweenEnemyAndPlayer(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh) {
        if (px <= rx + rw && px > rx+(rw/2) && py < ry+(rh/2) && py + ph > ry||px+pw>=rx&&rx+(rw/2)>px+pw&&py < ry+(rh/2) && py + ph > ry){
            return true;

    }
        else return false;
}

}

