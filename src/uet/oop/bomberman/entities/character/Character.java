package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Entity;

public abstract class Character extends Entity {

    protected int speed;

    protected  Direction currentDirection = Direction.LEFT;

    protected boolean alive = true;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();

    public abstract void move();

    public abstract boolean canMove();

    public abstract boolean collide(Entity e);

    public void move(int deltaX, int deltaY) {
        if (deltaX == 0 && deltaY == 0) {
            return;
        } else {
            this.x += deltaX;
            this.y += deltaY;
            if (!canMove()) {
                this.x -= deltaX;
                this.y -= deltaY;
                return;
            }
        }
    }

    public abstract void killed();
}
