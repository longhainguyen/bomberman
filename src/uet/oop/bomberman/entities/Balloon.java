package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Entity {

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        entity_frame = 0;
    }

    public void move() {
        if (this.isDie) {
            if (is_time_out_of_emotion) {
                Image balloon_image = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                        Sprite.mob_dead3, entity_frame, max_long_time).getFxImage();
                this.setImg(balloon_image);
                entity_frame++;
            } else {
                Image balloon_image = Sprite.balloom_dead.getFxImage();
                this.setImg(balloon_image);
                emotional_death_time++;
                if (emotional_death_time > max_long_time_emotion - 1) {
                    is_time_out_of_emotion = true;
                }
            }
        }
    }

    @Override
    public void update() {
        move();
    }
}
