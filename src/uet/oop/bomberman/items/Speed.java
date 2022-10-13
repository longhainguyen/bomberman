package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Speed extends Item{
    public Speed(int x, int y, Image img){
        super(x, y, img);
    }

    @Override
    public void update() {
        if(collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())){
            BombermanGame.fake_player.setPowerType(itemType.Speed);
            this.setAte(true);
        }
    }
}
