package uet.oop.bomberman.entities.character.enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.entities.other.bomb.Bomb;
import uet.oop.bomberman.entities.other.bomb.Flame;
import uet.oop.bomberman.entities.other.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

import java.util.Random;

public class Balloon extends Enemy {
    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        ranDomCurrentDirection();
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    public void update() {
        if (!this.alive){
            afterKill();
        }
        move();
    }

    @Override
    public void afterKill(){
        if (this.countImageAfterKill > 0){
            this.countImageAfterKill--;
        }
    }

    @Override
    public void killed() {
        this.alive = false;
        this.img = Sprite.balloom_dead.getFxImage();
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
        if (!this.alive){
            return false;
        }

        // xét va chạm với vật thể không chuyển động
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
            if (collide(e)) {
                ranDomCurrentDirection();
                return false;
            }
        }

        // xét va chạm với bomb
        for (Bomb e : Sandbox.getBomber().getBombs()){
            if (collide(e)){
                ranDomCurrentDirection();
                return false;
            }
        }

        // xét va chạm với flame
        for (Bomb obj : Sandbox.getBomber().getBombs()){
            for (Flame obj1 : obj.getFlames()){
                for (FlameSegment e : obj1.getFlameSegments()){
                    if (collide(e)){
                        killed();
                        return false;
                    }
                }
            }
        }

        // xét va chạm với player
        for (Entity e : Sandbox.getEntities()){
            if (e instanceof Bomber){
                if (collide(e)) {
                    ((Bomber) e).killed();
                    return true;
                }
            }
        }
        return true;
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
