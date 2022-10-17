package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

//public class Balloon extends Entity {
//
//    public Balloon(int xUnit, int yUnit, Image img) {
//        super(xUnit, yUnit, img);
//        entity_frame = 0;
//    }
//
//    public void move() {
//        if (this.isDie) {
//            if (is_time_out_of_emotion) {
//                Image balloon_image = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
//                        Sprite.mob_dead3, entity_frame, max_long_time).getFxImage();
//                this.setImg(balloon_image);
//                entity_frame++;
//            } else {
//                Image balloon_image = Sprite.balloom_dead.getFxImage();
//                this.setImg(balloon_image);
//                emotional_death_time++;
//                if (emotional_death_time > max_long_time_emotion - 1) {
//                    is_time_out_of_emotion = true;
//                }
//            }
//        }
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.intelligent.MoveRandom;
import uet.oop.bomberman.maps.Map;

import java.util.Random;

public class Balloon extends Entity {
    private MoveRandom moveRandom = new MoveRandom();
    private final int animation_time = 12;
    private int animate = 0;

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        moveRandom.xBeforeChange = this.x;
        moveRandom.yBeforeChange = this.y;
        entity_frame = 0;
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
        this.move();
        if(!this.isDie)
            this.updateAnimation();
        this.entity_collision.update(Map.stillEntity,Map.entitiesEntity);
    }

    @Override
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
        }else {
            moveRandom.moveRandom(this,this.entity_collision,this.entities_rect);
        }
    }

}
