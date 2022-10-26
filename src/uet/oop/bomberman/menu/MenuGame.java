package uet.oop.bomberman.menu;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MenuGame extends Parent {
    private final int offset = 400;
    private final VBox menu0 = new VBox(10);
    private final VBox menu1 = new VBox(10);

    private final VBox menuLose = new VBox(10);
    private final VBox menuWin = new VBox(10);

    private final ButtonMenu btnWin = new ButtonMenu("You WIN");
    private final ButtonMenu btnLose = new ButtonMenu("YOU LOSE");
    private final ButtonMenu btnResume = new ButtonMenu("RESUME");
    private final ButtonMenu btnOptions = new ButtonMenu("OPTIONS");
    private final ButtonMenu btnExit = new ButtonMenu("EXIT");
    private final ButtonMenu btnPlay = new ButtonMenu("NEW GAME");
    private final ButtonMenu btnContinue = new ButtonMenu("CONTINUE");

    private ButtonMenu btnSound = new ButtonMenu("SOUND");
    private ButtonMenu btnVideo = new ButtonMenu("VIDEO");
    private ButtonMenu btnBack = new ButtonMenu("BACK");
    public boolean isEnterGame = false;
    private BombermanGame bombermanGame;
    private ImageView background;

    private Rectangle bg;

    private void initButton() {
        btnSound.setEvent();
        btnVideo.setEvent();
        btnBack.setEvent();
        btnOptions.setEvent();
        btnPlay.setEvent();
        btnExit.setEvent();
        btnContinue.setEvent();
        btnResume.setEvent();
        btnLose.setDropShadow();
        btnWin.setDropShadow();
    }

    public void setMenuWhenWin() {
        BombermanGame.isPause = true;
        if(this.getChildren().contains(menuLose)) {
            this.getChildren().remove(menuLose);
        }
        if(!menu0.getChildren().contains(btnPlay)) {
            menu0.getChildren().add(0,btnPlay);
        }
        if(!this.getChildren().contains(menuWin)) {
            this.getChildren().add(menuWin);
        }
        this.isEnterGame = false;
        menu0.getChildren().remove(btnResume);
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
        ft.setFromValue(0);
        ft.setToValue(1);
        this.setVisible(true);
        ft.play();
    }
    public void setMenuWhenLose() {
        this.isEnterGame = false;
        if(!this.getChildren().contains(menuLose)) {
            this.getChildren().add(menuLose);
        }
        if(this.getChildren().contains(menuWin)) {
            this.getChildren().remove(menuWin);
        }
        if(!menu0.getChildren().contains(btnPlay)) {
            menu0.getChildren().add(0,btnPlay);
        }
        BombermanGame.isPause = true;
        menu0.getChildren().remove(btnResume);
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
        ft.setFromValue(0);
        ft.setToValue(1);
        this.setVisible(true);
        ft.play();
    }

    public void setMenuInGame2() {
        if(bombermanGame.entities.contains(bombermanGame.player))
        {
            BombermanGame.isPause = !BombermanGame.isPause;
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
        this.initButton();

        menu0.getChildren().addAll(btnPlay,btnOptions,btnExit);
        menu1.getChildren().addAll(btnBack,btnSound,btnVideo);
        getChildren().addAll(bg,menu0);
    }

    public void setEventAll() {
        btnResume.setOnMouseClicked(event -> {
            BombermanGame.isPause = false;
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
            bombermanGame.isPause = false;
            if (bombermanGame.isEndGame){
                this.bombermanGame.deleteGame();
                bombermanGame.isEndGame = false;

            }
            this.bombermanGame.initGame();
            this.getChildren().remove(background);
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this);
            ft.setFromValue(1);
            ft.setToValue(0);
            setVisible(false);
            ft.play();

        });

        btnContinue.setOnMouseClicked(event -> {
            BombermanGame.isPause = false;
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

        menuLose.setTranslateX(50);
        menuLose.setTranslateY(50);
        menuLose.getChildren().add(btnLose);

        menuWin.setTranslateX(50);
        menuWin.setTranslateY(50);
        menuWin.getChildren().add(btnWin);
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
