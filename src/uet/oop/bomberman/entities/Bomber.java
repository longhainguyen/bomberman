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
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.awt.font.ImageGraphicAttribute;
import java.util.List;

public class Bomber extends Entity {
    private Image image_current;

    public int animate = 0;
    public int posXInMap;
    public int posYInMap;

    private int posX;

    private int posY;

    private static final int vec_bom = 5;
    public static final int width = 21;
    public static final int height = 31;

    private int animation_time = 12;
    private Collision Bomber_collision = new Collision();
    private Rect Bomber_rect;

    private boolean turn_right;
    private boolean turn_left;
    private boolean turn_up;
    private boolean turn_down;

    public boolean isTurn_right() {
        return turn_right;
    }

    public void setTurn_right(boolean turn_right) {
        this.turn_right = turn_right;
    }

    public boolean isTurn_left() {
        return turn_left;
    }

    public void setTurn_left(boolean turn_left) {
        this.turn_left = turn_left;
    }

    public boolean isTurn_up() {
        return turn_up;
    }

    public void setTurn_up(boolean turn_up) {
        this.turn_up = turn_up;
    }

    public boolean isTurn_down() {
        return turn_down;
    }

    public void setTurn_down(boolean turn_down) {
        this.turn_down = turn_down;
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.image_current = img;
        Bomber_rect = new Rect(x * width, y * height, width, height);
        posXInMap = x * Sprite.SCALED_SIZE;
        posYInMap = y * Sprite.SCALED_SIZE;
        this.turn_down = false;
        this.turn_left = false;
        this.turn_up = false;
        this.turn_right = false;
    }

    public void setBomber(int x, int y, Image img) {
        this.x = x * 32;
        this.y = y * 32;
        this.img = img;
        Bomber_rect = new Rect(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, width, height);
    }

    public Collision getBomber_collision() {
        return Bomber_collision;
    }

    public void setBomberRectCollisions(List<Entity> stillObjects) {
        Bomber_collision.setRectCollisions(stillObjects);
    }

    public Image getImage_current(){
        return image_current;
    }

    @Override
    public void update() {
        this.move();
        Bomber_collision.update(Map.stillEntity);
    }

    @Override
    public void move() {
        this.animate++;
        if (animate > animation_time - 1) {
            animate = 0;
        }
        if (goUp && animate > 0) {
            posYInMap -= SPEED;
            y -= SPEED;
            Bomber_rect.setY(y);
            if (Bomber_collision.checkCollisions(Bomber_rect)) {
                y += SPEED;
                posYInMap += SPEED;
            }
            if (!(posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                    && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2)) {
                Bomber_rect.setY(y);
            }

            if (posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                    && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                y += SPEED;
                if (Bomber_collision.checkCollisions(Bomber_rect)) {
                    y -= SPEED;
                    Bomber_rect.setY(y + SPEED);
                } else Bomber_rect.setY(y);

                Map.mapStartY = BombermanGame.WINDOW_HEIGHT / 2 - posYInMap;
            } else if (posYInMap < BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = 0;
            } else if (posYInMap > Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = BombermanGame.WINDOW_HEIGHT - Map.heightOfMap;
            }

            this.turn_down = false;
            this.turn_left = false;
            this.turn_up = true;
            this.turn_right = false;
            Image image_bomberman_move_up = Sprite.movingSprite(Sprite.player_up,
                    Sprite.player_up_1, Sprite.player_up_2, animate, animation_time).getFxImage();
            image_current = Sprite.player_up.getFxImage();
            this.setImg(image_bomberman_move_up);
        }
        if (goDown && animate > 0) {
            posYInMap += SPEED;
            y += SPEED;
            Bomber_rect.setY(y);
            if (Bomber_collision.checkCollisions(Bomber_rect)) {
                y -= SPEED;
                posYInMap -= SPEED;
            }
            if (!(posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                    && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2)) {
                Bomber_rect.setY(y);
            }
            if (posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                    && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                y -= SPEED;
                if (Bomber_collision.checkCollisions(Bomber_rect)) {
                    y += SPEED;
                    Bomber_rect.setY(y - SPEED);
                } else
                    Bomber_rect.setY(y);
                Map.mapStartY = BombermanGame.WINDOW_HEIGHT / 2 - posYInMap;
            } else if (posYInMap > Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = BombermanGame.WINDOW_HEIGHT - Map.heightOfMap;
            } else if (posYInMap < BombermanGame.WINDOW_HEIGHT / 2) {
                Map.mapStartY = 0;
            }
            this.turn_down = true;
            this.turn_left = false;
            this.turn_up = false;
            this.turn_right = false;

            Image image_bomberman_move_down = Sprite.movingSprite(Sprite.player_down,
                    Sprite.player_down_1, Sprite.player_down_2, animate, animation_time).getFxImage();
            image_current = Sprite.player_down.getFxImage();
            this.setImg(image_bomberman_move_down);
        }
        if (goLeft && animate > 0) {
            posXInMap -= SPEED;
            x -= SPEED;
            Bomber_rect.setX(x);
            if (Bomber_collision.checkCollisions(Bomber_rect)) {
                x += SPEED;
                posXInMap += SPEED;
            }
            if (!(posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                    && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2)) {
                Bomber_rect.setX(x);
            }

            if (posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                    && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                x += SPEED;
                if (Bomber_collision.checkCollisions(Bomber_rect)) {
                    x -= SPEED;
                    Bomber_rect.setX(x + SPEED);
                } else
                    Bomber_rect.setX(x);
                Map.mapStartX = BombermanGame.WINDOW_WIDTH / 2 - posXInMap;
            } else if (posXInMap < BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = 0;
            } else if (posXInMap > Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = BombermanGame.WINDOW_WIDTH - Map.widthOfMap;
            }

            this.turn_down = false;
            this.turn_left = true;
            this.turn_up = false;
            this.turn_right = false;
            Image image_bomberman_move_left = Sprite.movingSprite(Sprite.player_left,
                    Sprite.player_left_1, Sprite.player_left_2, animate, animation_time).getFxImage();
            image_current = Sprite.player_left.getFxImage();
            this.setImg(image_bomberman_move_left);
        }
        if (goRight && animate > 0) {
            x += SPEED;
            posXInMap += SPEED;
            Bomber_rect.setX(x);
            if (Bomber_collision.checkCollisions(Bomber_rect)) {
                posXInMap -= SPEED;
                x -= SPEED;
            }
            if (!(posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                    && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2)) {
                Bomber_rect.setX(x);
            }
            if (posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                    && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                x -= SPEED;
                if (Bomber_collision.checkCollisions(Bomber_rect)) {
                    x += SPEED;
                    Bomber_rect.setX(x - SPEED);
                } else
                    Bomber_rect.setX(x);
                Map.mapStartX = BombermanGame.WINDOW_WIDTH / 2 - posXInMap;
            } else if (posXInMap > Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = BombermanGame.WINDOW_WIDTH - Map.widthOfMap;
            } else if (posXInMap < BombermanGame.WINDOW_WIDTH / 2) {
                Map.mapStartX = 0;
            }
            this.turn_down = false;
            this.turn_left = false;
            this.turn_up = false;
            this.turn_right = true;
            Image image_bomberman_move_right = Sprite.movingSprite(Sprite.player_right,
                    Sprite.player_right_1, Sprite.player_right_2, animate, animation_time).getFxImage();
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
