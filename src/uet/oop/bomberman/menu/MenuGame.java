package uet.oop.bomberman.menu;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MenuGame extends Parent {
    private final int offset = 400;
    private VBox menu0 = new VBox(10);
    private VBox menu1 = new VBox(10);

    private ButtonMenu btnResume = new ButtonMenu("RESUME");
    private ButtonMenu btnOptions = new ButtonMenu("OPTIONS");
    private ButtonMenu btnExit = new ButtonMenu("EXIT");
    private ButtonMenu btnPlay = new ButtonMenu("PLAY");
    private ButtonMenu btnContinue = new ButtonMenu("CONTINUE");

    private ButtonMenu btnSound = new ButtonMenu("SOUND");
    private ButtonMenu btnVideo = new ButtonMenu("VIDEO");
    private ButtonMenu btnBack = new ButtonMenu("BACK");
    public boolean isEnterGame = false;
    private BombermanGame bombermanGame;
    private ImageView background;

    private Rectangle bg;

    public void setMenuInGame2() {
        bombermanGame.isPause = !bombermanGame.isPause;
        if(this.isEnterGame) {
            this.setMenuContinue();
        }
        this.isEnterGame = false;
        if(!this.isVisible()) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
            ft.setFromValue(0);
            ft.setToValue(1);
            this.setVisible(true);
            ft.play();
        }else {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
            ft.setFromValue(1);
            ft.setToValue(0);
            this.setVisible(false);
            ft.play();
        }
    }

    private void initBG() {
        bg = new Rectangle(640,480);
        bg.setFill(Color.GRAY);
        bg.setOpacity(0.4);
    }

    public void setBombermanGame(BombermanGame bombermanGame) {
        this.bombermanGame = bombermanGame;
    }

    public MenuGame() {
        this.setBackGround();
        getChildren().add(background);

        this.initMenu();
        this.setEventAll();
        this.initBG();

        menu0.getChildren().addAll(btnPlay,btnOptions,btnExit);
        menu1.getChildren().addAll(btnBack,btnSound,btnVideo);
        getChildren().addAll(bg,menu0);
    }

    public void setEventAll() {
        btnResume.setOnMouseClicked(event -> {
            this.bombermanGame.isPause = false;
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(event1 -> this.setVisible(false));
            ft.play();
        });


        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(menu1);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu0);
            tt.setToX(menu0.getTranslateX() - offset);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(event2 -> {
                getChildren().remove(menu0);
            });
        });

        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        btnPlay.setOnMouseClicked(event -> {
            this.isEnterGame = true;
            this.bombermanGame.initGame();
            if(this.getChildren().contains(background)) {
                this.getChildren().remove(background);
            }
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
            ft.setFromValue(1);
            ft.setToValue(0);
            setVisible(false);
            ft.play();

        });

        btnContinue.setOnMouseClicked(event -> {
            this.bombermanGame.isPause = false;
            setVisible(false);
        });

        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(menu1);
            });
        });

    }

    public void initMenu() {
        menu0.setTranslateX(50);
        menu0.setTranslateY(200);

        menu1.setTranslateX(50);
        menu1.setTranslateY(200);

        menu1.setTranslateX(offset);
    }

    public void setMenuContinue() {
        if(menu0.getChildren().contains(btnPlay)) {
            menu0.getChildren().removeAll(btnPlay);
        }
        menu0.getChildren().add(0,btnResume);

    }

    public void setBackGround(){
        ImageView imageView = null;

        try {
            InputStream is = Files.newInputStream(Paths.get("res/imageMenu/bg.jpg"));
            Image image = new Image(is);
            is.close();

            imageView = new ImageView(image);
            imageView.setFitHeight(BombermanGame.WINDOW_HEIGHT);
            imageView.setFitWidth(BombermanGame.WINDOW_WIDTH);
        }catch (Exception e) {
            e.printStackTrace();
        }

        background = imageView;
    }
}
