package uet.oop.bomberman.intelligent;

import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.entities.Entity;

import java.util.Random;

public class MoveSpeed extends MoveRandom{
    private Random random;

    public MoveSpeed() {
        random = new Random();
    }

    @Override
    public void moveRandom(Entity entity, Collision collision, Rect rect) {
        super.moveRandom(entity, collision, rect);
            if(collision.checkCollisions(rect_fake) || cellWalkPassed >= 2) {
                int d = random.nextInt(2) + 1;
                if (d == 1) {
                    this.Speed_random = 4;
                }else {
                    this.Speed_random = 2;
                }
            }
        }
}
