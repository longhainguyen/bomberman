package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.itemType;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.musicItem;
import uet.oop.bomberman.sounds.Band;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {
    private Image image_current;

    public static final int acceleration_time = 100;

    private int speed_clock = 0;// use to count time of speed power

    private int flame_clock = 0;// use to connt time of flame power

    private int multibomb_clock = 0;// use to count time of multibomb

    private int remote_clock = 0; // use to count time of remote bomb

    public boolean isRemote = false;

    public boolean is_press_B = false;

    public boolean is_out_of_time_B = false;

    public int press_B_number = 0;

    private int wallpass_clock = 0; // use to count the time of wallpass.

    public boolean isWallpass = false;

    private int survival_clock = 0;// use to count the time of survival.

    public boolean isSurvival = false;

    private int bombpass_clock = 0;// use to count the time of bombpass.

    private musicItem deadSound = new musicItem(1, 50, musicItem.deadSound);

    public boolean checkDie = false;

    public boolean isBombpass = false;
    public boolean is_check_out_of_bomb = false;

    public boolean is_almost_die = false;

    public int almost_die_time = 21;

    public int current_almost_die_time = 0;
    private ArrayList<itemType> storePower = new ArrayList<>();

    public int animate = 0;
    public int posXInMap;
    public int posYInMap;

    private int posX;

    private int posY;

    private static final int vec_bom = 5;
    public static final int width = 21;
    public static final int height = 31;

    public static final int animation_time = 12;
    public static final int max_die_time = 30;

    private boolean turn_right;
    private boolean turn_left;
    private boolean turn_up;
    private boolean turn_down;

    //private boolean isDie;
    private int isDie_time;

    private int heart;

    public void setFlame_clock(int flame_clock) {
        this.flame_clock = flame_clock;
    }

    public void setMultibomb_clock(int multibomb_clock) {
        this.multibomb_clock = multibomb_clock;
    }

    public void setRemote_clock(int remote_clock) {
        this.remote_clock = remote_clock;
    }

    public void setWallpass_clock(int wallpass_clock) {
        this.wallpass_clock = wallpass_clock;
    }

    public void setSurvival_clock(int survival_clock) {
        this.survival_clock = survival_clock;
    }

    public void setBombpass_clock(int bombpass_clock) {
        this.bombpass_clock = bombpass_clock;
    }

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

    public boolean isDie() {
        return isDie;
    }

    public void setDie(boolean die) {
        isDie = die;
    }

    public int getIsDie_time() {
        return isDie_time;
    }

    public void setIsDie_time(int isDie_time) {
        this.isDie_time = isDie_time;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }


    public int getSpeed_clock() {
        return speed_clock;
    }

    public void setSpeed_clock(int speed_clock) {
        this.speed_clock = speed_clock;
    }

    public ArrayList<itemType> getStorePower() {
        return storePower;
    }

    public void setStorePower(ArrayList<itemType> storePower) {
        this.storePower = storePower;
    }

    public void addType(itemType type) {
        storePower.add(type);
    }

    private Collision Bomber_collision = new Collision();
    public Rect Bomber_rect;//Change public to easy use

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.image_current = img;
        entities_rect = new Rect(x * width, y * height, width, height);
        posXInMap = x * Sprite.SCALED_SIZE;
        posYInMap = y * Sprite.SCALED_SIZE;
        this.turn_down = false;
        this.turn_left = false;
        this.turn_up = false;
        this.turn_right = false;
        this.isDie = false;
        this.isDie_time = 0;
        this.heart = 3;
        speed_clock = 0;
    }

    public void setBomber(int x, int y, Image img) {
        this.x = x * 32;
        this.y = y * 32;
        this.img = img;
        entities_rect = new Rect(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, width, height);
        this.isDie = false;
        this.isDie_time = 0;
        this.heart = 3;
    }

    public Collision getEntity_collision() {
        return entity_collision;
    }

    public void setBomberRectCollisions(List<Entity> stillObjects) {
        entity_collision.setRectCollisions(stillObjects);
    }

    public Image getImage_current() {
        return image_current;
    }

    @Override
    public void update() {
        this.move();
        this.Speed();
        this.Flame();
        this.Multibomb();
        this.Controlbomb();
        this.Wallpass();
        this.Survival();
        this.Bombpass();
        entity_collision.update(Map.stillEntity, Map.entitiesEntity);
    }

    /**
     * check speed item.
     */
    public void Speed() {
        for (int i = 0; i < storePower.size(); i++) {
            if (storePower.get(i).equals(itemType.Speed)) {
                if (speed_clock < acceleration_time) {
                    speed_clock++;
                    SPEED = 4;
                } else {
                    Band.textPower = Band.textPower.replace("Speed ", "");
                    Band.detailPower.setText(Band.textPower);
                    speed_clock = 0;
                    SPEED = 2;
                    storePower.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * check flame item.
     */
    public void Flame() {
        for (int i = 0; i < storePower.size(); i++) {
            if (storePower.get(i).equals(itemType.Flame)) {
                if (flame_clock < acceleration_time) {
                    flame_clock++;
                    Bomb.max_length_explosion = 5;
                } else {
                    Band.textPower = Band.textPower.replace("Flame ", "");
                    Band.detailPower.setText(Band.textPower);
                    flame_clock = 0;
                    Bomb.max_length_explosion = 3;
                    storePower.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * check multibomb.
     */
    public void Multibomb() {
        for (int i = 0; i < storePower.size(); i++) {
            if (storePower.get(i).equals(itemType.Multibomb)) {
                if (multibomb_clock < 2 * acceleration_time) {
                    multibomb_clock++;
                    BombermanGame.bomb_max = 2;
                } else {
                    Band.textPower = Band.textPower.replace("Bombs ", "");
                    Band.detailPower.setText(Band.textPower);
                    multibomb_clock = 0;
                    BombermanGame.bomb_max = 1;
                    storePower.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * check control bomb.
     */
    public void Controlbomb() {
        for (int i = 0; i < storePower.size(); i++) {
            if (storePower.get(i).equals(itemType.Remote)) {
                if (remote_clock < 2 * acceleration_time) {
                    remote_clock++;
                } else {
                    System.out.println("end of period");
                    Band.textPower = Band.textPower.replace("Remote ", "");
                    Band.detailPower.setText(Band.textPower);
                    isRemote = false;
                    is_out_of_time_B = true;
                    BombermanGame.fake_player.is_press_B = false;
                    remote_clock = 0;
                    storePower.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * check wallpass.
     */
    public void Wallpass() {
        for (int i = 0; i < storePower.size(); i++) {
            if (storePower.get(i).equals(itemType.Wallpass)) {
                if (wallpass_clock < acceleration_time) {
                    wallpass_clock++;
                } else {
                    Band.textPower = Band.textPower.replace("Wallpass ", "");
                    Band.detailPower.setText(Band.textPower);
                    wallpass_clock = 0;
                    isWallpass = false;
                    storePower.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * check survival of bomber.
     */
    public void Survival() {
        for (int i = 0; i < storePower.size(); i++) {
            if (storePower.get(i).equals(itemType.Firepass)) {
                if (survival_clock < 2 * acceleration_time) {
                    survival_clock++;
                } else {
                    Band.textPower = Band.textPower.replace("Firepass ", "");
                    Band.detailPower.setText(Band.textPower);
                    survival_clock = 0;
                    isSurvival = false;
                    storePower.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * check bombpass.
     */
    public void Bombpass() {
        for (int i = 0; i < storePower.size(); i++) {
            if (storePower.get(i).equals(itemType.Bombpass)) {
                if (bombpass_clock < 2 * acceleration_time) {
                    bombpass_clock++;
                } else {
                    Band.textPower = Band.textPower.replace("Bombpass ", "");
                    Band.detailPower.setText(Band.textPower);
                    bombpass_clock = 0;
                    isBombpass = false;
                    storePower.remove(i);
                    i--;
                }
            }
        }
    }


    public void deadSound() {
        this.deadSound.playSound();
    }

    @Override
    public void move() {
        if (!this.isSurvival) {
            if (entity_collision.checkCollisionsOfentities(entities_rect)) {
                heart--;
                if (heart >= 0) {
                    Band.heart.setText(String.valueOf(heart));
                }
                if (heart >= 1) {
                    this.addType(itemType.Firepass);
                    this.isSurvival = true;
                    this.is_almost_die = true;
                }
                if (heart == 0) {
                    this.setDie(true);
                    BombermanGame.gameMusic.getMediaPlayer().pause();
                    if (!BombermanGame.effectMute) {
                        deadSound();
                    }
                    if (BombermanGame.gameMusic.isIs_playing()) {
                        BombermanGame.root.getChildren().remove(Band.musicImgae.get(0));
                        ImageView temp = Band.musicImgae.get(0);
                        Band.musicImgae.set(0, Band.musicImgae.get(3));
                        Band.musicImgae.set(3, temp);
                        BombermanGame.root.getChildren().add(Band.musicImgae.get(0));
                        BombermanGame.gameMusic.setIs_playing(false);
                    }
                }

            }
        }
        if (isDie && !is_almost_die) {
            isDie_time++;
            if (this.getIsDie_time() == 1) {
                this.setHeart(0);
            }
            if (isDie_time > max_die_time - 1) {
                isDie = false;
            }
            Image die_image = Sprite.movingSprite(Sprite.player_dead1,
                    Sprite.player_dead2,
                    Sprite.player_dead3, isDie_time, max_die_time).getFxImage();
            this.setImg(die_image);
        } else if (!isDie && is_almost_die) {
            current_almost_die_time ++;
            Image die_image = Sprite.movingSprite(Sprite.player_dead1,
                    Sprite.player_dead2,
                    Sprite.player_dead3, isDie_time, max_die_time).getFxImage();
            this.setImg(die_image);
            if (current_almost_die_time > almost_die_time - 1) {
                is_almost_die = false;
                current_almost_die_time = 0;
                this.setImg(image_current);
            }
        } else {
            this.animate++;
            if (animate > animation_time - 1) {
                animate = 0;
            }
            if (goUp && animate > 0) {
                posYInMap -= SPEED;
                y -= SPEED;
                entities_rect.setY(y);
                if (entity_collision.checkCollisions(entities_rect)) {
                    y += SPEED;
                    posYInMap += SPEED;
                }
                if (!(posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                        && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2)) {
                    entities_rect.setY(y);
                }

                if (posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                        && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                    y += SPEED;
                    if (entity_collision.checkCollisions(entities_rect)) {
                        y -= SPEED;
                        entities_rect.setY(y + SPEED);
                    } else entities_rect.setY(y);

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
                entities_rect.setY(y);
                if (entity_collision.checkCollisions(entities_rect)) {
                    y -= SPEED;
                    posYInMap -= SPEED;
                }
                if (!(posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                        && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2)) {
                    entities_rect.setY(y);
                }
                if (posYInMap >= BombermanGame.WINDOW_HEIGHT / 2
                        && posYInMap <= Map.heightOfMap - BombermanGame.WINDOW_HEIGHT / 2) {
                    y -= SPEED;
                    if (entity_collision.checkCollisions(entities_rect)) {
                        y += SPEED;
                        entities_rect.setY(y - SPEED);
                    } else
                        entities_rect.setY(y);
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
                entities_rect.setX(x);
                if (entity_collision.checkCollisions(entities_rect)) {
                    x += SPEED;
                    posXInMap += SPEED;
                }
                if (!(posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                        && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2)) {
                    entities_rect.setX(x);
                }

                if (posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                        && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                    x += SPEED;
                    if (entity_collision.checkCollisions(entities_rect)) {
                        x -= SPEED;
                        entities_rect.setX(x + SPEED);
                    } else
                        entities_rect.setX(x);
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
                entities_rect.setX(x);
                if (entity_collision.checkCollisions(entities_rect)) {
                    posXInMap -= SPEED;
                    x -= SPEED;
                }
                if (!(posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                        && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2)) {
                    entities_rect.setX(x);
                }
                if (posXInMap >= BombermanGame.WINDOW_WIDTH / 2
                        && posXInMap <= Map.widthOfMap - BombermanGame.WINDOW_WIDTH / 2) {
                    x -= SPEED;
                    if (entity_collision.checkCollisions(entities_rect)) {
                        x += SPEED;
                        entities_rect.setX(x - SPEED);
                    } else
                        entities_rect.setX(x);
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
}