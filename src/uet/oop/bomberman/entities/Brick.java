package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private int max_long_time;
    private int brick_frame;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.max_long_time = 12;
        this.brick_frame = 0;
    }

    public void move() {
        if (this.isDie) {
            Image brick_image = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1,
                    Sprite.brick_exploded2, brick_frame, max_long_time).getFxImage();
            this.setImg(brick_image);
            this.brick_frame++;
            if (brick_frame > max_long_time - 1) {
                this.isDie = false;
            }
        }
    }

    public int getMax_long_time() {
        return max_long_time;
    }

    public void setMax_long_time(int max_long_time) {
        this.max_long_time = max_long_time;
    }

    public int getBrick_frame() {
        return brick_frame;
    }

    public void setBrick_frame(int brick_frame) {
        this.brick_frame = brick_frame;
    }

    @Override
    public void update() {
        this.move();
    }
}
