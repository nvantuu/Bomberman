package uet.oop.bomberman.entities.character.enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.other.Flame;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.scene.Sandbox;

import java.util.Random;

public class Balloon extends Enemy {

    public boolean alive = true;

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        ranDomCurrentDirection();
    }

    public void update() {
        move();
    }

    @Override
    public void killed() {

    }

    @Override
    public void move() {
        switch (currentDirection) {
            case UP:
                move(0, -1);
                break;

            case DOWN:
                move(0, 1);
                break;

            case LEFT:
                move(-1, 0);
                break;
            case RIGHT:
                move(1, 0);
                break;
            default: break;
        }
    }

    @Override
    public boolean canMove() {
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
            if (collide(e)) {
                ranDomCurrentDirection();
                return false;
            }
        }
        return true;
    }

    public boolean isAlive() {
        return alive;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x , y , 32, 32);
    }

    @Override
    public boolean collide(Entity e) {
        //TODO if (e instanceof ...) -->
        return this.getBoundary().intersects(e.getBoundary());
    }

    public void ranDomCurrentDirection() {
        Random random = new Random();
        int r = random.nextInt(4);
        if (r == 0) {
            currentDirection = Direction.UP;
        }
        if (r == 1) {
            currentDirection = Direction.DOWN;
        }
        if (r == 2) {
            currentDirection = Direction.LEFT;
        }
        if (r == 3) {
            currentDirection = Direction.RIGHT;
        }
    }
}
