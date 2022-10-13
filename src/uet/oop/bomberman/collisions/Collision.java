package uet.oop.bomberman.collisions;

import uet.oop.bomberman.entities.*;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

public class Collision {
    private List<Rect> collisions = new ArrayList<>();
    public static final int width = 32;
    public static final int height = 32;

    public void setRectCollisions(List<Entity> stillObjects) {
        List<Rect> temp = new ArrayList<>();
        for (Entity stillObject : stillObjects) {
            if (!((stillObject.getClass().equals(Grass.class))
                    || stillObject.getClass().equals(Portal.class)
                    || stillObject.getClass().equals(Flame.class))) {
                Entity object = stillObject;
                temp.add(new Rect(object.getX(), object.getY(), width, height));
            }
        }
        collisions = temp;
    }

    public boolean checkCollisions(Rect playerRect) {
        int Left_player = playerRect.getX();
        int Right_player = playerRect.getX() + playerRect.getW();
        int Top_player = playerRect.getY();
        int Bottom_player = playerRect.getY() + playerRect.getH();
        for (Rect collision : collisions) {
            int Left_object = collision.getX();
            int Right_object = collision.getX() + collision.getW();
            int Top_object = collision.getY();
            int Bottom_object = collision.getY() + collision.getH();
            if (!(Bottom_player <= Top_object
                    || Top_player >= Bottom_object
                    || Right_player <= Left_object
                    || Left_player >= Right_object)) {
                return true;
            }

        }
        return false;
    }

    public void update(List<Entity> stillObjects) {
        setRectCollisions(stillObjects);
    }
}
