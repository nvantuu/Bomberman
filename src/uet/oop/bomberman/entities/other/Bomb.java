package uet.oop.bomberman.entities.other;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import uet.oop.bomberman.entities.Entity;

public class Bomb extends Entity {
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public Rectangle2D getBoundary() {
        return null;
    }

    @Override
    public void update() {

    }
}
