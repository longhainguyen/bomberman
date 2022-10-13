package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.maps.Map;


public class Flame extends Item {
    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
       if(collisionItem.checkcollision(rectItem, BombermanGame.fake_player.getEntities_rect())){
                  BombermanGame.fake_player.setPowerType(itemType.Flame);
                  this.setAte(true);
       }
    }
}
