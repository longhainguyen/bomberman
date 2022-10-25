package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.collisions.Rect;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.musicItem;

public class Speed extends Item {
    // bomber can run faster
    public Speed(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Speed);
            BombermanGame.fake_player.setSpeed_clock(0);
            if(!BombermanGame.effectMute)
            this.itemSound.playSound();
            this.setAte(true);
        }
    }
}
