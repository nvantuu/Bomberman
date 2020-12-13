package uet.oop.bomberman.entities.other.item;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.Entity;

public class BombItem extends Item{
    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void Active() {
        this.active = true;
        GlobalConstants.addBombRate(1);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x + 5, y + 5, 22,22);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
