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
        for (int i = 0; i < stillObjects.size(); i++) {
            if (!((stillObjects.get(i).getClass().equals(Grass.class))
                    || stillObjects.get(i).getClass().equals(Portal.class)
                    || stillObjects.get(i).getClass().equals(Flame.class))) {
                Entity object = stillObjects.get(i);
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
        for (int i = 0; i < collisions.size(); i++) {
            int Left_object = collisions.get(i).getX();
            int Right_object = collisions.get(i).getX() + collisions.get(i).getW();
            int Top_object = collisions.get(i).getY();
            int Bottom_object = collisions.get(i).getY() + collisions.get(i).getH();
            if ((Bottom_player <= Top_object
                    || Top_player >= Bottom_object
                    || Right_player <= Left_object
                    || Left_player >= Right_object) == false) {
                return true;
            }
        }
        return false;
    }

    public void update(List<Entity> stillObjects){
          setRectCollisions(stillObjects);
    }
}
