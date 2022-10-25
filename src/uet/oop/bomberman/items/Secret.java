package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.sounds.musicItem;

public class Secret extends Item{
    // bomber will be invincible in a few seconds
    public Secret(int x, int y, Image img){
        super(x, y, img);
    }

    @Override
    public void update() {
        this.setRectItem();
        if(collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())
                && !isBrickcovered()){
            BombermanGame.fake_player.addType(itemType.Secret);
            if(!BombermanGame.effectMute)
            this.itemSound.playSound();
            this.setAte(true);
        }
    }
}
