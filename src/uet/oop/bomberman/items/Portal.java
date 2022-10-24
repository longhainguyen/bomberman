package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.musicItem;

public class Portal extends Item {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    private boolean checkout = false;

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            if(!BombermanGame.effectMute && !checkout && BombermanGame.enemiesNumber == 0) {
                checkout = true;
                BombermanGame.gameMusic.getMediaPlayer().pause();
                this.itemSound.playSound(musicItem.gameWin);
                BombermanGame.root.getChildren().remove(BombermanGame.musicImgae.get(0));
                ImageView temp =BombermanGame.musicImgae.get(0);
                BombermanGame.musicImgae.set(0, BombermanGame.musicImgae.get(3));
                BombermanGame.musicImgae.set(3, temp);
                BombermanGame.root.getChildren().add(BombermanGame.musicImgae.get(0));
                BombermanGame.gameMusic.setIs_playing(false);
            }
        }
    }
}
