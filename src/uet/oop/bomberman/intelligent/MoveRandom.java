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
        Rect rect_fake = null;
        int MAX_CELL_WALK_PASSED = 4;
        if (randomMove == 1) {
            rect_fake = new Rect(rect.getX() - Entity.SPEED, rect.getY(), rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake)) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setGoRight(false);
                entity.setGoLeft(true);
                entity.setX(entity.getX() - Entity.SPEED);
            }
        } else if (randomMove == 2) {
            rect_fake = new Rect(rect.getX() + Entity.SPEED, rect.getY(), rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake)) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setGoRight(true);
                entity.setGoLeft(false);
                entity.setX(entity.getX() + Entity.SPEED);
            }
        } else if (randomMove == 3) {
            rect_fake = new Rect(rect.getX() , rect.getY() + Entity.SPEED, rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake)) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setY(entity.getY() + Entity.SPEED);
            }
        } else if (randomMove == 4){
            rect_fake = new Rect(rect.getX() , rect.getY() - Entity.SPEED, rect.getW(), rect.getH());
            if(collision.checkCollisions(rect_fake)) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setY(entity.getY() - Entity.SPEED);
            }
        }
    }
}
