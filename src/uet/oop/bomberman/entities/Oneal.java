package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Entity {

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        entity_frame = 0;
        max_long_time = 30;
    }

    public void move() {
        if (this.isDie) {
            Image oneal_image = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                    Sprite.mob_dead3, entity_frame, max_long_time).getFxImage();
            this.setImg(oneal_image);
            entity_frame++;
        }
    }

    @Override
    public void update() {
        move();
    }
}
