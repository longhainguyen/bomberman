package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.util.Random;

public class Balloon extends Entity {
    private int xBeforeChange;
    private int yBeforeChange;
    private int randomMove = 3;
    private final int animation_time = 12;
    private int animate = 0;
    private final Collision collision = new Collision();
    private final Rect rect;
    private int cellWalkPassed;

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        rect = new Rect(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        xBeforeChange = this.x;
        yBeforeChange = this.y;
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
        this.setCellWalkPassed();
        int MAX_CELL_WALK_PASSED = 4;
        if (randomMove == 1) {
            this.goLeft = true;
            this.goRight = false;
            this.x -= SPEED;
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                this.x += 2 * SPEED;
                setRandomMove();
            }
        } else if (randomMove == 2) {
            this.goRight = true;
            this.goLeft = false;
            this.x += SPEED;
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                this.x -= 2 * SPEED;
                setRandomMove();
            }
        } else if (randomMove == 3) {
            this.y += SPEED;
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                this.y -= 2 * SPEED;
                setRandomMove();
            }
        }else {
            this.y -= SPEED;
            if (collision.checkCollisions(rect) || cellWalkPassed > MAX_CELL_WALK_PASSED) {
                this.y += 2 * SPEED;
                setRandomMove();
            }
        }
    }

    public void setRandomMove() {
        Random random = new Random();
        int temp = random.nextInt(4) + 1;
        if(temp != randomMove) {
            randomMove = temp;
        }
        this.setXYBeforeChange(x,y);
    }

    public void setCellWalkPassed() {
        double distance = Math.sqrt(Math.pow(x - xBeforeChange,2) + Math.pow(y - yBeforeChange,2));
        cellWalkPassed = (int) distance / Sprite.SCALED_SIZE;
    }

    public void setXYBeforeChange(int x_, int y_) {
        this.xBeforeChange = x_;
        this.yBeforeChange = y_;
    }


}
