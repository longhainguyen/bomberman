package uet.oop.bomberman;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.intelligent.MoveIntelligent;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.menu.MenuGame;
import uet.oop.bomberman.menu.ButtonMenu;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public boolean isPause = false;
    private MenuGame menuGame;
    private ButtonMenu menuMain;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private List<Item> powerup = new ArrayList<>();

    private List<Entity> grass = new ArrayList<>();

    private Bomber player = new Bomber(1, 1, Sprite.player_right.getFxImage());

    public static Bomber fake_player = new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    public static final int WINDOW_WIDTH = WIDTH * Sprite.SCALED_SIZE;
    public static final int WINDOW_HEIGHT = HEIGHT * Sprite.SCALED_SIZE;

    private Bomb bomb = new Bomb(player.getX() / Sprite.SCALED_SIZE,
            player.getY() / Sprite.SCALED_SIZE,
            Sprite.bomb.getFxImage());

    private List<Bomb> bombChain = new ArrayList<>();

    public static int bomb_max = 1;

    private GraphicsContext gc;
    private Canvas canvas;

    private Map mapGame = new Map();

    private Scene scene;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public void setupBomb(Bomb other) {
        other.setbomb_explosion(stillObjects, entities);
        other.setIs_explode(true);
        stillObjects.remove(other);
        Map.stillEntity.remove(other);
        other.addEntities(entities);
        other.addEntities(Map.entitiesEntity);
        other.setExplosion_time(0);
    }

    @Override
    public void start(Stage stage) {
        menuGame = new MenuGame();
        menuGame.setBombermanGame(this);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(menuGame);

        // Tao scene
        //Scene scene = new Scene(root);
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


//        scene.setOnKeyPressed(event -> {
//            if(event.getCode() == KeyCode.ESCAPE) {
//                if(!menuGame.isVisible()) {
//                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuGame);
//                    ft.setFromValue(0);
//                    ft.setToValue(1);
//                    menuGame.setVisible(true);
//                    this.isPause = true;
//                    ft.play();
//                }else {
//                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuGame);
//                    ft.setFromValue(1);
//                    ft.setToValue(0);
//                    menuGame.setVisible(false);
//                    this.isPause = false;
//                    ft.play();
//                }
//            }
//        });

    }

    public void initGame() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            render();
            if(!this.isPause)
                update();
            if (player.getIsDie_time() > Bomber.max_die_time - 1) {
                entities.remove(player);
            }
            for (int i = 0; i < stillObjects.size(); i++) {
                if (stillObjects.get(i) instanceof Brick) {
                    Brick value = (Brick) stillObjects.get(i);
                    if (value.getEntity_frame() > value.getMax_long_time() - 1) {
                        Map.stillEntity.remove(stillObjects.get(i));
                        stillObjects.remove(i);
                        i--;
                    }
                }
            }

            for (int i = 0; i < entities.size(); i++) {
                if (!(entities.get(i) instanceof Bomber ||
                        entities.get(i) instanceof Bomb ||
                        entities.get(i) instanceof Explosion)) {
                    if (entities.get(i).getEntity_frame() > entities.get(i).getMax_long_time() - 1 && entities.get(i).isDie()) {
                        entities.get(i).setDie(false);
                        Map.entitiesEntity.remove(entities.get(i));
                        entities.remove(i);
                        i--;
                    }
                }
            }
            for (int i = 0; i < powerup.size(); i++) {
                if (powerup.get(i).isAte()) {
                    Map.powerMap.remove(powerup.get(i));
                    powerup.remove(i);
                    i--;
                }
            }
        }));
        timeline.setCycleCount(-1);
        timeline.play();
        Timeline timebomb = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            for (int i = 0; i < bombChain.size(); i++) {
                if (player.is_press_B && bombChain.get(i).isGo()) {
                    bombChain.get(i).setGo(false);
                    setupBomb(bombChain.get(i));
                } else if (player.is_out_of_time_B && !bombChain.get(i).isGo() && !player.is_press_B) {
                    bombChain.get(i).setGo(true);
                    setupBomb(bombChain.get(i));
                } else {
                    bombChain.get(i).setBomb_frame(bombChain.get(i).getBomb_frame() + 1);
                    if (bombChain.get(i).isGo()) {
                        bombChain.get(i).setExplosion_time(bombChain.get(i).getExplosion_time() + 1);
                    }
                    if (bombChain.get(i).getExplosion_time() >= 10) {
                        setupBomb(bombChain.get(i));
                    }
                }

                if (bombChain.get(i).isIs_explode()) {
                    if (bombChain.get(i).getBomb_explosion().get(0).getExplosion_frame() == Explosion.max_explosion_frame_time - 1) {
                        for (int j = 0; j < bombChain.get(i).getBomb_explosion().size(); j++) {
                            entities.remove(bombChain.get(i).getBomb_explosion().get(j));
                            Map.entitiesEntity.remove(bombChain.get(i).getBomb_explosion().get(j));
                        }
                        bombChain.remove(i);
                        i--;
                    } else {
                        for (int j = 0; j < bombChain.get(i).getBomb_explosion().size(); j++) {
                            int num = bombChain.get(i).getBomb_explosion().get(j).getExplosion_frame();
                            bombChain.get(i).getBomb_explosion().get(j).setExplosion_frame(num + 1);
                        }
                    }
                }
            }
        }));
        timebomb.setCycleCount(-1);
        timebomb.play();

        //mapGame.creatMap2("res/levels/Level2.txt", entities, stillObjects, powerup, grass, player);
        mapGame.creatMap2("res/levels/Level1.txt", entities, stillObjects, powerup, grass, player);

        player.setBomberRectCollisions(stillObjects);

        //set event for player
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE:
                        isPause = !isPause;
                        System.out.println(menuGame.isEnterGame);
                        if(menuGame.isEnterGame) {
                            menuGame.setMenuContinue();
                        }
                        menuGame.isEnterGame = false;
                        if(!menuGame.isVisible()) {
                            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuGame);
                            ft.setFromValue(0);
                            ft.setToValue(1);
                            menuGame.setVisible(true);
                            ft.play();
                        }else {
                            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuGame);
                            ft.setFromValue(1);
                            ft.setToValue(0);
                            menuGame.setVisible(false);
                            ft.play();
                        }
                        break;
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
                        if (bombChain.size() < bomb_max && player.getHeart() != 0) {
                            Bomb temp = new Bomb(0, 0, Sprite.bomb.getFxImage());
                            if (Math.abs(stillObjects.get(0).getX()) % Sprite.SCALED_SIZE != 0) {
                                temp.setBomb(player, Sprite.bomb.getFxImage(), Sprite.SCALED_SIZE -
                                        (Math.abs(stillObjects.get(0).getX()) % Sprite.SCALED_SIZE));
                            } else {
                                temp.setBomb(player, Sprite.bomb.getFxImage(), 0);
                            }
                            if (!player.isRemote) {
                                temp.setGo(true);
                            }
                            bombChain.add(temp);
                            stillObjects.add(temp);
                            Map.stillEntity.add(temp);
                        }
                        break;
                    case B:
                        if (player.isRemote && bombChain.size() > 0 && bombChain.get(0).press_B_number < 1) {
                            for (Bomb value : bombChain) {
                                value.press_B_number++;
                            }
                            player.is_press_B = true;
                            for (Bomb value : bombChain) {
                                value.setGo(true);
                            }
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
                        break;
                    case SPACE:
                        break;
                    case B:
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
        grass.forEach(Entity::update);
        powerup.forEach(Item::update);
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        mapGame.update();
        MoveIntelligent.setBomberXY(player.getX(), player.getY());
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grass.forEach(g -> g.render(gc));
        powerup.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
