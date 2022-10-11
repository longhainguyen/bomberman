package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {

    private int bomb_frame;
    private int bomb_number;
    private int max_length_explosion;

    private List<Explosion> bomb_explosion = new ArrayList<>();
    private int explosion_time;
    private boolean is_explode;
    private boolean go;

    private List<Explosion> temp = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.bomb_frame = 0;
        this.bomb_number = 0;
        this.explosion_time = 0;
        this.max_length_explosion = 3;
        this.is_explode = false;
    }


    public void setBomb(Bomber other, Image img) {
        int posx = other.getX();
        int posy = other.getY();
        boolean checkinside = false;
        if ((posx / 32) * 32 + Sprite.SCALED_SIZE >= posx + Bomber.width
                && (posy / 32) * 32 + Sprite.SCALED_SIZE >= posy + Bomber.height) {
            checkinside = true;
        }
        if (checkinside) {
            this.x = (posx / 32) * 32;
            this.y = (posy / 32) * 32;
        } else {
            if (other.isTurn_right()) {
                this.x = (posx / 32) * 32 + 32;
                this.y = (posy / 32) * 32;
            } else if (other.isTurn_left()) {
                this.x = (posx / 32) * 32;
                this.y = (posy / 32) * 32;
            } else if (other.isTurn_up()) {
                this.x = (posx / 32) * 32;
                this.y = (posy / 32) * 32;
            } else if (other.isTurn_down()) {
                this.x = (posx / 32) * 32;
                this.y = (posy / 32) * 32 + 32;
            }
        }
        this.img = img;
    }

    public boolean isIs_explode() {
        return is_explode;
    }

    public void setIs_explode(boolean is_explode) {
        this.is_explode = is_explode;
    }

    public void set_entities_die(List<Rect>is_die_rect, List<Entity>entities){

    }

    public int getMax_length_explosion() {
        return max_length_explosion;
    }

    public void setMax_length_explosion(int max_length_explosion) {
        this.max_length_explosion = max_length_explosion;
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

    public List<Explosion> getBomb_explosion() {
        return bomb_explosion;
    }

    public void setBomb_explosion(List<Explosion> bomb_explosion) {
        this.bomb_explosion = bomb_explosion;
    }


    public void swap(int i, int j, List<Explosion>arr){
        Explosion temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public void set_stillobject_die(Rect object, List<Entity>stillObjects){
        for(Entity value : stillObjects){
            if(value.getEntities_rect().getX() == object.getX() && value.getEntities_rect().getY() == object.getY() && value instanceof Brick){
                value.setDie(true);
            }
        }
    }
    @Override
    public void update() {
        this.move();
    }
public void setbomb_explosion(List<Entity>stillObjects) {
    int posx = this.getX();
    int posy = this.getY();
    int length = max_length_explosion;
    int size = 0;
    int count = 0;
    int left = 0;
    int right = 0;
    int up = 0;
    int down = 0;
    List<Explosion> temp = new ArrayList<>();
    temp.add(new Explosion(posx - Sprite.SCALED_SIZE * (length / 2), posy,
            Sprite.bomb_exploded.getFxImage()));
    temp.get(0).setExplosion(posx, posy,
            Sprite.bomb_exploded.getFxImage());
    temp.get(0).setPri_frame(1);
    for (int i = 1; i <= length / 2; i++) {
        Explosion change = new Explosion(0, 0, Sprite.explosion_horizontal_left_last.getFxImage());
        change.setExplosion(temp.get(i - 1).getX() - Sprite.SCALED_SIZE, posy,
                Sprite.explosion_horizontal.getFxImage());
        change.getExplosion_collison().setRectCollisions(stillObjects);
        if (!change.getExplosion_collison().checkCollisions(change.getExplosion_rect())) {
            temp.add(change);
            count++;
            temp.get(count).setPri_frame(3);
            left = count;
        } else {
            set_stillobject_die(change.getExplosion_collison().getIs_die_rect(),  stillObjects);
            break;
        }
    }
    size = temp.size();
    for (int i = size; i < size + length / 2; i++) {
        Explosion change = new Explosion(0, 0, Sprite.explosion_horizontal.getFxImage());
        if (i == size) {
            change.setExplosion(temp.get(0).getX() + Sprite.SCALED_SIZE, posy,
                    Sprite.explosion_horizontal.getFxImage());
        } else {
            change.setExplosion(temp.get(i - 1).getX() + Sprite.SCALED_SIZE, posy,
                    Sprite.explosion_horizontal.getFxImage());
        }
        change.getExplosion_collison().setRectCollisions(stillObjects);
        if (!change.getExplosion_collison().checkCollisions(change.getExplosion_rect())) {
            temp.add(change);
            count++;
            right = count;
            temp.get(count).setPri_frame(3);
        } else {
            set_stillobject_die(change.getExplosion_collison().getIs_die_rect(),  stillObjects);
            break;
        }
    }
    size = temp.size();
    for (int i = size; i < size + length / 2; i++) {
        Explosion change = new Explosion(0, 0, Sprite.explosion_vertical1.getFxImage());
        if (i == size) {
            change.setExplosion(posx, posy - Sprite.SCALED_SIZE,
                    Sprite.explosion_vertical.getFxImage());
        } else {
            change.setExplosion(posx, temp.get(i - 1).getY() - Sprite.SCALED_SIZE,
                    Sprite.explosion_vertical.getFxImage());
        }
        change.getExplosion_collison().setRectCollisions(stillObjects);
        if (!change.getExplosion_collison().checkCollisions(change.getExplosion_rect())) {
            temp.add(change);
            count++;
            temp.get(count).setPri_frame(6);
            up = count;
        } else {
            set_stillobject_die(change.getExplosion_collison().getIs_die_rect(),  stillObjects);
            break;
        }
    }
    size = temp.size();
    for (int i = size; i < size + length / 2; i++) {
        Explosion change = new Explosion(0, 0, Sprite.explosion_vertical1.getFxImage());
        if (i == size) {
            change.setExplosion(posx, posy + Sprite.SCALED_SIZE,
                    Sprite.explosion_vertical.getFxImage());
        } else {
            change.setExplosion(posx, temp.get(i - 1).getY() + Sprite.SCALED_SIZE,
                    Sprite.explosion_vertical.getFxImage());
        }
        change.getExplosion_collison().setRectCollisions(stillObjects);
        if (!change.getExplosion_collison().checkCollisions(change.getExplosion_rect())) {
            temp.add(change);
            count++;
            down = count;
            temp.get(count).setPri_frame(6);
        } else {
            set_stillobject_die(change.getExplosion_collison().getIs_die_rect(),  stillObjects);
            break;
        }
    }
    if(left != 0) {
        temp.get(left).setExplosion(temp.get(left).getX(), temp.get(left).getY(),
                Sprite.explosion_horizontal_left_last.getFxImage());
        temp.get(left).setPri_frame(0);
    }
    if(right != 0) {
        temp.get(right).setExplosion(temp.get(right).getX(), temp.get(right).getY(),
                Sprite.explosion_horizontal_right_last.getFxImage());
        temp.get(right).setPri_frame(2);
    }
    if(up != 0) {
        temp.get(up).setExplosion(temp.get(up).getX(), temp.get(up).getY(),
                Sprite.explosion_vertical_top_last.getFxImage());
        temp.get(up).setPri_frame(4);
    }
    if(down != 0) {
        temp.get(down).setExplosion(temp.get(down).getX(), temp.get(down).getY(),
                Sprite.explosion_vertical_down_last.getFxImage());
        temp.get(down).setPri_frame(5);
    }

    setBomb_explosion(temp);

}

    /*public void setBomb_explosion() {
        // set horizontal
        int posx = this.getX();
        int posy = this.getY();
        int length = max_length_explosion;
        List<Explosion> temp = new ArrayList<>();
        List<Explosion> change = new ArrayList<>();

        for (int i = 0; i < length * 2; i++) {
            change.add(new Explosion(posx - length / 2, posy,
                    Sprite.explosion_horizontal_left_last.getFxImage()));
        }
        // set horizontal
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                change.get(i).setExplosion(posx - Sprite.SCALED_SIZE * (length / 2), posy,
                        Sprite.explosion_horizontal_left_last.getFxImage());
                temp.add(change.get(i));
                temp.get(0).setPri_frame(0);
            } else if (i == length / 2) {
                change.get(i).setExplosion(posx, posy, Sprite.bomb_exploded.getFxImage());
                temp.add(change.get(i));
                temp.get(length / 2).setPri_frame(1);
            } else if (i == length - 1) {
                change.get(i).setExplosion(temp.get(i - 1).getX()
                                + Sprite.SCALED_SIZE, temp.get(i - 1).getY(),
                        Sprite.explosion_horizontal_right_last.getFxImage());
                temp.add(change.get(i));
                temp.get(i).setPri_frame(2);
            } else {
                change.get(i).setExplosion(temp.get(i - 1).getX()
                                + Sprite.SCALED_SIZE, temp.get(i - 1).getY(),
                        Sprite.explosion_horizontal.getFxImage());
                temp.add(change.get(i));
                temp.get(i).setPri_frame(3);
            }
        }
        // set vertical
        for (int j = length; j < length * 2; j++) {
            if (j == length) {
                change.get(j).setExplosion(posx, posy - Sprite.SCALED_SIZE * (length / 2),
                        Sprite.explosion_vertical_top_last.getFxImage());
                temp.add(change.get(j));
                temp.get(j).setPri_frame(4);
            } else if (j == (3 * length - 1) / 2) {
                change.get(j).setExplosion(posx, posy, Sprite.bomb_exploded.getFxImage());
                temp.add(change.get(j));
                temp.get(j).setPri_frame(1);
            } else if (j == length * 2 - 1) {
                change.get(j).setExplosion(posx, temp.get(j - 1).getY() + Sprite.SCALED_SIZE,
                        Sprite.explosion_vertical_down_last.getFxImage());

                temp.add(change.get(j));
                temp.get(j).setPri_frame(5);
            } else {
                change.get(j).setExplosion(posx, temp.get(j - 1).getY() + Sprite.SCALED_SIZE,
                        Sprite.explosion_vertical.getFxImage());
                temp.add(change.get(j));
                temp.get(j).setPri_frame(6);
            }
        }
        setBomb_explosion(temp);
    }*/

    public void addEntities(List<Entity> entities) {
        entities.addAll(bomb_explosion);
    }

    public void getinfo() {
        for (Explosion value : bomb_explosion) {
            System.out.println(value.getX() + "  " + value.getY());
        }
    }
}
