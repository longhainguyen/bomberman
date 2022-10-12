package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.max_long_time = 12;
        this.entity_frame = 0;
    }

    public void move() {
        if (this.isDie) {
            Image brick_image = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1,
                    Sprite.brick_exploded2, entity_frame, max_long_time).getFxImage();
            this.setImg(brick_image);
            this.entity_frame++;
            if (entity_frame > max_long_time - 1) {
                this.isDie = false;
            }
        }
    }


    @Override
    public void update() {
        this.move();
    }
}
