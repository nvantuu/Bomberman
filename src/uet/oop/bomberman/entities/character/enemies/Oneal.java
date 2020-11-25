package uet.oop.bomberman.entities.character.enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    @Override
    public void update() {

    }
}
