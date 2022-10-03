package uet.oop.bomberman.maps;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public static int mapStartX = 0;// Coordinates of Map relative to Window.
    public static int mapStartY = 0;// Coordinate of Map relative to Window.
    public static boolean goUp;// Map go up compare to window.
    public static boolean goDown;// Map go down compare to window.
    public static boolean goRight;// Map go right compare to window.
    public static boolean goLeft;// Map go left compare to window.

    public static int widthOfMap;
    public static int heightOfMap;
    public int col;
    public int row;
    public String level;
    private List<String> readFile;
    public String[][] mapCode;

    public static List<Entity> stillEntity = new ArrayList<>();//Store of stillEntity in Map.
    public static List<Entity> entitiesEntity = new ArrayList<>();//Store of entitiesEntity in Map.

    private void setReadFile(String fileName) {
        File f2 = new File(fileName);
        try {
            readFile = Files.readAllLines(f2.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    set and get map's code of level
     */
    public void setAndGetMapCode(String fileName) {
        this.setReadFile(fileName);
        String[] numbers_info = readFile.get(0).split(" ");
        level = "Level " + numbers_info[0];
        row = Integer.parseInt(numbers_info[1]);
        col = Integer.parseInt(numbers_info[2]);
        heightOfMap = row * Sprite.SCALED_SIZE;
        widthOfMap = col * Sprite.SCALED_SIZE;
        mapCode = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mapCode[i][j] = readFile.get(i + 1).charAt(j) + "";
            }
        }
    }

    /**
     * Create entity of map and initialization for stillEntity, entitiesEntity of map.
     * @param fileName file name
     * @param entities entities
     * @param stillObjects stillObjects
     * @param player player
     */
    public void creatMap2(String fileName, List<Entity> entities
            , List<Entity> stillObjects, Bomber player) {
        setAndGetMapCode(fileName);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                Entity object;
                Entity grass;
                /*
                        At positions where the frame is not "Wall", we will creat an addition Grass's frame below it.
                 */
                switch (this.mapCode[i][j]) {
                    case "#":
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case "*":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        object = new Brick(j, i, Sprite.brick_exploded.getFxImage());
                        break;
                    case "x":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        object = new Portal(j, i, Sprite.portal.getFxImage());
                        break;
                    case "p":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        player.setX(j * Sprite.SCALED_SIZE);
                        player.setY(i * Sprite.SCALED_SIZE);
                        player.setImg(Sprite.player_right.getFxImage());
                        object = player;
                        break;
                    case "1":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        break;
                    case "2":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        object = new Balloon(j, i, Sprite.oneal_right1.getFxImage());
                        break;
                    case "b":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        object = new Bomb(j, i, Sprite.bomb_exploded.getFxImage());
                        break;
                    case "f":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        object = new Flame(j, i, Sprite.powerup_flames.getFxImage());
                        break;
                    case "s":
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        stillEntity.add(grass);
                        object = new Speed(j, i, Sprite.powerup_speed.getFxImage());
                        break;
                    default:
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                if (object.getClass().equals(Wall.class)
                        || object.getClass().equals(Brick.class)
                        || object.getClass().equals(Portal.class)
                        || object.getClass().equals(Grass.class)) {
                    stillObjects.add(object);
                    stillEntity.add(object);
                } else {
                    entities.add(object);
                    if (!object.getClass().equals(Bomber.class))
                        entitiesEntity.add(object);
                }
            }
        }
    }

    /**
     * Update Map when player move.
     */
    public void update() {
        if (goRight) {
            distanceX();
        }
        if (goLeft) {
            distanceX();
        }
        if (goUp) {
            distanceY();
        }
        if (goDown) {
            distanceY();
        }

    }

    /**
     * Move Entity of map follow mapStartX.
     */
    private void distanceY() {
        int distance = stillEntity.get(0).getY() - Map.mapStartY;
        for (Entity entity : stillEntity) {
            entity.setY(entity.getY() - distance);
        }
        for (Entity entity : entitiesEntity) {
            entity.setY(entity.getY() - distance);
        }
    }

    /**
     * Move entity of map follow mapStartX.
     */
    private void distanceX() {
        int distance = stillEntity.get(0).getX() - Map.mapStartX;
        for (Entity entity : stillEntity) {
            entity.setX(entity.getX() - distance);
        }
        for (Entity entity : entitiesEntity) {
            entity.setX(entity.getX() - distance);
        }
    }
}
