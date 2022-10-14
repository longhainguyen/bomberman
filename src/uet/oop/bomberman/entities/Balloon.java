package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveRandom;
import uet.oop.bomberman.maps.Map;

import java.util.Random;

public class Balloon extends Entity {
    private MoveRandom moveRandom = new MoveRandom();
    private final int animation_time = 12;
    private int animate = 0;
    private final Collision collision = new Collision();
    private final Rect rect;

    public Balloon(int xUnit, int yUnit, Image img) {
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
            Image image_goRight = Sprite.movingSprite(Sprite.balloom_right1,
                    Sprite.balloom_right2, Sprite.balloom_right3, animate, animation_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.balloom_left1,
                    Sprite.balloom_left2, Sprite.balloom_left3, animate, animation_time).getFxImage();
            this.setImg(image_goLeft);
        }
    }

    @Override
    public void update() {
        this.updateAnimation();
        collision.setRectCollisions(Map.stillEntity);
        rect.setX(x);
        rect.setY(y);
        this.move();
    }

    @Override
    public void move() {
        moveRandom.moveRandom(this,collision,rect);
    }

}
