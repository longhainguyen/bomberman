package uet.oop.bomberman.sounds;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Band {

    public static List<ImageView> musicImgae = new ArrayList<>();

    private List<Image> Imgae = new ArrayList<>();

    private List<FileInputStream> fileInput = new ArrayList<>();

    public static int enemiesNumber = 0;

    public static int countdownTime = 200;

    public static Text musicText = null;

    public static Text countdownText = null;

    public static Text time = null;

    public static Text heart = null;

    public static Text point = null;

    public static Text Point = null;

    public static int score = 0;


    public Band(){
        try {
            setFileInput();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        setImgae();
        setMusicImgae();
        setText();
    }

    public void setFileInput() throws FileNotFoundException {
        for (int i = 0; i < 8; i++) {
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
            } else if( i == 5){
                input = new FileInputStream("res/something/volume-mute.png");
            }
            else if( i == 6){
                input = new FileInputStream("res/something/effect.png");
            }
            else{
                input = new FileInputStream("res/something/mute_effect.png");
            }
            fileInput.add(input);
        }
    }

    public void setImgae() {
        for (int i = 0; i < 8; i++) {
            Image image = new Image(fileInput.get(i));
            Imgae.add(image);
        }
    }

    public void setMusicImgae() {
        for (int i = 0; i < 8; i++) {
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
            } else if( i == 5){
                view.setX(120);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            }
            else if(i == 6) {
                view.setX(160);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            }
            else {
                view.setX(160);
                view.setY(448);
                view.setFitWidth(30);
                view.setFitHeight(30);
            }
            musicImgae.add(view);
        }
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
        heart = new Text(289, 472, String.valueOf(BombermanGame.fake_player.getHeart()));
        heart.setFont(Font.font(Font.getFamilies().get(0), FontWeight.BOLD, 30));
        heart.setFill(Color.WHITE);
        point = new Text(340, 445, "Point");
        point.setFont(Font.font(Font.getFamilies().get(0), FontWeight.BOLD, 30));
        point.setFill(Color.WHITE);
        Point = new Text(340, 472, String.valueOf(score));
        musicSymbol.setBlanced(Point, point.getX() + point.getBoundsInLocal().getWidth() / 2);
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
            e.printStackTrace();
        }
    }


    public void setPoint(Group root) {
        //root.getChildren().remove(Point);
        Point.setText(String.valueOf(score));
        musicSymbol.setBlanced(Point, point.getX() + point.getBoundsInLocal().getWidth() / 2);
        //root.getChildren().add(Point);
    }

    public void addmusicImage(Group root) {
        for (int i = 0; i < 8; i++) {
            if (i == 3 || i == 5 || i == 7) {
                continue;
            }
            root.getChildren().add(musicImgae.get(i));
        }
    }

    public boolean checkSymbol(ImageView view, int posx, int posy) {
        if (posx >= view.getX() && posx <= view.getX() + view.getFitWidth()
                && posy >= view.getY() && posy <= view.getY() + view.getFitHeight()) {
            return true;
        }
        return false;
    }


    public void changeSymbol(Group root, int value) {
        if (value == 0) {
            root.getChildren().remove(musicImgae.get(0));
            ImageView temp = musicImgae.get(0);
            musicImgae.set(0, musicImgae.get(3));
            musicImgae.set(3, temp);
            root.getChildren().add(musicImgae.get(0));
        } else if (value == 4) {
            root.getChildren().remove(musicImgae.get(4));
            ImageView temp = musicImgae.get(4);
            musicImgae.set(4, musicImgae.get(5));
            musicImgae.set(5, temp);
            root.getChildren().add(musicImgae.get(4));
        } else if (value == 6) {
            root.getChildren().remove(musicImgae.get(6));
            ImageView temp = musicImgae.get(6);
            musicImgae.set(6, musicImgae.get(7));
            musicImgae.set(7, temp);
            root.getChildren().add(musicImgae.get(6));
        }
    }

public void coutdown(){
    Timeline Countdownline;
    Countdownline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        if (countdownTime > 0) {
            countdownTime--;
            countdownText.setText(String.valueOf(countdownTime));
            musicSymbol.setBlanced(countdownText, time.getX() + time.getBoundsInLocal().getWidth() / 2);
        }
    }));
    Countdownline.setCycleCount(-1);
    Countdownline.play();

}

    public void display(Scene scene){
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                for (int value = 0; value < musicImgae.size(); value++) {
                    if (value == 3 || value == 5 || value == 7) {
                        continue;
                    }
                    if (checkSymbol(musicImgae.get(value), (int) event.getX(), (int) event.getY())) {
                        if (value == 0) {
                            if (BombermanGame.gameMusic.isIs_playing()) {
                                changeSymbol(BombermanGame.root, value);
                                BombermanGame.gameMusic.pause();
                            } else {
                                changeSymbol(BombermanGame.root,value);
                                BombermanGame.gameMusic.resumme();
                            }
                        } else if (value == 1) {
                            if (!BombermanGame.gameMusic.isIs_playing()) {
                                changeSymbol(BombermanGame.root, 0);
                            }
                            BombermanGame.gameMusic.playLeft();
                            musicText.setText(musicGame.currentMusic.substring(10));

                        } else if (value == 2) {
                            if (!BombermanGame.gameMusic.isIs_playing()) {
                                changeSymbol(BombermanGame.root, 0);
                            }
                            BombermanGame.gameMusic.playRight();
                            musicText.setText(musicGame.currentMusic.substring(10));

                        } else if (value == 4) {
                            if (!BombermanGame.gameMusic.getMediaPlayer().isMute()) {
                                changeSymbol(BombermanGame.root,value);
                                BombermanGame.gameMusic.getMediaPlayer().setMute(true);
                            } else {
                                changeSymbol(BombermanGame.root,value);
                                BombermanGame.gameMusic.getMediaPlayer().setMute(false);
                            }
                        } else {
                            if (!BombermanGame.effectMute) {
                                changeSymbol(BombermanGame.root,value);
                                BombermanGame.effectMute = true;
                            } else {
                                changeSymbol(BombermanGame.root,value);
                                BombermanGame.effectMute = false;
                            }
                        }
                    }
                }
            }
        });
    }

    public void update(){
        display(BombermanGame.scene);
    }

}
