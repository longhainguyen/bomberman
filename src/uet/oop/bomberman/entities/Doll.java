package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveSpeed;
import uet.oop.bomberman.maps.Map;

public class Doll extends Entity{
    private MoveSpeed moveSpeed = new MoveSpeed();

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.max_long_time = 12;
        this.entity_frame = 0;
    }

    private void updateAnimation() {
        this.entity_frame++;
        if ( entity_frame > max_long_time - 1) {
            entity_frame = 0;
        }
        if (goRight) {
            Image image_goRight = Sprite.movingSprite(Sprite.doll_right1,
                    Sprite.doll_right2, Sprite.doll_right2, entity_frame, max_long_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.doll_left1,
                    Sprite.doll_left2, Sprite.doll_left3, entity_frame, max_long_time).getFxImage();
            this.setImg(image_goLeft);
        }
    }

    @Override
    public void update() {
        this.move();
        if(!this.isDie)
            this.updateAnimation();
        this.entity_collision.update(Map.stillEntity,Map.entitiesEntity);
    }

    public void move() {
        if (this.isDie) {
            if (is_time_out_of_emotion) {
                Image doll_image = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                        Sprite.mob_dead3, entity_frame, max_long_time).getFxImage();
                this.setImg(doll_image);
                entity_frame++;
            } else {
                Image doll_image = Sprite.doll_dead.getFxImage();
                this.setImg(doll_image);
                emotional_death_time++;
                if (emotional_death_time > max_long_time_emotion - 1) {
                    is_time_out_of_emotion = true;
                }
            }
        }else {
            moveSpeed.moveRandom(this,this.entity_collision,this.entities_rect);
        }
    }
}
