package uet.oop.bomberman.menu;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.sounds.musicItem;
import uet.oop.bomberman.sounds.musicGame;

import java.awt.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MenuGame extends Parent {
    public String level = "res/levels/Level1.txt";
    private final int offset = 400;
    private final VBox menu0 = new VBox(10);
    private final VBox menu1 = new VBox(10);
    private final VBox menuLevel = new VBox(10);

    private final VBox menuHighScore = new VBox(10);

    private final VBox menuLose = new VBox(10);
    private final VBox menuWin = new VBox(10);

    private final VBox menuHowToPlay = new VBox(10);

    private final ButtonMenu btnBackMenu = new ButtonMenu("EXIT");
    private final ButtonMenu btnLevel = new ButtonMenu("LEVEL");
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

    private ButtonMenu btnHighScore = new ButtonMenu("HIGH SCORE");
    private ButtonMenu btnHowToPlay = new ButtonMenu("HOW TO PLAY");

    private ButtonMenu btnMenuHighScore = new ButtonMenu("HIGH SCORE");
    private ButtonMenu btnMenuHowToPlay = new ButtonMenu("");
    private ButtonMenu btnBack2 = new ButtonMenu("BACK");
    private ButtonMenu btnBack3 = new ButtonMenu("BACK");
    private ButtonMenu btnBack4 = new ButtonMenu("BACK");
    private ButtonMenu btnLevel1 = new ButtonMenu("LEVEL1");
    private ButtonMenu btnLevel2 = new ButtonMenu("LEVEL2");
    private ButtonMenu btnLevel3 = new ButtonMenu("LEVEL3");
    public boolean isEnterGame = false;
    private BombermanGame bombermanGame;
    private ImageView background;

    private Rectangle bg;


    private musicItem clickMouse = new musicItem(1, 50, musicItem.click);

    public musicItem lobbyMusic = new musicItem(-1, 50, musicItem.lobby);


    private void initButton() {
        btnBackMenu.setEvent();
        btnBack4.setEvent();
        btnLevel.setEvent();
        btnBack3.setEvent();
        btnBack2.setEvent();
        btnHowToPlay.setEvent();
        btnHighScore.setEvent();
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
        btnMenuHighScore.setMenuHighScore();
        btnMenuHowToPlay.setMenuHowToPlay();
    }

    public void setMenuWhenWin() {

        this.isEnterGame = false;
        if (this.getChildren().contains(menuLose)) {
            this.getChildren().remove(menuLose);
        }
        if (!menu0.getChildren().contains(btnPlay)) {
            menu0.getChildren().add(0, btnPlay);
        }
        if (!this.getChildren().contains(menuWin)) {
            this.getChildren().add(menuWin);

        }
        BombermanGame.isPause = true;
        menu0.getChildren().remove(btnResume);
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
        ft.setFromValue(0);
        ft.setToValue(1);
        this.setVisible(true);
        ft.play();
    }

    public void setMenuWhenLose() {
        this.isEnterGame = false;
        if (!this.getChildren().contains(menuLose)) {
            this.getChildren().add(menuLose);
        }
        if (this.getChildren().contains(menuWin)) {
            this.getChildren().remove(menuWin);
        }
        if (!menu0.getChildren().contains(btnPlay)) {
            menu0.getChildren().add(0, btnPlay);
        }
        BombermanGame.isPause = true;
        menu0.getChildren().remove(btnResume);
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
        ft.setFromValue(0);
        ft.setToValue(1);
        this.setVisible(true);
        ft.play();
    }

    public void setMenuInGame2() {
        if (bombermanGame.entities.contains(bombermanGame.player)) {
            getChildren().remove(menuWin);
            getChildren().remove(menuLose);
            BombermanGame.isPause = !BombermanGame.isPause;
            bombermanGame.isEndGame = true;
            if (this.isEnterGame) {
                this.setMenuContinue();
            }
            this.isEnterGame = false;
            if (!this.isVisible()) {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setFromValue(0);
                ft.setToValue(1);
                this.setVisible(true);
                ft.play();
            } else {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setFromValue(1);
                ft.setToValue(0);
                this.setVisible(false);
                ft.play();
            }
        }
    }

    private void initBG() {
        bg = new Rectangle(640, 480);
        bg.setFill(Color.GRAY);
        bg.setOpacity(0.4);
    }

    public void setBombermanGame(BombermanGame bombermanGame) {
        this.bombermanGame = bombermanGame;
    }

    public MenuGame() {
        this.setBackGround("res/imageMenu/bg.jpg");
        getChildren().add(background);

        this.initMenu();
        this.setEventAll();
        this.initBG();
        this.initButton();
        this.setEventForBtnHighScore();
        this.setEvenForBtnHowToPlay();
        this.setEventFroBtnLevel();
        this.setEventForBtnLevelType();

        this.lobbyMusic.playSound();

        menu0.getChildren().addAll(btnPlay, btnOptions, btnExit);
        menu1.getChildren().addAll(btnHighScore, btnLevel,btnHowToPlay, btnBack);
        menuLevel.getChildren().addAll(btnLevel1,btnLevel2,btnLevel3,btnBack4);
        menuHighScore.getChildren().addAll(btnMenuHighScore, btnBack2);
        menuHowToPlay.getChildren().addAll(btnMenuHowToPlay, btnBack3);
        getChildren().addAll(bg, menu0);
    }

    public void setEventAll() {
        btnResume.setOnMouseClicked(event -> {

            this.clickMouse.soundclick(musicItem.click);
            this.bombermanGame.isPause = false;

            BombermanGame.isPause = false;

            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(event1 -> this.setVisible(false));
            ft.play();
        });


        btnOptions.setOnMouseClicked(event -> {

            this.clickMouse.soundclick(musicItem.click);
            getChildren().add(menu1);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(event2 -> {
                getChildren().remove(menu0);
            });

            changeMenuRight(menu0, menu1);

        });

        btnExit.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            System.exit(0);
        });

        btnPlay.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            this.isEnterGame = true;
            bombermanGame.isPause = false;

            if (bombermanGame.isEndGame) {
                this.bombermanGame.deleteGame();
                bombermanGame.isEndGame = false;
            }
            this.lobbyMusic.getMediaPlayer().stop();
            this.bombermanGame.mapGame.fileName = level;
            this.bombermanGame.initGame();
            this.getChildren().remove(background);
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            setVisible(false);
            ft.play();

        });

        btnContinue.setOnMouseClicked(event -> {

            this.clickMouse.soundclick(musicItem.click);
            this.bombermanGame.isPause = false;

            BombermanGame.isPause = false;

            setVisible(false);
        });

        btnBack.setOnMouseClicked(event -> {

            this.clickMouse.soundclick(musicItem.click);
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(event1 -> {
                getChildren().remove(menu1);
            });

            changeMenu(menu1, menu0);

        });

    }

    private void changeMenu(VBox menuCur, VBox menuNext) {
        if(!getChildren().contains(menuNext))
            getChildren().add(menuNext);

        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuCur);
        tt.setToX(menuCur.getTranslateX() + offset);

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuNext);
        tt1.setToX(menuCur.getTranslateX());

        tt.play();
        tt1.play();

        tt.setOnFinished(event1 -> {
            getChildren().remove(menuCur);
        });
    }

    public void setEventForBtnHighScore() {

        btnHighScore.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            changeMenuRight(menu1, menuHighScore);
            updateHighScore();
        });

        btnBack2.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            changeMenu(menuHighScore, menu1);
        });
    }

    public void setEvenForBtnHowToPlay() {

        btnHowToPlay.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            changeMenuRight(menu1, menuHowToPlay);
        });

        btnBack3.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            changeMenu(menuHowToPlay, menu1);
        });
    }

    public void setEventFroBtnLevel() {
        btnLevel.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            changeMenuRight(menu1, menuLevel);
        });
        btnBack4.setOnMouseClicked(event -> {
            this.clickMouse.soundclick(musicItem.click);
            changeMenu(menuLevel, menu1);
        });
    }

    public void setEventForBtnLevelType() {
        btnLevel1.setOnMouseClicked(event -> {
            level = "res/levels/Level1.txt";
            changeLevel(btnLevel1,btnLevel2,btnLevel3);
        });

        btnLevel2.setOnMouseClicked(event -> {
            level = "res/levels/Level2.txt";
            changeLevel(btnLevel2,btnLevel1,btnLevel3);
        });

        btnLevel3.setOnMouseClicked(event -> {
            level = "res/levels/Level3.txt";
            changeLevel(btnLevel3,btnLevel2,btnLevel1);
        });
    }

    public void changeLevel(ButtonMenu cur, ButtonMenu btn2, ButtonMenu btn3) {
        cur.setDropShadow();
        btn2.setBackShadow();
        btn3.setBackShadow();
    }


    private void changeMenuRight(VBox menuCur, VBox menuNext) {
        if (!getChildren().contains(menuNext))
            getChildren().add(menuNext);

        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuCur);
        tt.setToX(menuCur.getTranslateX() - offset);
        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuNext);
        tt1.setToX(menuCur.getTranslateX());

        tt.play();
        tt1.play();

        tt.setOnFinished(event2 -> {
            getChildren().remove(menuCur);
        });
    }

    public void initMenu() {
        menuLevel.setTranslateX(50);
        menuLevel.setTranslateY(200);

        menuHowToPlay.setTranslateX(50);
        menuHowToPlay.setTranslateY(100);

        menuHighScore.setTranslateX(50);
        menuHighScore.setTranslateY(100);

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
//        if(menu0.getChildren().contains(btnPlay))
//            menu0.getChildren().removeAll(btnPlay);
        if(!menu0.getChildren().contains(btnResume))
            menu0.getChildren().add(1, btnResume);

    }

    public void setBackGround(String path) {
        ImageView imageView = null;

        try {
            InputStream is = Files.newInputStream(Paths.get(path));
            Image image = new Image(is);
            is.close();

            imageView = new ImageView(image);
            imageView.setFitHeight(BombermanGame.WINDOW_HEIGHT);
            imageView.setFitWidth(BombermanGame.WINDOW_WIDTH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        background = imageView;
    }

    public void updateHighScore() {
        MenuStage.setHighScore();
        String text = "HIGH SCORE \n" + MenuStage.highScore;
        btnMenuHighScore.text.setText(text);
    }
}
