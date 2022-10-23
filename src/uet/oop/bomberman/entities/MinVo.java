package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveNo;
import uet.oop.bomberman.maps.Map;

public class MinVo extends Entity{
    private MoveNo moveNo = new MoveNo();
    public MinVo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.max_long_time = 12;
        this.entity_frame = 0;
    }

    @Override
    public void update() {
        this.move();
        if(!this.isDie)
            this.updateAnimation();
        this.entity_collision.update(Map.stillEntity,Map.entitiesEntity);
    }

    private void updateAnimation() {
        this.entity_frame++;
        if ( entity_frame > max_long_time - 1) {
            entity_frame = 0;
        }
        if (goRight) {
            Image image_goRight = Sprite.movingSprite(Sprite.minvo_right1,
                    Sprite.minvo_right2, Sprite.minvo_right2, entity_frame, max_long_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.minvo_left1,
                    Sprite.minvo_left2, Sprite.minvo_left3, entity_frame, max_long_time).getFxImage();
            this.setImg(image_goLeft);
        }
    }

    public void move() {
        if (this.isDie) {
            if (is_time_out_of_emotion) {
                Image doll_image = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                        Sprite.mob_dead3, entity_frame, max_long_time).getFxImage();
                this.setImg(doll_image);
                entity_frame++;
            } else {
                Image doll_image = Sprite.minvo_dead.getFxImage();
                this.setImg(doll_image);
                emotional_death_time++;
                if (emotional_death_time > max_long_time_emotion - 1) {
                    is_time_out_of_emotion = true;
                }
            }
        }else {
            moveNo.moveRandom(this,this.entity_collision,this.entities_rect);
        }
    }
}
