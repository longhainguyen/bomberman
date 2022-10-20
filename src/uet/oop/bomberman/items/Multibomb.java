package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.sounds.musicItem;

public class Multibomb extends Item {
    // allow the bomber can lay 1 bomb more
    public Multibomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Multibomb);
            BombermanGame.fake_player.setMultibomb_clock(0);
            this.itemSound.playSound(musicItem.collectItem);
            this.setAte(true);
        }
    }
}
