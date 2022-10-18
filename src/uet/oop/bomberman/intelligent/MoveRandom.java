package uet.oop.bomberman.intelligent;

import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class MoveRandom {
    public int xBeforeChange;
    public int yBeforeChange;
    private int randomMove = 3;
    private int cellWalkPassed;

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
            entity.setGoLeft(true);
            entity.setGoRight(false);
            entity.setX(entity.getX() - Entity.SPEED);
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                entity.setX(entity.getX() + 2 * Entity.SPEED);
                setRandomMove(entity.getX(), entity.getY());
            }
        } else if (randomMove == 2) {
            entity.setGoRight(true);
            entity.setGoLeft(false);
            entity.setX(entity.getX() + Entity.SPEED);
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                entity.setX(entity.getX() - 2 * Entity.SPEED);
                setRandomMove(entity.getX(), entity.getY());
            }
        } else if (randomMove == 3) {
            entity.setY(entity.getY() + Entity.SPEED);
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                entity.setY(entity.getY() - 2 * Entity.SPEED);
                setRandomMove(entity.getX(), entity.getY());
            }
        } else {
            entity.setY(entity.getY() - Entity.SPEED);
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                entity.setY(entity.getY() + 2 * Entity.SPEED);
                setRandomMove(entity.getX(), entity.getY());
            }
        }
    }
}
