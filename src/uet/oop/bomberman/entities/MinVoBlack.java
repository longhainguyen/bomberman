package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveIntelligent;
import uet.oop.bomberman.intelligent.MoveThough;
import uet.oop.bomberman.intelligent.MoveThoughIntelligent;
import uet.oop.bomberman.maps.Map;

public class MinVoBlack extends Entity{
    private final MoveThough moveThough = new MoveThough();
    //private final MoveThoughIntelligent moveIntelligent = new MoveThoughIntelligent();


    public MinVoBlack(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.max_long_time = 12;
        this.entity_frame = 0;
        moveThough.Speed_random = 2;
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
            Image image_goRight = Sprite.movingSprite(Sprite.minVoBlackLeft,
                    Sprite.minVoBlackLeft, Sprite.minVoBlackLeft, entity_frame, max_long_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.minVoBlackRight,
                    Sprite.minVoBlackRight1, Sprite.minVoBlackRight2, entity_frame, max_long_time).getFxImage();
            this.setImg(image_goLeft);
        }
    }

    @Override
    public void move() {
        if (this.isDie) {
            if (is_time_out_of_emotion) {
                Image oneal_image = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                        Sprite.mob_dead3, entity_frame, max_long_time).getFxImage();
                this.setImg(oneal_image);
                entity_frame++;
            } else {
                Image oneal_image = Sprite.mivVoBlackDead.getFxImage();
                this.setImg(oneal_image);
                emotional_death_time++;
                if (emotional_death_time > max_long_time_emotion - 1) {
                    is_time_out_of_emotion = true;
                }
            }
        }else {
            moveThough.moveRandom(this,this.entity_collision,this.entities_rect);
        }
    }
}
