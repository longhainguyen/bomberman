package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {

    protected static final int SPEED = 2;
    protected boolean goUp;
    protected boolean goDown;
    protected boolean goLeft;
    protected boolean goRight;

    protected boolean isDie;

    protected int max_long_time;

    protected int entity_frame;

    protected Rect entities_rect;

    protected Collision entity_collision = new Collision();

    public Collision getEntity_collision() {
        return entity_collision;
    }

    public void setEntity_collision(Collision entity_collision) {
        this.entity_collision = entity_collision;
    }

    public Rect getEntities_rect() {
        return entities_rect;
    }

    public void setEntities_rect(Rect entities_rect) {
        this.entities_rect = entities_rect;
    }

    public boolean isDie() {
        return isDie;
    }

    public void setDie(boolean die) {
        isDie = die;
    }

    public boolean isGoUp() {
        return goUp;
    }

    public void setGoUp(boolean goUp) {
        this.goUp = goUp;
    }

    public boolean isGoDown() {
        return goDown;
    }

    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    public boolean isGoLeft() {
        return goLeft;
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    public boolean isGoRight() {
        return goRight;
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }

    public int getMax_long_time() {
        return max_long_time;
    }

    public void setMax_long_time(int max_long_time) {
        this.max_long_time = max_long_time;
    }

    public int getEntity_frame() {
        return entity_frame;
    }

    public void setEntity_frame(int entity_frame) {
        this.entity_frame = entity_frame;
    }

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.entities_rect = new Rect(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        this.isDie = false;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public void move() {
    }
}
