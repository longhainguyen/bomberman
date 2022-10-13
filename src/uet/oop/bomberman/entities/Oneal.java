package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.util.*;

public class Oneal extends Entity {
    private List<Rect> listRectHaveToMove = new ArrayList<>();
    private int animation_time = 12;
    private int animate = 0;

    private Collision collision = new Collision();
    private Rect rect;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        rect = new Rect(this.x, this.y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        this.goRight = true;
        this.goLeft = false;
    }

    private void updateAnimation() {
        this.animate++;
        if (animate > animation_time - 1) {
            animate = 0;
        }
        if (goRight) {
            Image image_goRight = Sprite.movingSprite(Sprite.oneal_right1,
                    Sprite.oneal_right2, Sprite.oneal_right2, animate, animation_time).getFxImage();
            this.setImg(image_goRight);
        } else if (goLeft) {
            Image image_goLeft = Sprite.movingSprite(Sprite.oneal_left1,
                    Sprite.oneal_left2, Sprite.oneal_left3, animate, animation_time).getFxImage();
            this.setImg(image_goLeft);
        }
    }

    @Override
    public void update() {
        this.find_road();
        for(Rect rect1 : listRectHaveToMove) {
            System.out.println(rect1.getX() + "---" + rect1.getY());
        }
        System.out.println("---------");
        this.updateAnimation();
        collision.setRectCollisions(Map.stillEntity);
        collision.update(Map.stillEntity);
        rect.setX(x);
        rect.setY(y);
        if(listRectHaveToMove.size() >= 2)
            this.move();
    }

    private void find_road() {
        boolean[][] visited = new boolean[Map.widthOfMap][Map.heightOfMap];
        Queue<Rect> pos = new LinkedList<>();
        Rect rect1 = new Rect(rect.getX()/32 * 32, rect.getY()/32 * 32, rect.getW(), rect.getH());
        pos.add(rect1);
        visited[rect1.getX()][rect1.getY()] = true;
        java.util.Map<Rect,Rect> parentMap = new HashMap<>();
        Rect start = rect1;
        parentMap.put(start, null);
        while (!pos.isEmpty()) {
            start = pos.poll();
            if(start.getX() == 32 && start.getY() == 32) {
                break;
            }

            int[] dr = new int[]{-32, +32, 0, 0};
            int[] dc = new int[]{0, 0, 32, -32};
            for(int i = 0; i < 4; i++) {

                int cc = start.getX() + dr[i];
                int rr = start.getY() + dc[i];

                if (cc < 0 || rr < 0) continue;
                if (cc >= Map.widthOfMap || rr >= Map.heightOfMap) continue;


                Rect rect2 = new Rect(cc, rr, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);

                if(collision.checkCollisions(start)) continue;

                if(!visited[rect2.getX()][rect2.getY()]) {
                    pos.add(rect2);
                    visited[rect2.getX()][rect2.getY()] = true;
                    parentMap.put(rect2,start);
                }
            }
        }
        List<Rect> path = new ArrayList<>();
        Rect curr = start;
        while (curr != null) {
            path.add(0,curr);
            curr = parentMap.get(curr);
        }
        listRectHaveToMove = path;
    }

    @Override
    public void move() {
        Rect rect1 = listRectHaveToMove.get(1);
        Rect rect2 = new Rect(rect.getX()/32 * 32, rect.getY()/32 * 32, rect.getW(), rect.getH());
        if(rect1.getY() > rect.getY()) {
            this.y += SPEED;
        }else if(rect1.getY() < rect.getY()) {
            this.y -= SPEED;
        }
        if(rect1.getX() > rect.getX()) {
            this.x += SPEED;
        }else if(rect1.getX() < rect.getX()) {
            this.x -= SPEED;
        }

    }
}
