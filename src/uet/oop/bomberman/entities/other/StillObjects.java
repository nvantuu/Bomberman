package uet.oop.bomberman.entities.other;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * cái này chắc ko cần đến
 */
public abstract class StillObjects extends Entity {
    public StillObjects(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public Rectangle2D getBoundary() {
        return null;
    }

    @Override
    public void update() {

    }
}
