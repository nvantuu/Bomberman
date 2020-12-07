package uet.oop.bomberman.entities.character.enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

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

    @Override
    public void move() {

    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void killed() {

    }
}
