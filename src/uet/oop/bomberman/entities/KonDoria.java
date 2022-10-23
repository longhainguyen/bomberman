package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveIntelligent;
import uet.oop.bomberman.intelligent.MoveThough;
import uet.oop.bomberman.intelligent.MoveThoughIntelligent;
import uet.oop.bomberman.maps.Map;

public class KonDoria extends Entity{
    private final MoveThough moveThough = new MoveThough();
    private final MoveThoughIntelligent moveIntelligent = new MoveThoughIntelligent();


    public KonDoria(int xUnit, int yUnit, Image img) {
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
            Image image_goRight = Sprite.movingSprite(Sprite.kondoria_right1,
                    Sprite.kondoria_right2, Sprite.kondoria_right2, entity_frame, max_long_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.kondoria_left1,
                    Sprite.kondoria_left2, Sprite.kondoria_left3, entity_frame, max_long_time).getFxImage();
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
                Image oneal_image = Sprite.kondoria_dead.getFxImage();
                this.setImg(oneal_image);
                emotional_death_time++;
                if (emotional_death_time > max_long_time_emotion - 1) {
                    is_time_out_of_emotion = true;
                }
            }
        }else {
            double distance = Math.sqrt(Math.pow(this.getX() - MoveIntelligent.bomberX, 2)
                    + Math.pow(this.getY() - MoveIntelligent.bomberY, 2));
            if(distance <= 200) {
                moveIntelligent.setIsCanMove(this.entities_rect, this.entity_collision);
                if(moveIntelligent.isCanMove) {
                    moveIntelligent.moveIntelligent(this);
                }else {
                    moveThough.moveRandom(this,this.entity_collision,this.entities_rect);
                }
            }else {
                moveThough.moveRandom(this,this.entity_collision,this.entities_rect);
            }
        }
    }
}
