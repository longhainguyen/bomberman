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
    }

    @Override
    public void moveRandom(Entity entity, Collision collision, Rect rect) {
        super.moveRandom(entity, collision, rect);
    }
}
