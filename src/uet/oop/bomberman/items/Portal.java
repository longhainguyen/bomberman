package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.musicItem;

public class Portal extends Item {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Portal);
            BombermanGame.gameMusic.getMediaPlayer().pause();
            if(BombermanGame.effectMute)
            this.itemSound.playSound(musicItem.gameWin);
            this.setAte(true);
        }
    }
}
