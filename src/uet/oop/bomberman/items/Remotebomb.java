package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

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
            this.setAte(true);
        }
    }
}
