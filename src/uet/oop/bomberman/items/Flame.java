package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.maps.Map;
import uet.oop.bomberman.sounds.musicItem;


public class Flame extends Item {
    // extends the blast radius of bomb by one tile
    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Flame);
            BombermanGame.fake_player.setFlame_clock(0);
            if(!BombermanGame.effectMute)
            this.itemSound.playSound(musicItem.collectItem);
            this.setAte(true);
        }
    }
}
