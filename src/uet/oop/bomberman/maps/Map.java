package uet.oop.bomberman.maps;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.*;

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
    public static int col;
    public static int row;
    public String level;
    public String fileName;
    private List<String> readFile = new ArrayList<>();
    public static String[][] mapCode;

    public static List<Entity> stillEntity = new ArrayList<>();//Store of stillEntity in Map.

    public static List<Entity> grassMap = new ArrayList<>();//Store of grass in Map.
    public static List<Item> powerMap = new ArrayList<>();//Store of powerup in Map.
    public static List<Entity> entitiesEntity = new ArrayList<>();//Store of entitiesEntity in Map.

    public Map() {
    }

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
     *
     * @param entities     entities
     * @param stillObjects stillObjects
     * @param player       player
     */

// Set map and character
    public void creatMap2(List<Entity> entities
            , List<Entity> stillObjects, List<Item> powerup,
                          List<Entity> grass, Bomber player) {
        setAndGetMapCode(fileName);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                Entity object;
                Entity grass_object = new Grass(j, i, Sprite.grass.getFxImage());
                Item power;

                /*
                        At positions where the frame is not "Wall", we will creat an addition Grass's frame below it.
                 */

                switch (this.mapCode[i][j]) {
                    case "#":
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case "*":
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "x":
                        power = new Portal(j, i, Sprite.portal.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        BombermanGame.portal = power;
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "p":
                        player.setBomber(j, i, Sprite.player_right.getFxImage());
                        BombermanGame.fake_player = player;
                        object = player;
                        break;
                    case "1":
                        object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "2":
                        object = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "b":
                        power = new Multibomb(j, i, Sprite.powerup_bombs.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "f":
                        power = new Flame(j, i, Sprite.powerup_flames.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "s":
                        power = new Speed(j, i, Sprite.powerup_speed.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "w":
                        power = new Wallpass(j, i, Sprite.powerup_wallpass.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "F":
                        power = new Firepass(j, i, Sprite.powerup_flamepass.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "B":
                        power = new Bombpass(j, i, Sprite.powerup_bombpass.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "r":
                        power = new Remotebomb(j, i, Sprite.powerup_detonator.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "?":
                        power = new Secret(j, i, Sprite.powerup_secret.getFxImage());
                        powerup.add(power);
                        powerMap.add(power);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    case "d":
                        object = new Doll(j, i, Sprite.doll_left1.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "m":
                        object = new MinVo(j,i,Sprite.minvo_right1.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "k":
                        object = new KonDoria(j,i,Sprite.kondoria_right1.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "o":
                        object = new OVape(j,i,Sprite.oVapeRight.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "a":
                        object = new Pass(j,i,Sprite.passRight.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "i":
                        object = new MinVoBlack(j,i,Sprite.minVoBlackRight.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    case "j":
                        object = new MinVoGreen(j,i,Sprite.minVoGreenRight.getFxImage());
                        BombermanGame.enemiesNumber++;
                        break;
                    default:
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                grass.add(grass_object);
                grassMap.add(grass_object);
                if (object.getClass().equals(Wall.class)
                        || object.getClass().equals(Brick.class)
                ) {
                    stillObjects.add(object);
                    stillEntity.add(object);
                } else {
                    if (!(object instanceof Grass)) {
                        entities.add(object);
                    }
                    if (!object.getClass().equals(Bomber.class) && !object.getClass().equals(Grass.class))
                        entitiesEntity.add(object);
                }
            }
        }
    }

    /**
     * Update Map when player move.
     */
    public void update() {
        if (Map.widthOfMap > BombermanGame.WINDOW_WIDTH) {
            if (goRight) {
                distanceX();
            }
            if (goLeft) {
                distanceX();
            }
        }
        if (Map.heightOfMap > BombermanGame.WINDOW_HEIGHT) {
            if (goUp) {
                distanceY();
            }
            if (goDown) {
                distanceY();
            }
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
        for (Entity entity : grassMap) {
            entity.setY(entity.getY() - distance);
        }
        for (Item entity : powerMap) {
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
        for (Entity entity : grassMap) {
            entity.setX(entity.getX() - distance);
        }
        for (Item entity : powerMap) {
            entity.setX(entity.getX() - distance);
        }
    }

    public void deleteMap() {
        this.readFile.removeAll(readFile);
        entitiesEntity.removeAll(entitiesEntity);
        stillEntity.removeAll(stillEntity);
        grassMap.removeAll(grassMap);
        powerMap.removeAll(powerMap);
    }
}
