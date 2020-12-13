package uet.oop.bomberman.entities.other.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public  abstract class Item extends Entity {
    protected boolean active = false;

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract boolean isActive();

    public abstract void Active();
}
