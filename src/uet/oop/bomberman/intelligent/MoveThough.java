package uet.oop.bomberman.intelligent;

import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.util.*;

public class MoveThough extends MoveRandom{
    public MoveThough() {
        this.Speed_random = 1;
    }

    public void moveRandom2(Entity entity, Collision collision, Rect rect) {
        this.setCellWalkPassed(entity.getX(), entity.getY());
        int MAX_CELL_WALK_PASSED = 4;
        if (randomMove == 1) {
            rect_fake = new Rect(rect.getX() - Speed_random, rect.getY(), rect.getW(), rect.getH());
            if(collision.checkCollisionsOfBrick(rect_fake) || cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setGoRight(false);
                entity.setGoLeft(true);
                entity.setX(entity.getX() - Speed_random);
            }
        } else if (randomMove == 2) {
            rect_fake = new Rect(rect.getX() + Speed_random, rect.getY(), rect.getW(), rect.getH());
            if(collision.checkCollisionsOfBrick(rect_fake)|| cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setGoRight(true);
                entity.setGoLeft(false);
                entity.setX(entity.getX() + Speed_random);
            }
        } else if (randomMove == 3) {
            rect_fake = new Rect(rect.getX() , rect.getY() + Speed_random, rect.getW(), rect.getH());
            if(collision.checkCollisionsOfBrick(rect_fake) || cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setY(entity.getY() + Speed_random);
            }
        } else if (randomMove == 4){
            rect_fake = new Rect(rect.getX() , rect.getY() - Speed_random, rect.getW(), rect.getH());
            if(collision.checkCollisionsOfBrick(rect_fake)|| cellWalkPassed >= MAX_CELL_WALK_PASSED) {
                setRandomMove(entity.getX(), entity.getY());
            }else {
                entity.setY(entity.getY() - Speed_random);
            }
        }
    }
}
