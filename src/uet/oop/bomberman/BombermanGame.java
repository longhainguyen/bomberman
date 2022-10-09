package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.MapExpression;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    private Bomber player = new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    public static final int WINDOW_WIDTH = WIDTH * Sprite.SCALED_SIZE;
    public static final int WINDOW_HEIGHT = HEIGHT * Sprite.SCALED_SIZE;

    private Bomb bomb = new Bomb(player.getX() / Sprite.SCALED_SIZE, player.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());

    public static final int bomb_max = 1;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private Map mapGame = new Map();

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


//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                render();
//                update();
//            }
//        };
//        timer.start();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            render();
            update();
        }));
        timeline.setCycleCount(-1);
        timeline.play();
        Timeline timebomb = new Timeline(new KeyFrame(Duration.millis(200), e -> {

            bomb.setBomb_frame(bomb.getBomb_frame() + 1);
            if (bomb.isGo()) {
                bomb.setExplosion_time(bomb.getExplosion_time() + 1);
            }
            if (bomb.getExplosion_time() >= 10) {
                bomb.setBomb_explosion();
                for (int i = 0; i < entities.size(); i++) {
                    if (entities.get(i) instanceof Bomb) {
                        entities.remove(i);
                        Map.entitiesEntity.remove(bomb);
                        bomb.setBomb_number(bomb.getBomb_number() - 1);
                        bomb.setExplosion_time(0);
                        bomb.setGo(false);
                    }
                }
                bomb.addEntities(entities);
                bomb.addEntities(Map.entitiesEntity);
            }
            if (!bomb.isGo()) {
                if (bomb.getBomb_explosion().get(0).getExplosion_frame() == Explosion.max_explosion_frame_time - 1) {
                    for (int i = 0; i < bomb.getBomb_explosion().size(); i++) {
                        entities.remove(bomb.getBomb_explosion().get(i));
                        Map.entitiesEntity.remove(bomb.getBomb_explosion().get(i));
                    }
                } else {
                    for (int i = 0; i < bomb.getBomb_explosion().size(); i++) {
                        int num = bomb.getBomb_explosion().get(i).getExplosion_frame();
                        bomb.getBomb_explosion().get(i).setExplosion_frame(num + 1);
                    }
                }
            }

        }));
        timebomb.setCycleCount(-1);
        timebomb.play();

        //mapGame.creatMap2("res/levels/Level2.txt", entities, stillObjects, player);
        mapGame.creatMap2("res/levels/Level1.txt", entities, stillObjects, player);
        player.setBomberRectCollisions(stillObjects);

        //set event for player
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        player.setGoUp(true);
                        Map.goDown = true;
                        break;
                    case DOWN:
                        player.setGoDown(true);
                        Map.goUp = true;
                        break;
                    case RIGHT:
                        player.setGoRight(true);
                        Map.goLeft = true;
                        break;
                    case LEFT:
                        player.setGoLeft(true);
                        Map.goRight = true;
                        break;
                    case SPACE:
                        if (bomb.getBomb_number() < bomb_max) {
                            bomb.setGo(true);
                            bomb.setBomb(player, Sprite.bomb.getFxImage());
                            bomb.setBomb_number(bomb.getBomb_number() + 1);
                            entities.add(bomb);
                            Map.entitiesEntity.add(bomb);
                        }
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        player.setGoUp(false);
                        Map.goDown = false;
                        break;
                    case DOWN:
                        player.setGoDown(false);
                        Map.goUp = false;
                        break;
                    case RIGHT:
                        player.setGoRight(false);
                        Map.goLeft = false;
                        break;
                    case LEFT:
                        player.setGoLeft(false);

                        Map.goRight = false;

                    case SPACE:

                        break;
                }
            }
        });


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

    public void update() {
        entities.forEach(Entity::update);
        mapGame.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
