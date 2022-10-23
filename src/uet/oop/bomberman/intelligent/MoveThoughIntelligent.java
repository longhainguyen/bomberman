package uet.oop.bomberman.intelligent;

import uet.oop.bomberman.collisions.Collision;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.util.*;

public class MoveThoughIntelligent extends MoveIntelligent{
    public MoveThoughIntelligent() {
        this.speed_entity = 1;
    }

    @Override
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

                if (collision.checkCollisionsOfBrick(start)) continue;

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
}
