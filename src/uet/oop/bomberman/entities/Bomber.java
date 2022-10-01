package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private Image image_current;

    public int animate = 0;
    private int posX;
    private int posY;
    private static final int vec_bom = 5;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.image_current = img;
    }
   /* public void setBomber(Bomber other){
        this = other;
    }*/
    @Override
    public void update() {

    }

    @Override
    public void move() {
        if(animate > 2) {
            animate = 0;
        }
        if (goUp && animate > 0) {
            y -= SPEED;
            Image image_bomberman_move_up = Sprite.movingSprite(Sprite.player_up,
                    Sprite.player_up_1,Sprite.player_up_2, animate,3).getFxImage();
            image_current = Sprite.player_up.getFxImage();
            this.setImg(image_bomberman_move_up);
        }
        if (goDown  && animate > 0) {
            y += SPEED;
            Image image_bomberman_move_down = Sprite.movingSprite(Sprite.player_down,
                    Sprite.player_down_1, Sprite.player_down_2,animate,3).getFxImage();
            image_current = Sprite.player_down.getFxImage();
            this.setImg(image_bomberman_move_down);
        }
        if (goLeft  && animate > 0) {
            x -= SPEED;
            Image image_bomberman_move_left = Sprite.movingSprite(Sprite.player_left,
                    Sprite.player_left_1, Sprite.player_left_2,animate,3).getFxImage();
            image_current = Sprite.player_left.getFxImage();
            this.setImg(image_bomberman_move_left);
        }
        if (goRight  && animate > 0) {
            x += SPEED;
            Image image_bomberman_move_right = Sprite.movingSprite(Sprite.player_right,
                    Sprite.player_right_1, Sprite.player_right_2,animate,3).getFxImage();
            image_current = Sprite.player_right.getFxImage();
            this.setImg(image_bomberman_move_right);
        }
        if(!goUp && !goDown && !goLeft && !goRight) {
            animate = 0;
            this.setImg(image_current);
        }
    }
}
