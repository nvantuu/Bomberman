package uet.oop.bomberman.entities.character.enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.scene.Sandbox;

public class Oneal extends Enemy {
    public int maxStep = 16;
    public int step = maxStep;
    public int r;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    public void update() {
        move();
    }

    @Override
    public void move() {
        ranDomCurrentDirection();
        if (currentDirection == Direction.UP){
            if (canMove()){
                step--;
                move(0, -2);
            }
            else {
                step = 0;
            }
        }

        if (currentDirection == Direction.DOWN){
            if (canMove()){
                step--;
                move(0, 2);
            }
            else {
                step = 0;
            }
        }

        if (currentDirection == Direction.LEFT){
            if (canMove()){
                step--;
                move(-2, 0);
            }
            else {
                step = 0;
            }
        }

        if (currentDirection == Direction.RIGHT){
            if (canMove()){
                step--;
                move(2, 0);
            }
            else {
                step = 0;
            }
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

    @Override
    public boolean collide(Entity e) {
        //TODO if (e instanceof ...) -->
        return this.getBoundary().intersects(e.getBoundary());
    }

    @Override
    public void killed() {

    }


    public void ranDomCurrentDirection(){
        AIChase ai  = new AIChase(Sandbox.getBomber(), this);
        if (step <= 0){
            r = ai.calculateDirection();
            step = maxStep;
        }
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
