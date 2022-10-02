package uet.oop.bomberman.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

public class Bomber extends Entity {
    private Image image_current;

    public int animate = 0;
    public int posXInMap;
    public int posYInMap;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.image_current = img;
        posXInMap = x * Sprite.SCALED_SIZE;
        posYInMap = y * Sprite.SCALED_SIZE;
    }

    @Override
    public void update() {
        this.move();
    }

    @Override
    public void move() {
        this.animate++;
        if (animate > 2) {
            animate = 0;
        }
        if (goUp && animate > 0) {
            posYInMap -= SPEED;
            if (posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                    && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                y += SPEED;
                Map.mapStartY = BombermanGame.WINDOW_HEIGHT / 2 - posYInMap;
            } else if (posYInMap < BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = 0;
            } else if (posYInMap > Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = BombermanGame.WINDOW_HEIGHT - Map.heightOfMap;
            }
            y -= SPEED;
            Image image_bomberman_move_up = Sprite.movingSprite(Sprite.player_up,
                    Sprite.player_up_1, Sprite.player_up_2, animate, 3).getFxImage();
            image_current = Sprite.player_up.getFxImage();
            this.setImg(image_bomberman_move_up);
        }
        if (goDown && animate > 0) {
            posYInMap += SPEED;
            if (posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                    && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                y -= SPEED;
                Map.mapStartY = BombermanGame.WINDOW_HEIGHT / 2 - posYInMap;
            } else if (posYInMap > Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = BombermanGame.WINDOW_HEIGHT - Map.heightOfMap;
            } else if (posYInMap < BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = 0;
            }
            y += SPEED;
            Image image_bomberman_move_down = Sprite.movingSprite(Sprite.player_down,
                    Sprite.player_down_1, Sprite.player_down_2, animate, 3).getFxImage();
            image_current = Sprite.player_down.getFxImage();
            this.setImg(image_bomberman_move_down);
        }
        if (goLeft && animate > 0) {
            posXInMap -= SPEED;
            if (posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                    && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                x += SPEED;
                Map.mapStartX = BombermanGame.WINDOW_WIDTH / 2 - posXInMap;
            } else if (posXInMap < BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = 0;
            } else if (posXInMap > Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = BombermanGame.WINDOW_WIDTH - Map.widthOfMap;
            }
            x -= SPEED;

            Image image_bomberman_move_left = Sprite.movingSprite(Sprite.player_left,
                    Sprite.player_left_1, Sprite.player_left_2, animate, 3).getFxImage();
            image_current = Sprite.player_left.getFxImage();
            this.setImg(image_bomberman_move_left);
        }
        if (goRight && animate > 0) {
            posXInMap += SPEED;

            if (posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                    && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                x -= SPEED;
                Map.mapStartX = BombermanGame.WINDOW_WIDTH / 2 - posXInMap;
            } else if (posXInMap > Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = BombermanGame.WINDOW_WIDTH - Map.widthOfMap;
            } else if (posXInMap < BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = 0;
            }
            x += SPEED;

            Image image_bomberman_move_right = Sprite.movingSprite(Sprite.player_right,
                    Sprite.player_right_1, Sprite.player_right_2, animate, 3).getFxImage();
            image_current = Sprite.player_right.getFxImage();
            this.setImg(image_bomberman_move_right);
        }
        if (!goUp && !goDown && !goLeft && !goRight) {
            animate = 0;
            Map.goLeft = false;
            Map.goRight = false;
            Map.goUp = false;
            Map.goDown = false;
            this.setImg(image_current);
        }
    }
}
