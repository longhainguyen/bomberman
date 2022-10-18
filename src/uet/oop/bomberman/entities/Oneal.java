package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveIntelligent;
import uet.oop.bomberman.intelligent.MoveRandom;
import uet.oop.bomberman.maps.Map;

import java.util.*;

public class Oneal extends Entity {

    private MoveRandom moveRandom = new MoveRandom();
    private MoveIntelligent moveIntelligent = new MoveIntelligent();

    private List<Rect> listRectHaveToMove = new ArrayList<>();
    private int animation_time = 12;
    private int animate = 0;


    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        entity_frame = 0;
        moveRandom.xBeforeChange = this.x;
        moveRandom.yBeforeChange = this.y;
    }

    public void move() {
        if (this.isDie) {
            if (is_time_out_of_emotion) {
                Image oneal_image = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                        Sprite.mob_dead3, entity_frame, max_long_time).getFxImage();
                this.setImg(oneal_image);
                entity_frame++;
            } else {
                Image oneal_image = Sprite.oneal_dead.getFxImage();
                this.setImg(oneal_image);
                emotional_death_time++;
                if (emotional_death_time > max_long_time_emotion - 1) {
                    is_time_out_of_emotion = true;
                }
            }
        }else {
            double distance = Math.sqrt(Math.pow(x - MoveIntelligent.bomberX, 2)
                + Math.pow(y - MoveIntelligent.bomberY, 2));
            if(distance >= 100) {
                moveRandom.moveRandom(this,this.entity_collision,this.entities_rect);
            }else {
                moveIntelligent.find_road(this.entities_rect,this.entity_collision);
                moveIntelligent.moveIntelligent(this);
            }
            //moveRandom.moveRandom(this,this.entity_collision,this.entities_rect);
        }
    }
    private void updateAnimation() {
        this.animate++;
        if (animate > animation_time - 1) {
            animate = 0;
        }
        if (goRight) {
            Image image_goRight = Sprite.movingSprite(Sprite.oneal_right1,
                    Sprite.oneal_right2, Sprite.oneal_right2, animate, animation_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.oneal_left1,
                    Sprite.oneal_left2, Sprite.oneal_left3, animate, animation_time).getFxImage();
            this.setImg(image_goLeft);
        }
    }

    @Override
    public void update() {
        this.move();
        if(!this.isDie)
            this.updateAnimation();
        this.entity_collision.update(Map.stillEntity, Map.entitiesEntity);
    }


}
