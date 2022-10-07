package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

public class Balloon extends Entity {

    private int animation_time = 12;
    private int animate = 0;
    private Collision collision = new Collision();
    private Rect rect;

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        rect = new Rect(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        this.goRight = true;
        this.goLeft = false;
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
            Image image_goRight = Sprite.movingSprite(Sprite.balloom_left1,
                    Sprite.balloom_left2, Sprite.balloom_left3, animate, animation_time).getFxImage();
            this.setImg(image_goRight);
        }
    }

    @Override
    public void update() {
        this.updateAnimation();
        collision.setRectCollisions(Map.stillEntity);
        collision.update(Map.stillEntity);
        rect.setX(x);
        rect.setY(y);
        if (this.goRight && !this.goLeft) {
            this.x += Entity.SPEED;
            if (collision.checkCollisions(rect)) {
                this.x -= Entity.SPEED * 2;
                this.goRight = false;
                this.goLeft = true;
            }
        } else if (this.goLeft && !this.goRight) {
            this.x -= Entity.SPEED;
            if (collision.checkCollisions(rect)) {
                this.x += Entity.SPEED * 2;
                this.goLeft = false;
                this.goRight = true;
            }
        }
    }

}
