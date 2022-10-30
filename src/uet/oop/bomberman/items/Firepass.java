package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.sounds.Band;
import uet.oop.bomberman.sounds.musicItem;

public class Firepass extends Item {
    // bomber can survive when unfortunately you're in range blast of bomb
    public Firepass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if (collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()) {
            BombermanGame.fake_player.addType(itemType.Firepass);
            BombermanGame.fake_player.setSurvival_clock(0);
            BombermanGame.fake_player.isSurvival = true;
            addTextItem("Firepass ");
            if(!BombermanGame.effectMute)
            this.itemSound.playSound();
            this.setAte(true);
        }
    }
}
