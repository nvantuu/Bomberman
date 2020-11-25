package uet.oop.bomberman.entities.other;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x , y , 32, 32);
    }

    @Override
    public void update() {

    }
}
