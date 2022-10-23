package uet.oop.bomberman.sounds;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Rect;

public class musicSymbol {
    private int x;
    private int y;
    private Image img;

    private Rect musicRect;

    public musicSymbol(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.musicRect = new Rect(x, y, 60, 60);
    }

   /* public boolean checkSymbol(int posx, int posy) {
        if(posx >= musicRect.getX() && posx <= musicRect.getX() + musicRect.getW()
                && posy >= musicRect.getY() && posy <= musicRect.getY() + musicRect.getH()){
            return true;
        }
        return false;
    }*/

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public Rect getMusicRect() {
        return musicRect;
    }

    public void setMusicRect(Rect musicRect) {
        this.musicRect = musicRect;
    }
}
