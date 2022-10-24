package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.sounds.musicItem;

public class Bombpass extends Item {
    // bomber can walk through the bomb
    public Bombpass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Bombpass);
            BombermanGame.fake_player.setBombpass_clock(0);
            BombermanGame.fake_player.isBombpass = true;
            if(BombermanGame.effectMute)
            this.itemSound.playSound(musicItem.collectItem);
            this.setAte(true);
        }
    }
}
