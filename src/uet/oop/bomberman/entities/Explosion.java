package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {
    private int explosion_frame;
    public static final int max_explosion_frame_time = 3;

    private int pri_frame;

    private Collision explosion_collison;

    private Rect explosion_rect;

    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        explosion_frame = 0;
        pri_frame = -1;
        explosion_rect = new Rect(xUnit, yUnit, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        explosion_collison = new Collision();
    }

   public void setExplosion(int x, int y, Image img){
        this.x = x;
        this.y = y;
        this.img = img;
       explosion_frame = 0;
       pri_frame = -1;
       explosion_rect = new Rect(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
       explosion_collison = new Collision();
   }

    public int getPri_frame() {
        return pri_frame;
    }

    public void setPri_frame(int pri_frame) {
        this.pri_frame = pri_frame;
    }

    public int getExplosion_frame() {
        return explosion_frame;
    }

    public void setExplosion_frame(int explosion_frame) {
        this.explosion_frame = explosion_frame;
    }

    public Collision getExplosion_collison() {
        return explosion_collison;
    }

    public void setExplosion_collison(Collision explosion_collison) {
        this.explosion_collison = explosion_collison;
    }

    public Rect getExplosion_rect() {
        return explosion_rect;
    }

    public void setExplosion_rect(Rect explosion_rect) {
        this.explosion_rect = explosion_rect;
    }

    public void move() {
        if (explosion_frame > max_explosion_frame_time - 1) {
            explosion_frame = 0;
        }
        //System.out.println(explosion_frame);
        //  Image move = Sprite.movingSprite(Sprite.)
        if (pri_frame == 0) {
            Image explosion_image = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                    Sprite.explosion_horizontal_left_last1,
                    Sprite.explosion_horizontal_left_last2, explosion_frame, max_explosion_frame_time).getFxImage();
            this.setImg(explosion_image);
        }
        if (pri_frame == 1) {
            Image explosion_image = Sprite.movingSprite(Sprite.bomb_exploded,
                    Sprite.bomb_exploded1, Sprite.bomb_exploded2,
                    explosion_frame, max_explosion_frame_time).getFxImage();
            this.setImg(explosion_image);
        }
        if (pri_frame == 2) {
            Image explosion_image = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                    Sprite.explosion_horizontal_right_last1,
                    Sprite.explosion_horizontal_right_last2,
                    explosion_frame, max_explosion_frame_time).getFxImage();
            this.setImg(explosion_image);
        }
        if (pri_frame == 3) {
            Image explosion_image = Sprite.movingSprite(Sprite.explosion_horizontal,
                    Sprite.explosion_horizontal1,
                    Sprite.explosion_horizontal2,
                    explosion_frame, max_explosion_frame_time).getFxImage();
            this.setImg(explosion_image);
        }
        if (pri_frame == 4) {
            Image explosion_image = Sprite.movingSprite(Sprite.explosion_vertical_top_last,
                    Sprite.explosion_vertical_top_last1,
                    Sprite.explosion_vertical_top_last2,
                    explosion_frame, max_explosion_frame_time).getFxImage();
            this.setImg(explosion_image);
        }
        if (pri_frame == 5) {
            Image explosion_image = Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                    Sprite.explosion_vertical_down_last1,
                    Sprite.explosion_vertical_down_last2,
                    explosion_frame, max_explosion_frame_time).getFxImage();
            this.setImg(explosion_image);
        }
        if (pri_frame == 6) {
            Image explosion_image = Sprite.movingSprite(Sprite.explosion_vertical,
                    Sprite.explosion_vertical1,
                    Sprite.explosion_vertical2,
                    explosion_frame, max_explosion_frame_time).getFxImage();
            this.setImg(explosion_image);
        }
    }

    public void update() {
        this.move();
    }
}
