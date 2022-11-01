package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.musicItem;
import uet.oop.bomberman.sounds.Band;

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
            if( !checkout && BombermanGame.enemiesNumber == 0) {
                checkout = true;
                BombermanGame.gameMusic.getMediaPlayer().pause();
                if(!BombermanGame.effectMute) {
                    this.itemSound.soundclick(musicItem.gameWin);
                }
                if(BombermanGame.gameMusic.isIs_playing()) {
                    BombermanGame.is_want_pause_game = true;
                    BombermanGame.root.getChildren().remove(Band.musicImgae.get(0));
                    ImageView temp = Band.musicImgae.get(0);
                    Band.musicImgae.set(0, Band.musicImgae.get(3));
                    Band.musicImgae.set(3, temp);
                    BombermanGame.root.getChildren().add(Band.musicImgae.get(0));
                    BombermanGame.gameMusic.setIs_playing(false);
                }else{
                    BombermanGame.is_want_pause_game = false;
                }

            }
        }
    }
}
