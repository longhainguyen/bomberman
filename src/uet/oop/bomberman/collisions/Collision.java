package uet.oop.bomberman.collisions;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.maps.Map;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

public class Collision {
    private List<Rect> collisionsOfBrick = new ArrayList<>();
    private List<Rect> collisions = new ArrayList<>();
    private List<Rect> collisionsOfentities = new ArrayList<>();

    private Rect is_die_rect;
    public static final int width = 32;
    public static final int height = 32;


    public void setCollisionsOfBrick(List<Entity> stillObjects) {
        List<Rect> temp = new ArrayList<>();
        if (!BombermanGame.fake_player.isWallpass) {
            for (int i = 0; i < stillObjects.size(); i++) {
                if (stillObjects.get(i) instanceof Bomb) {
                    if (!((Bomb) stillObjects.get(i)).is_out_of_bomber || BombermanGame.fake_player.isBombpass) {
                        stillObjects.get(i).setEntities_rect(new Rect(stillObjects.get(i).getX(), stillObjects.get(i).getY(), width, height));
                        continue;
                    }
                }
                if (!stillObjects.get(i).getClass().equals(Brick.class)) {
                    Entity object = stillObjects.get(i);
                    temp.add(new Rect(object.getX(), object.getY(), width, height));
                    stillObjects.get(i).setEntities_rect(new Rect(object.getX(), object.getY(), width, height));
                }
            }
        }
        collisionsOfBrick = temp;
    }
    public void setRectCollisions(List<Entity> stillObjects) {
        List<Rect> temp = new ArrayList<>();
        //if (!BombermanGame.fake_player.isWallpass) {
            for (int i = 0; i < stillObjects.size(); i++) {
                if (stillObjects.get(i) instanceof Bomb) {
                    if (!((Bomb) stillObjects.get(i)).is_out_of_bomber || BombermanGame.fake_player.isBombpass) {
                        stillObjects.get(i).setEntities_rect(new Rect(stillObjects.get(i).getX(), stillObjects.get(i).getY(), width, height));
                        continue;
                    }
                }
                if (!((stillObjects.get(i).getClass().equals(Grass.class)))) {
                    Entity object = stillObjects.get(i);
                    temp.add(new Rect(object.getX(), object.getY(), width, height));
                    stillObjects.get(i).setEntities_rect(new Rect(object.getX(), object.getY(), width, height));
                }
            }
       // }
        collisions = temp;
    }

    public void setRectCollisionsOfentities(List<Entity> entities) {
        List<Rect> temp = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomber) {
                entities.get(i).setEntities_rect(new Rect(entities.get(i).getX(),
                        entities.get(i).getY(), Bomber.width, Bomber.height));
                continue;
            }
            if (!(entities.get(i).getClass().equals(Bomber.class)
                    || entities.get(i).getClass().equals(Bomb.class))) {
                Entity object = entities.get(i);
                temp.add(new Rect(object.getX(), object.getY(), width, height));
                entities.get(i).setEntities_rect(new Rect(entities.get(i).getX(), entities.get(i).getY(), width, height));
            }
        }
        collisionsOfentities = temp;
    }

    public boolean checkCollisions(Rect playerRect) {
        int Left_player = playerRect.getX();
        int Right_player = playerRect.getX() + playerRect.getW();
        int Top_player = playerRect.getY();
        int Bottom_player = playerRect.getY() + playerRect.getH();
        for (int i = 0; i < collisions.size(); i++) {
            int Left_object = collisions.get(i).getX();
            int Right_object = collisions.get(i).getX() + collisions.get(i).getW();
            int Top_object = collisions.get(i).getY();
            int Bottom_object = collisions.get(i).getY() + collisions.get(i).getH();
            if (!(Bottom_player <= Top_object
                    || Top_player >= Bottom_object
                    || Right_player <= Left_object
                    || Left_player >= Right_object)) {
                is_die_rect = (new Rect(Left_object, Top_object, width, height));
                return true;
            }
        }
        return false;
    }

    /**
     * Only check collision with brick
     * @param playerRect rect
     * @return true or false
     */
    public boolean checkCollisionsOfBrick(Rect playerRect) {
        int Left_player = playerRect.getX();
        int Right_player = playerRect.getX() + playerRect.getW();
        int Top_player = playerRect.getY();
        int Bottom_player = playerRect.getY() + playerRect.getH();
        for (Rect rect : collisionsOfBrick) {
            int Left_object = rect.getX();
            int Right_object = rect.getX() + rect.getW();
            int Top_object = rect.getY();
            int Bottom_object = rect.getY() + rect.getH();
            if (!(Bottom_player <= Top_object
                    || Top_player >= Bottom_object
                    || Right_player <= Left_object
                    || Left_player >= Right_object)) {
                is_die_rect = (new Rect(Left_object, Top_object, width, height));
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionsOfentities(Rect playerRect) {
        int Left_player = playerRect.getX();
        int Right_player = playerRect.getX() + playerRect.getW();
        int Top_player = playerRect.getY();
        int Bottom_player = playerRect.getY() + playerRect.getH();
        for (int i = 0; i < collisionsOfentities.size(); i++) {
            int Left_object = collisionsOfentities.get(i).getX();
            int Right_object = collisionsOfentities.get(i).getX() + collisionsOfentities.get(i).getW();
            int Top_object = collisionsOfentities.get(i).getY();
            int Bottom_object = collisionsOfentities.get(i).getY() + collisionsOfentities.get(i).getH();
            if (!(Bottom_player <= Top_object
                    || Top_player >= Bottom_object
                    || Right_player <= Left_object
                    || Left_player >= Right_object)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkcollision(Rect playerRect, Rect other) {
        int Left_player = playerRect.getX();
        int Right_player = playerRect.getX() + playerRect.getW();
        int Top_player = playerRect.getY();
        int Bottom_player = playerRect.getY() + playerRect.getH();
        int Left_object = other.getX();
        int Right_object = other.getX() + other.getW();
        int Top_object = other.getY();
        int Bottom_object = other.getY() + other.getH();
        if (!(Bottom_player <= Top_object
                || Top_player >= Bottom_object
                || Right_player <= Left_object
                || Left_player >= Right_object)) {
            return true;
        }
        return false;
    }

    public Rect getIs_die_rect() {
        return is_die_rect;
    }

    public void setIs_die_rect(Rect is_die_rect) {
        this.is_die_rect = is_die_rect;
    }

    public void update(List<Entity> stillObjects, List<Entity> entities) {
        setRectCollisions(stillObjects);
        setRectCollisionsOfentities(entities);
        setCollisionsOfBrick(stillObjects);//Here, i add this collision to entity konDoria!
    }

    public List<Rect> getCollisions() {
        return collisions;
    }

    public void setCollisions(List<Rect> collisions) {
        this.collisions = collisions;
    }

    public List<Rect> getCollisionsOfentities() {
        return collisionsOfentities;
    }

    public void setCollisionsOfentities(List<Rect> collisionsOfentities) {
        this.collisionsOfentities = collisionsOfentities;
    }

}
