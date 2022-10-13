package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Multibomb extends Item{
    public Multibomb(int x, int y, Image img){
        super(x, y, img);
    }
    @Override
    public void update() {
        if(collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())){
            BombermanGame.fake_player.setPowerType(itemType.Multibomb);
            this.setAte(true);
        }
    }
}
