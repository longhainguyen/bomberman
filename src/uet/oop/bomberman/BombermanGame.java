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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.intelligent.MoveIntelligent;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.musicItem;
import uet.oop.bomberman.sounds.musicSymbol;
import uet.oop.bomberman.sounds.musicGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BombermanGame extends Application {
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private List<Item> powerup = new ArrayList<>();

    private List<Entity> grass = new ArrayList<>();

    private List<ImageView> musicImgae = new ArrayList<>();

    private List<Image> Imgae = new ArrayList<>();

    private List<FileInputStream> fileInput = new ArrayList<>();

    public static int countdownTime = 200;

    public static Text musicText = null;

    public static Text countdownText = null;

    public static Text time = null;

    public static Text heart = null;

    public static Text point = null;

    public static Text Point = null;

    public static int score = 0;

    public static final int balloonScore = 2000;

    public static final int onealScore = 4000;

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
    public static musicGame gameMusic = new musicGame();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }


    public void gameMusic() {
        gameMusic.playMusic();
    }

    public void stopMusic() {
        gameMusic.getMediaPlayer().pause();
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

    public void setFileInput() throws FileNotFoundException {
        for (int i = 0; i < 6; i++) {
            FileInputStream input;
            if (i == 0) {
                input = new FileInputStream("res/something/pause.png");
            } else if (i == 1) {
                input = new FileInputStream("res/something/left.png");
            } else if (i == 2) {
                input = new FileInputStream("res/something/right.png");
            } else if (i == 3) {
                input = new FileInputStream("res/something/play.png");
            } else if (i == 4) {
                input = new FileInputStream("res/something/volume.png");
            } else {
                input = new FileInputStream("res/something/volume-mute.png");
            }
            fileInput.add(input);
        }
    }

    public void setImgae() {
        for (int i = 0; i < 6; i++) {
            Image image = new Image(fileInput.get(i));
            Imgae.add(image);
        }
    }

    public void setMusicImgae() {
        for (int i = 0; i < 6; i++) {
            ImageView view = new ImageView(Imgae.get(i));
            if (i == 0) {
                view.setX(40);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            } else if (i == 1) {
                view.setX(0);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            } else if (i == 2) {
                view.setX(80);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            } else if (i == 3) {
                view.setX(40);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            } else if (i == 4) {
                view.setX(120);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            } else {
                view.setX(120);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            }
            musicImgae.add(view);
        }
    }

    public void addmusicImage(Group root) {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                continue;
            }
            root.getChildren().add(musicImgae.get(i));
        }
    }

    public void changeSymbol(Group root, int value) {
        if (value != 4) {
            root.getChildren().remove(musicImgae.get(0));
            ImageView temp = musicImgae.get(0);
            musicImgae.set(0, musicImgae.get(3));
            musicImgae.set(3, temp);
            root.getChildren().add(musicImgae.get(0));
        } else {
            root.getChildren().remove(musicImgae.get(4));
            ImageView temp = musicImgae.get(4);
            musicImgae.set(4, musicImgae.get(5));
            musicImgae.set(5, temp);
            root.getChildren().add(musicImgae.get(4));
        }
    }

    public boolean checkSymbol(ImageView view, int posx, int posy) {
        if (posx >= view.getX() && posx <= view.getX() + view.getFitWidth()
                && posy >= view.getY() && posy <= view.getY() + view.getFitHeight()) {
            return true;
        }
        return false;
    }

    public void setText() {
        musicText = new Text(10, 430, musicGame.currentMusic.substring(10));
        Font font = Font.loadFont("res/font/INVASION2000.TTF", 20);
        musicText.setFont(font);
        musicText.setFill(Color.HONEYDEW);
        musicText.setStroke(Color.YELLOW);
        countdownText = new Text(200, 473, String.valueOf(countdownTime));
        countdownText.setFont(Font.font(Font.getFamilies().get(0), FontWeight.BOLD, 30));
        countdownText.setFill(Color.WHITE);
        time = new Text(195, 445, "TIME");
        time.setFont(Font.font(Font.getFamilies().get(0), FontWeight.BOLD, 30));
        time.setFill(Color.WHITE);
        heart = new Text(292, 472, String.valueOf(player.getHeart()));
        heart.setFont(Font.font(Font.getFamilies().get(0), FontWeight.BOLD, 30));
        heart.setFill(Color.WHITE);
        point = new Text(340, 445, "Point");
        point.setFont(Font.font(Font.getFamilies().get(0), FontWeight.BOLD, 30));
        point.setFill(Color.WHITE);
        Point = new Text(340, 472, String.valueOf(score));
        musicSymbol.setBlanced(Point, point.getX() + point.getBoundsInLocal().getWidth()/2);
        Point.setFont(Font.font(Font.getFamilies().get(0), FontWeight.BOLD, 30));
        Point.setFill(Color.WHITE);
    }

    public void setHeart(Group root) {
        try {
            FileInputStream input = new FileInputStream("res/something/heart.png");
            Image img = new Image(input);
            ImageView view = new ImageView(img);
            view.setX(280);
            view.setY(415);
            view.setFitWidth(30);
            view.setFitHeight(30);
            root.getChildren().add(view);
        } catch (FileNotFoundException e) {

        }
    }

    public void setPoint(Group root){
        root.getChildren().remove(Point);
        Point.setText(String.valueOf(score));
        musicSymbol.setBlanced(Point, point.getX() + point.getBoundsInLocal().getWidth()/2);
        root.getChildren().add(Point);
    }


    @Override
    public void start(Stage stage)  {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        try {
            setFileInput();
        } catch (FileNotFoundException e) {

        }
        setImgae();
        setMusicImgae();
        setText();
        Rectangle pointBand = new Rectangle(0, 416, 640, 64);
        pointBand.setFill(Color.gray(0.5));

        // Tao root container
        Group root = new Group();
        root.getChildren().add(pointBand);
        root.getChildren().add(musicText);
        root.getChildren().add(countdownText);
        root.getChildren().add(time);
        root.getChildren().add(heart);
        root.getChildren().add(point);
        root.getChildren().add(Point);
        setHeart(root);
        addmusicImage(root);
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        // music when play
        gameMusic();
        initGame(scene, root);
    }


    public void initGame(Scene scene, Group root) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            render();
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
                        if (entities.get(i) instanceof Balloon) {
                            score += balloonScore;
                        } else if (entities.get(i) instanceof Oneal) {
                            score += onealScore;
                        }
                       setPoint(root);
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
                    player.is_out_of_time_B = false;
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
                    if (!player.isDie()) {
                        bombChain.get(i).exploSound();
                    } else {
                        if (bombChain.get(i).getExplosionSound().isIs_playing()) {
                            bombChain.get(i).getExplosionSound().getMediaPlayer().pause();
                            bombChain.get(i).getExplosionSound().setIs_playing(false);
                        }
                    }
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

        Timeline Countdownline;
        Countdownline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (countdownTime > 0) {
                countdownTime--;
                countdownText.setText(String.valueOf(countdownTime));
                musicSymbol.setBlanced(countdownText, time.getX() + time.getBoundsInLocal().getWidth()/ 2);
            }
        }));

        Countdownline.setCycleCount(-1);
        Countdownline.play();

        //mapGame.creatMap2("res/levels/Level2.txt", entities, stillObjects, powerup, grass, player);
        mapGame.creatMap2("res/levels/Level1.txt", entities, stillObjects, powerup, grass, player);

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
                    /*case P:
                        gameMusic.changeMusic();
                        break;*/
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
                    /*case P:
                        break;*/
                }
            }
        });

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int value = 0; value < musicImgae.size(); value++) {
                    if (value == 3 || value == 5) {
                        continue;
                    }
                    if (checkSymbol(musicImgae.get(value), (int) event.getX(), (int) event.getY())) {
                        if (value == 0) {
                            if (gameMusic.isIs_playing()) {
                                changeSymbol(root, value);
                                gameMusic.pause();
                            } else {
                                changeSymbol(root, value);
                                gameMusic.resumme();
                            }
                        } else if (value == 1) {
                            if (!gameMusic.isIs_playing()) {
                                changeSymbol(root, value);
                            }
                            gameMusic.playLeft();
                            root.getChildren().remove(musicText);
                            musicText.setText(musicGame.currentMusic.substring(10));
                            root.getChildren().add(musicText);

                        } else if (value == 2) {
                            if (!gameMusic.isIs_playing()) {
                                changeSymbol(root, value);
                            }
                            gameMusic.playRight();
                            root.getChildren().remove(musicText);
                            musicText.setText(musicGame.currentMusic.substring(10));
                            root.getChildren().add(musicText);

                        } else {
                            if (!gameMusic.getMediaPlayer().isMute()) {
                                gameMusic.getMediaPlayer().setMute(true);
                                changeSymbol(root, value);
                            } else {
                                gameMusic.getMediaPlayer().setMute(false);
                                changeSymbol(root, value);
                            }
                        }
                    }
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
