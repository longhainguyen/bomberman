package uet.oop.bomberman.menu;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ButtonMenu extends StackPane {
    public boolean isTick = false;
    public Text text;
    public DropShadow drop;

    public Rectangle bg;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public ButtonMenu(String name) {
        text = new Text(name);
        text.setFont(Font.font(20));

        drop = new DropShadow(50,Color.WHITE);
        drop.setInput(new Glow());


        bg = new Rectangle(200,30);
        bg.setOpacity(0.6);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.5));

        setAlignment(Pos.CENTER);
        setRotate(-0.5);
        getChildren().addAll(bg,text);
    }

    public void setEvent() {
        setOnMouseEntered(event -> {
            bg.setTranslateX(10);
            text.setTranslateX(10);
            bg.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
        });

        setOnMouseExited(event -> {
            bg.setTranslateX(0);
            text.setTranslateX(0);
            bg.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
        });
        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }

    public void setDropShadow() {
        bg.setTranslateX(10);
        text.setTranslateX(10);
        bg.setFill(Color.WHITE);
        text.setFill(Color.BLACK);
        //setEffect(drop);
    }

    public void setBackShadow() {
        bg.setTranslateX(0);
        text.setTranslateX(0);
        bg.setFill(Color.BLACK);
        text.setFill(Color.WHITE);
    }

    public void setMenuHighScore() {
        bg.setHeight(200);
        bg.setWidth(200);
        bg.setFill(Color.WHITE);
        text.setFill(Color.BLACK);
        setEffect(drop);
    }

    public void setMenuHowToPlay() {
        bg.setHeight(200);
        bg.setWidth(550);
        bg.setFill(Color.WHITE);
        text.setFill(Color.BLACK);
        setEffect(drop);
    }

    public void setDropShadowForLevel() {
        this.bg.setFill(Color.PINK);
    }

}
