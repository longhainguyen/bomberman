package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Wallpass extends Item {
    // bomber can walk through the wall
    public Wallpass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Wallpass);
            this.setAte(true);
        }
    }
}
