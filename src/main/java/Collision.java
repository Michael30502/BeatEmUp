public class Collision {
    boolean collision(float px, float py, float pw, float ph, float rx, float ry, float rw, float rh){
        if(px+pw > rx && px< rx+rw &&  py+ph> ry && py+ph <ry+rh){
            return true;
        }else{
            return false;
        }

    }
}
