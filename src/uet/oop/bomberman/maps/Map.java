package uet.oop.bomberman.maps;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class Map {
    public int col;
    public int row;
    private String level;
    private List<String> readFile;
    public String[][] mapCode;

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
        row = Integer.parseInt(numbers_info[1]);
        col = Integer.parseInt(numbers_info[2]);
        mapCode = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mapCode[i][j] = readFile.get(i + 1).charAt(j) + "";
            }
        }
    }
// Set map and character
    public void creatMap2(String fileName, List<Entity> entities
            , List<Entity> stillObjects, Bomber player) {
        setAndGetMapCode(fileName);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                Entity object;
                Entity grass;
                switch (this.mapCode[i][j]) {
                    case "#":
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case "*":
                        object = new Brick(j, i, Sprite.brick_exploded.getFxImage());
                        break;
                    case "x":
                        object = new Portal(j, i, Sprite.portal.getFxImage());
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        break;
                    case "p":
                        player.setBomber(j, i, Sprite.player_right.getFxImage());
                        object = player;
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        break;
                    case "1":
                        object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        break;
                    case "2":
                        object = new Balloon(j, i, Sprite.oneal_right1.getFxImage());
                        grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        break;
                    case "b":
                        object = new Bomb(j, i, Sprite.bomb_exploded.getFxImage());
                        break;
                    case "f":
                        object = new Flame(j, i, Sprite.powerup_flames.getFxImage());
                        break;
                    case "s":
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
                } else {
                    entities.add(object);
                }
            }
        }
    }

}
