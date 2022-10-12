package uet.oop.bomberman.bfs;

import uet.oop.bomberman.maps.Point;

import java.util.*;

public class BFS {
    private final boolean[][] visit;

    public BFS(boolean[][] visit) {
        this.visit = visit;
    }

    public ArrayList<Point> get_neighbours(Point point, String[][] grid) {
        ArrayList<Point> neighbours = new ArrayList<>();

        int R = grid.length;
        int C = grid[0].length;

        int[] dr = new int[]{-1, +1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int rr = point.getY() + dr[i];
            int cc = point.getX() + dc[i];

            if (rr < 0 || cc < 0) continue;
            if (rr >= R || cc >= C) continue;

            if (Objects.equals(grid[rr][cc], "#")) continue;
            if (Objects.equals(grid[rr][cc], "*")) continue;

            Point point1 = new Point(cc, rr);
            neighbours.add(point1);
        }
        return neighbours;
    }

    public List<Point> find_path_bfs(Point start, Point end, String[][] grid) {

        Queue<Point> queue = new LinkedList<>();
        List<Point> path = new ArrayList<>();

        queue.add(start);
        visit[start.getY()][start.getX()] = true;
        Map<Point,Point> parentMap = new HashMap<>();

        Point point = start;
        while (!queue.isEmpty()) {
            point = queue.poll();

            if (point.equals(end)) break;

            ArrayList<Point> adj_point = get_neighbours(point, grid);
            for (Point p : adj_point) {
                if (!Objects.equals(grid[p.getY()][p.getX()], "#")
                        && !Objects.equals(grid[p.getY()][p.getX()], "*")
                        && !visit[p.getY()][p.getX()]) {
                    queue.add(p);
                    parentMap.put(p,point);
                    visit[p.getY()][p.getX()] = true;
                }
            }
        }

        Point cur = point;
        while (cur != null) {
            path.add(0,cur);
            cur = parentMap.get(cur);
        }
        return path;
    }

    public static void main(String[] args) {
        uet.oop.bomberman.maps.Map map = new uet.oop.bomberman.maps.Map();
        map.setAndGetMapCode("res/levels/Level1.txt");
        boolean[][] visit = new boolean[uet.oop.bomberman.maps.Map.row][uet.oop.bomberman.maps.Map.col];
        for (boolean[] booleans : visit) {
            Arrays.fill(booleans, false);
        }
        BFS bfs = new BFS(visit);
        Point start = new Point(2,1);
        Point end = new Point(2,4);
        List<Point> result = bfs.find_path_bfs(start,end, uet.oop.bomberman.maps.Map.mapCode);
        for(Point p : result) {
            System.out.println(p.getX() + "---" + p.getY());
        }
    }
}
