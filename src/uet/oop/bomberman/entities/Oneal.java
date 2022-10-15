package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveIntelligent;
import uet.oop.bomberman.intelligent.MoveRandom;
import uet.oop.bomberman.maps.Map;

import java.util.*;

public class Oneal extends Entity {

    private MoveRandom moveRandom = new MoveRandom();
    private MoveIntelligent moveIntelligent = new MoveIntelligent();

    private List<Rect> listRectHaveToMove = new ArrayList<>();
    private int animation_time = 12;
    private int animate = 0;

    private Collision collision = new Collision();
    private Rect rect;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        rect = new Rect(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        moveRandom.xBeforeChange = this.x;
        moveRandom.yBeforeChange = this.y;
    }

    private void updateAnimation() {
        this.animate++;
        if (animate > animation_time - 1) {
            animate = 0;
        }
        if (goRight) {
            Image image_goRight = Sprite.movingSprite(Sprite.oneal_right1,
                    Sprite.oneal_right2, Sprite.oneal_right2, animate, animation_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.oneal_left1,
                    Sprite.oneal_left2, Sprite.oneal_left3, animate, animation_time).getFxImage();
            this.setImg(image_goLeft);
        }
    }

    @Override
    public void update() {
        this.updateAnimation();
        collision.setRectCollisions(Map.stillEntity);
        collision.update(Map.stillEntity);
        rect.setX(x);
        rect.setY(y);
        this.move();
    }

    @Override
    public void move() {

        double distance = Math.sqrt(Math.pow(x - MoveIntelligent.bomberX, 2)
                + Math.pow(y - MoveIntelligent.bomberY, 2));
        if(distance >= 100) {
            moveRandom.moveRandom(this,collision,rect);
        }else {
            moveIntelligent.find_road(rect,collision);
            moveIntelligent.moveIntelligent(this);
        }
    }

}
