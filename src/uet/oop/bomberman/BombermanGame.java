package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
        creatMap2("res/levels/Level1.txt");

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void creatMap2(String fileName) {
        Map map = new Map();
        map.setAndGetMapCode(fileName);
        for (int i = 0; i < map.row; i++) {
            for (int j = 0; j < map.col; j++) {
                Entity object;
                switch (map.mapCode[i][j]) {
                    case "#":
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case "*":
                        object = new Brick(j, i, Sprite.brick_exploded.getFxImage());
                        break;
                    case "x":
                        object = new Portal(j, i, Sprite.portal.getFxImage());
                        break;
                    case "p":
                        object = new Bomber(j, i, Sprite.player_right.getFxImage());
                        break;
                    case "1":
                        object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        break;
                    case "2":
                        object = new Balloon(j, i, Sprite.oneal_right1.getFxImage());
                        break;
                    case "b":
                        object = new Bomb(j ,i, Sprite.bomb_exploded.getFxImage());
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
                if(object.getClass().equals(Wall.class)
                        || object.getClass().equals(Brick.class)
                        ||object.getClass().equals(Portal.class)) {
                    stillObjects.add(object);
                }else {
                    entities.add(object);
                }
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
