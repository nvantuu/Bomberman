package uet.oop.bomberman.entities.character.enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.entities.other.bomb.Bomb;
import uet.oop.bomberman.entities.other.bomb.Flame;
import uet.oop.bomberman.entities.other.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

public class Oneal extends Enemy {
    public int maxStep = 16;
    public int step = maxStep;
    public int r;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        speed = GlobalConstants.SPEED_ONEAL;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public void afterKill() {
        if (this.countImageAfterKill > 0){
            this.countImageAfterKill--;
        }
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }



    public void update() {
        if (!this.alive){
            afterKill();
        }
        move();
    }

    /**
     *  cứ đi chuyển được 1 ô hoặc không đi được thì random
     */
    @Override
    public void move() {
        ranDomCurrentDirection();
        if (currentDirection == Direction.UP){
            if (canMove()){
                step--;
                move(0, -speed);
            }
            else {
                step = 0;
            }
        }

        if (currentDirection == Direction.DOWN){
            if (canMove()){
                step--;
                move(0, speed);
            }
            else {
                step = 0;
            }
        }

        if (currentDirection == Direction.LEFT){
            if (canMove()){
                step--;
                move(-speed, 0);
            }
            else {
                step = 0;
            }
        }

        if (currentDirection == Direction.RIGHT){
            if (canMove()){
                step--;
                move(speed, 0);
            }
            else {
                step = 0;
            }
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
                return false;
            }
        }

        // xét va chạm với bom
        for (Bomb e : Sandbox.getBomber().getBombs()){
            if (collide(e)){
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
                if (collide(e)){
                    ((Bomber) e).killed();
                    return true;
                }
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
        this.alive = false;
        img = Sprite.oneal_dead.getFxImage();
    }

    /**
     * nếu không thể di chuyển thì sẽ nhận một giá trị random, sau đó nhận direction
     */
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
