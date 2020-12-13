package uet.oop.bomberman.entities.character.player;

import com.sun.org.apache.bcel.internal.generic.IFLE;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemies.Enemy;
import uet.oop.bomberman.entities.other.Brick;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.entities.other.Wall;
import uet.oop.bomberman.entities.other.bomb.Bomb;
import uet.oop.bomberman.entities.other.item.Item;
import uet.oop.bomberman.gamecontroller.EventHandlersManager;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.entities.other.bomb.Flame;
import uet.oop.bomberman.entities.other.bomb.FlameSegment;
import uet.oop.bomberman.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private int countImage = 0;
    public int timeBetweenPutBombs = 0;
    public boolean alive = true;
    public List<Bomb> bombs = new ArrayList<>();

    public void setBomberSpeed(int speed) {
        this.speed = speed;
    }

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setBomb(){
        if (GlobalConstants.getBombRate() > 0 && timeBetweenPutBombs < 0) {
            bombs.add(new Bomb(this.getX(), this.getY(), Sprite.bomb.getFxImage()));
            GlobalConstants.addBombRate(-1);
        }

        timeBetweenPutBombs = 30;
    }

    @Override
    public void update() {
        if (!this.alive){
            afterKill();
            if (countImageAfterKill <= 0) {
                System.exit(1);
            }
        }
        else {
            timeBetweenPutBombs--;
            if (timeBetweenPutBombs < -7500) {
                timeBetweenPutBombs = -1;
            }

            for (int i = 0; bombs.size() > i; i++) {
                if (bombs.get(i).countImageBomb == 220) {
                    bombs.remove(i);
                } else {
                    bombs.get(i).update();
                }
            }
        }
        move();
    }

    @Override
    public void render(GraphicsContext gc) {
        for (Bomb b : bombs) {
            b.render(gc);
        }
        gc.drawImage(img, x, y);
    }

    @Override
    public void killed() {
        this.alive = false;
    }

    @Override
    public void move() {
        EventHandlersManager.handleBomberEvents();
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                move(0, -speed);
                break;
            case DOWN:
                move(0, speed);
                break;
            case RIGHT:
                move(speed, 0);
                break;
            case LEFT:
                move(-speed, 0);
                break;
            default:
                move(0, 0);
                break;
        }
        animationsInSameDirection(direction);
    }

    // To make it feel like the bomber moves easily,
    // the rectangle around the bomber slightly smaller than the bomber
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    @Override
    public boolean canMove() {
        if (!this.alive){
            return false;
        }
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
            if (e instanceof Item){
                if (collide(e)) {
                    ((Item) e).Active();
                    return true;
                }
            }
            if (collide(e)) {
                return false;
            }
        }
        for (Entity e : Sandbox.getEntities()) {
            if (e instanceof Bomber) continue;
            if (collide(e)) {
                return false;
            }
        }
        for (Bomb obj : Sandbox.getBomber().getBombs()){
            for (Flame obj1 : obj.getFlames()){
                if (obj1.collide(this)){
                    this.alive = false;
                    killed();
                    return false;
                }
                if (collide(obj1)){
                    this.alive = false;
                    killed();
                    return false;
                }

                for (FlameSegment e : obj1.getFlameSegments()){
                    if (collide(e)){
                        this.alive = false;
                        killed();
                        return false;
                    }
                }
            }
        }
        for (Bomb e : Sandbox.getBomber().getBombs()){
            if (e.collide(this)) {
                return true;
            }
            if (collide(e)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO if (e instanceof ... ) -->

        boolean tf = this.getBoundary().intersects(e.getBoundary());
        if (tf) {
            if (e instanceof Enemy) {
                killed();
            }
        }
        return tf;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public void afterKill() {
        if (this.countImageAfterKill == 120){
            img = Sprite.player_dead1.getFxImage();
        }
        else if (this.countImageAfterKill == 60){
            img = Sprite.player_dead2.getFxImage();
        }
        else if (this.countImageAfterKill == 20){
            img = Sprite.player_dead3.getFxImage();
        }
        countImageAfterKill--;
    }

    private void animationsInSameDirection(Direction direction) {
        if (!alive) {
            img = Sprite.player_dead1.getFxImage();
            if (countImage == 20) {
                img = Sprite.player_dead2.getFxImage();
            }
            else if (countImage == 40) {
                img = Sprite.player_dead3.getFxImage();
            }
            countImage ++;
            return;
        }

        switch (direction) {
            case UP:
                if (countImage == 0) {
                    img = Sprite.player_up.getFxImage();
                }
                else if (countImage == 10) {
                    img = Sprite.player_up_1.getFxImage();
                }
                else if (countImage == 20) {
                    img = Sprite.player_up_2.getFxImage();
                }
                countImage++;
                if (countImage == 31) {
                    countImage = 0;
                }
                break;

            case DOWN:
                if (countImage == 0){
                    img = Sprite.player_down.getFxImage();
                }
                else if (countImage == 10) {
                    img = Sprite.player_down_1.getFxImage();
                }
                else if (countImage == 20) {
                    img = Sprite.player_down_2.getFxImage();
                }
                countImage++;
                if (countImage == 31) {
                    countImage = 0;
                }
                break;

            case LEFT:
                if (countImage == 0) {
                    img = Sprite.player_left.getFxImage();
                }
                else if (countImage == 10) {
                    img = Sprite.player_left_1.getFxImage();
                }
                else if (countImage == 20) {
                    img = Sprite.player_left_2.getFxImage();
                }
                countImage++;
                if (countImage == 31){
                    countImage = 0;
                }
                break;

            case RIGHT:
                if (countImage == 0) {
                    img = Sprite.player_right.getFxImage();
                }
                else if (countImage == 10) {
                    img = Sprite.player_right_1.getFxImage();
                }
                else if (countImage == 20) {
                    img = Sprite.player_right_2.getFxImage();
                }
                countImage++;
                if (countImage == 31) {
                    countImage = 0;
                }
                break;
        }
    }

    public List<Bomb> getBombs() {
        return bombs;
    }
}
