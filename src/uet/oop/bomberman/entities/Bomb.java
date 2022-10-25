package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import jdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.musicItem;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {

    private int bomb_frame;
    private int bomb_number;
    public static int max_length_explosion;

    public int press_B_number = 0;

    private List<Explosion> bomb_explosion = new ArrayList<>();

    private musicItem explosionSound = new musicItem(1, 50, musicItem.explosionBomb);

    private musicItem laybombSound = new musicItem(1, 50, musicItem.layBomb);
    private int explosion_time;
    private boolean is_explode;
    private boolean go;

    public boolean is_out_of_bomber = false;

    public int positionOfwall = 0;


    private List<Explosion> temp = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.bomb_frame = 0;
        this.bomb_number = 0;
        this.explosion_time = 0;
        this.max_length_explosion = 3;
        this.is_explode = false;
    }

    public boolean checkBombcollision(Rect playerRect) {
        int Left_player = playerRect.getX();
        int Right_player = playerRect.getX() + playerRect.getW();
        int Top_player = playerRect.getY();
        int Bottom_player = playerRect.getY() + playerRect.getH();
        for (int i = 0; i < Map.stillEntity.size(); i++) {
            if (Map.stillEntity.get(i) instanceof Bomb) {
                continue;
            }
            int Left_object = Map.stillEntity.get(i).getEntities_rect().getX();
            int Right_object = Map.stillEntity.get(i).getEntities_rect().getX() + Map.stillEntity.get(i).getEntities_rect().getW();
            int Top_object = Map.stillEntity.get(i).getEntities_rect().getY();
            int Bottom_object = Map.stillEntity.get(i).getEntities_rect().getY() + Map.stillEntity.get(i).getEntities_rect().getH();
            if (!(Bottom_player <= Top_object
                    || Top_player >= Bottom_object
                    || Right_player <= Left_object
                    || Left_player >= Right_object)) {
                this.positionOfwall = Left_object;
                return true;
            }
        }
        return false;
    }


    public void setBomb(Bomber other, Image img, int virtual_distance) {
        int posx = (other.getX() / 32) * 32;
        int posy = (other.getY() / 32) * 32;
        boolean checkinside = false;
        if (other.getX() >= posx - virtual_distance
                && other.getX() + Bomber.width <= posx + Sprite.SCALED_SIZE + Bomber.width
                && posy + Sprite.SCALED_SIZE >= other.getY() + Bomber.height) {
            checkinside = true;
        }
        if (!checkinside) {
            if (other.isTurn_right()) {
                this.x = posx + virtual_distance + 32;
                this.y = posy;
            } else if (other.isTurn_left()) {
                this.x = posx + virtual_distance;
                this.y = posy;
            } else if (other.isTurn_up()) {
                this.x = posx + virtual_distance;
                this.y = posy;
            } else if (other.isTurn_down()) {
                this.x = posx + virtual_distance;
                this.y = posy + 32;
            }
            this.setEntities_rect(new Rect(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE));
        }
        this.getEntity_collision().update(Map.stillEntity, Map.entitiesEntity);
        if (checkinside || this.checkBombcollision(entities_rect)) {
            if (other.isTurn_left() && !checkinside) {
                this.x = positionOfwall + 32;
            } else if (other.isTurn_right() && !checkinside && virtual_distance != 0) {
                this.x = positionOfwall - 32;
            } else this.x = posx + virtual_distance;
            this.y = posy;
            this.setEntities_rect(new Rect(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE));
        }
        this.is_out_of_bomber = false;
        laySound();
        this.img = img;
    }

    public void setOutOfBomb() {
        if (!BombermanGame.fake_player.is_check_out_of_bomb) {
            if (!this.getEntity_collision().checkcollision(BombermanGame.fake_player.getEntities_rect(), this.getEntities_rect())) {
                is_out_of_bomber = true;
            }
        }
    }

    public boolean isIs_explode() {
        return is_explode;
    }

    public void setIs_explode(boolean is_explode) {
        this.is_explode = is_explode;
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

    public void exploSound() {
        if (!BombermanGame.effectMute) {
            explosionSound.playSound();
        }
    }

    public musicItem getExplosionSound() {
        return explosionSound;
    }

    public void setExplosionSound(musicItem explosionSound) {
        this.explosionSound = explosionSound;
    }

    public musicItem getLaybombSound() {
        return laybombSound;
    }

    public void setLaybombSound(musicItem laybombSound) {
        this.laybombSound = laybombSound;
    }

    public void laySound() {
        if (!BombermanGame.effectMute) {
            laybombSound.playSound();
        }
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


    public void set_stillobject_die(Rect object, List<Entity> stillObjects) {
        for (Entity value : stillObjects) {
            if (value.getEntities_rect().getX() == object.getX() && value.getEntities_rect().getY() == object.getY() && value instanceof Brick) {
                value.setDie(true);
            }
        }
    }

    @Override
    public void update() {
        this.move();
        this.setOutOfBomb();
    }

    public void setbomb_explosion(List<Entity> stillObjects, List<Entity> entities) {
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
                set_stillobject_die(change.getExplosion_collison().getIs_die_rect(), stillObjects);
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
                set_stillobject_die(change.getExplosion_collison().getIs_die_rect(), stillObjects);
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
                set_stillobject_die(change.getExplosion_collison().getIs_die_rect(), stillObjects);
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
                set_stillobject_die(change.getExplosion_collison().getIs_die_rect(), stillObjects);
                break;
            }
        }
        if (left != 0) {
            temp.get(left).setExplosion(temp.get(left).getX(), temp.get(left).getY(),
                    Sprite.explosion_horizontal_left_last.getFxImage());
            temp.get(left).setPri_frame(0);
        }
        if (right != 0) {
            temp.get(right).setExplosion(temp.get(right).getX(), temp.get(right).getY(),
                    Sprite.explosion_horizontal_right_last.getFxImage());
            temp.get(right).setPri_frame(2);
        }
        if (up != 0) {
            temp.get(up).setExplosion(temp.get(up).getX(), temp.get(up).getY(),
                    Sprite.explosion_vertical_top_last.getFxImage());
            temp.get(up).setPri_frame(4);
        }
        if (down != 0) {
            temp.get(down).setExplosion(temp.get(down).getX(), temp.get(down).getY(),
                    Sprite.explosion_vertical_down_last.getFxImage());
            temp.get(down).setPri_frame(5);
        }

        setBomb_explosion(temp);
        for (Entity value : entities) {
            if (!(value instanceof Bomber) && !(value instanceof Bomb)) {
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).getExplosion_collison().checkcollision(temp.get(i).getExplosion_rect(), value.getEntities_rect())) {
                        value.setDie(true);
                        break;
                    }
                }
            }
        }

    }


    public void addEntities(List<Entity> entities) {
        entities.addAll(bomb_explosion);
    }

    public void getinfo() {
        for (Explosion value : bomb_explosion) {
            System.out.println(value.getX() + "  " + value.getY());
        }
    }
}