package uet.oop.bomberman.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.Band;
import uet.oop.bomberman.sounds.musicItem;


public abstract class Item {
    protected int x;

    protected int y;

    protected Image img;

    public Collision collisionItem;

    public Rect rectItem;

    protected boolean isAte;

    protected musicItem itemSound;


    public Item(int x, int y, Image img){
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
        this.collisionItem = new Collision();
        this.rectItem = new Rect(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        this.isAte = false;
        this.itemSound = new musicItem(1, 50, musicItem.collectItem);
    }

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

    public Collision getCollisionItem() {
        return collisionItem;
    }

    public void setCollisionItem(Collision collisionItem) {
        this.collisionItem = collisionItem;
    }

    public Rect getRectItem() {
        return rectItem;
    }

    public void setRectItem() {
        rectItem.setX(this.x);
        rectItem.setY(this.y);
    }

    public musicItem getItemSound() {
        return itemSound;
    }

    public void setItemSound(musicItem itemSound) {
        this.itemSound = itemSound;
    }

    public boolean isAte() {
        return isAte;
    }

    public void setAte(boolean ate) {
        isAte = ate;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public void move() {

    }

    public void addTextItem(String text){
        if(!Band.textPower.contains(text)) {
            Band.textPower += text + " ";
            Band.detailPower.setText(Band.textPower);
        }
    }

    public boolean isBrickcovered(){
        for(Entity value : Map.stillEntity){
            if(value.getX() == this.x && value.getY() == this.y){
                return true;
            }
        }
        return false;
    }
}
