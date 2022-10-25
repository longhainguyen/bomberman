package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.sounds.musicItem;

public class Remotebomb extends Item {
    // bomber can romote bomb whenever bomber want the bomb to explode
    public Remotebomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Remote);
            BombermanGame.fake_player.isRemote = true;
            BombermanGame.fake_player.is_out_of_time_B = false;
            BombermanGame.fake_player.setRemote_clock(0);
            if(!BombermanGame.effectMute)
            this.itemSound.playSound();
            this.setAte(true);
        }
    }
}
