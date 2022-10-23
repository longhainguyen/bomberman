package uet.oop.bomberman.intelligent;

import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class MoveRandom {
    public int xBeforeChange;
    public int yBeforeChange;
    private int randomMove = 1;
    protected int cellWalkPassed;

    protected int Speed_random = 2;

    protected Rect rect_fake = null;

    private void setCellWalkPassed(int x, int y) {
        double distance = Math.sqrt(Math.pow(x - xBeforeChange, 2) + Math.pow(y - yBeforeChange, 2));
        cellWalkPassed = (int) distance / Sprite.SCALED_SIZE;
    }

    private void setXYBeforeChange(int x_, int y_) {
        this.xBeforeChange = x_;
        this.yBeforeChange = y_;
    }

    private void setRandomMove(int x, int y) {
        Random random = new Random();
        int temp = random.nextInt(4) + 1;
        if (temp != randomMove) {
            randomMove = temp;
        }
        this.setXYBeforeChange(x, y);
    }

    public void moveRandom(Entity entity, Collision collision, Rect rect) {
        this.setCellWalkPassed(entity.getX(), entity.getY());
        int MAX_CELL_WALK_PASSED = 4;
        if (randomMove == 1) {
            rect_fake = new Rect(rect.getX() - Speed_random, rect.getY(), rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake) || cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setGoRight(false);
                entity.setGoLeft(true);
                entity.setX(entity.getX() - Speed_random);
            }
        } else if (randomMove == 2) {
            rect_fake = new Rect(rect.getX() + Speed_random, rect.getY(), rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake)|| cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setGoRight(true);
                entity.setGoLeft(false);
                entity.setX(entity.getX() + Speed_random);
            }
        } else if (randomMove == 3) {
            rect_fake = new Rect(rect.getX() , rect.getY() + Speed_random, rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake) || cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setY(entity.getY() + Speed_random);
            }
        } else if (randomMove == 4){
            rect_fake = new Rect(rect.getX() , rect.getY() - Speed_random, rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake)|| cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setY(entity.getY() - Speed_random);
            }
        }
    }
}
