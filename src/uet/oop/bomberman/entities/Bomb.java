package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    private int bomb_frame;
    private int bomb_number;

    private int explosion_time;

    private boolean go;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.bomb_frame = 0;
        this.bomb_number = 0;
        this.explosion_time = 0;
    }


    public void setBomb(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public int getBomb_frame() {
        return bomb_frame;
    }

    public void setBomb_frame(int bomb_frame) {
        this.bomb_frame = bomb_frame;
    }

    public void move() {
        if (this.bomb_frame > 5) {
            this.bomb_frame = 0;
        }
        Image bomb_imgae = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, bomb_frame, 6).getFxImage();
        this.setImg(bomb_imgae);
    }

    public int getBomb_number() {
        return bomb_number;
    }

    public void setBomb_number(int bomb_number) {
        this.bomb_number = bomb_number;
    }

    public int getExplosion_time() {
        return explosion_time;
    }

    public void setExplosion_time(int explosion_time) {
        this.explosion_time = explosion_time;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    @Override
    public void update() {

    }
}
