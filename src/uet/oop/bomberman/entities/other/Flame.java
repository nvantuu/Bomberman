package uet.oop.bomberman.entities.other;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public int countImageFlame = 0;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, null);
    }

    private void animationFlame() {
        if (countImageFlame == 120) {
            img = Sprite.bomb_exploded.getFxImage();
        }
        if (countImageFlame == 150) {
            img = Sprite.bomb_exploded1.getFxImage();
        }
        if (countImageFlame == 180) {
            img = Sprite.bomb_exploded2.getFxImage();
        }
        countImageFlame++;
    }

    @Override
    public void update() {
        animationFlame();
    }
}
