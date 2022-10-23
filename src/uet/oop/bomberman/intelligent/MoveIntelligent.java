package uet.oop.bomberman.intelligent;

import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.util.*;


public class MoveIntelligent {
    protected int speed_entity = 2;
    public boolean isCanMove;
    public List<Rect> listRectHaveToMove = null;
    public static int bomberX;
    public static int bomberY;

    public static void setBomberXY(int x, int y) {
        bomberX = x;
        bomberY = y;
    }

    public void find_road(Rect rect, Collision collision) {
        boolean[][] visited = new boolean[Map.widthOfMap][Map.heightOfMap];
        Queue<Rect> pos = new LinkedList<>();
        pos.add(rect);
        visited[rect.getX()][rect.getY()] = true;
        java.util.Map<Rect, Rect> parentMap = new HashMap<>();
        Rect start = rect;
        parentMap.put(start, null);
        while (!pos.isEmpty()) {
            start = pos.poll();
            if (this.check(start)) {
                break;
            }

            int[] dr = new int[]{-speed_entity, +speed_entity, 0, 0};
            int[] dc = new int[]{0, 0, speed_entity, -speed_entity};
            for (int i = 0; i < 4; i++) {

                int cc = start.getX() + dr[i];
                int rr = start.getY() + dc[i];

                if (cc < 0 || rr < 0) continue;
                if (cc >= Map.widthOfMap || rr >= Map.heightOfMap) continue;


                Rect rect2 = new Rect(cc, rr, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);

                if (collision.checkCollisions(start)) continue;

                if (!visited[rect2.getX()][rect2.getY()]) {
                    pos.add(rect2);
                    visited[rect2.getX()][rect2.getY()] = true;
                    parentMap.put(rect2, start);
                }
            }
        }
        Rect curr = start;
        List<Rect> path = new ArrayList<>();
        while (curr != null) {
            path.add(0, curr);
            curr = parentMap.get(curr);
        }
        listRectHaveToMove = path;
    }

    public void moveIntelligent(Entity entity) {
        if(listRectHaveToMove.size() >= 2)
        {
            Rect rect1 = listRectHaveToMove.get(1);
            if(entity.getX() != rect1.getX()  || entity.getY() != rect1.getY()) {
                if(rect1.getX() > entity.getX()) {
                    entity.setX(entity.getX() + speed_entity);
                } else if (rect1.getX() < entity.getX()) {
                    entity.setX(entity.getX() - speed_entity);
                }

                if(rect1.getY() > entity.getY()) {
                    entity.setY(entity.getY() + speed_entity);
                }else if(rect1.getY() < entity.getY()) {
                    entity.setY(entity.getY() - speed_entity);
                }
            }
        }
    }

    /**
     * Check find bomber.
     * @param start start
     * @return true if find
     */
    public boolean check(Rect start) {
        return (start.getX() <= bomberX + 10 && start.getX() >= bomberX - 10)
                && (start.getY() <= bomberY + 10 && start.getY() >= bomberY - 10);
    }

    public void setIsCanMove(Rect rect, Collision collision) {
        this.find_road(rect, collision);
        if(listRectHaveToMove.size() >= 1) {
            Rect last = listRectHaveToMove.get(listRectHaveToMove.size() - 1);
            this.isCanMove = check(last);
        }
    }
}
