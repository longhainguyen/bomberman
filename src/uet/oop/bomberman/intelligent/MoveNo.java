package uet.oop.bomberman.intelligent;

import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.entities.Entity;

import java.util.Random;

public class MoveNo extends MoveRandom {
    private long start;
    private long end;
    private int s = 1;
    private Random random;

    public MoveNo() {
        random = new Random();
        start = System.currentTimeMillis()/1000;
    }

    @Override
    public void moveRandom(Entity entity, Collision collision, Rect rect) {
        long temp = 0;
        super.moveRandom(entity, collision, rect);
        if(Speed_random > 0) {
            temp = end - start;
            end = System.currentTimeMillis() / 1000;
            if(temp > 5) {
                Speed_random = 0;
                start = System.currentTimeMillis() / 1000;
            }
        }else {
            end = System.currentTimeMillis() / 1000;
            temp = end - start;
            if(temp > 5) {
                Speed_random = 2;
                start = System.currentTimeMillis() / 1000;
            }
        }
    }
}
