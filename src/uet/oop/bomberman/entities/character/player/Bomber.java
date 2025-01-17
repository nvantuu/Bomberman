package uet.oop.bomberman.entities.character.player;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemies.Enemy;
import uet.oop.bomberman.entities.other.Grass;
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

        MyAudioPlayer placeSound = new MyAudioPlayer(MyAudioPlayer.PLACE_BOMB);
        placeSound.play();
    }

    @Override
    public void update() {
        if (!this.alive){
            afterKill();
            if (this.countImageAfterKill <= 0) {
                System.exit(0);
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
        MyAudioPlayer deadAudio = new MyAudioPlayer(MyAudioPlayer.DEAD);
        deadAudio.play();

        BombermanGame._musicPlayer.stop();
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
        return new Rectangle2D(x, y, 25, 32);
    }

    @Override
    public boolean canMove() {
        if (!this.alive){
            return false;
        }
        // xét va chạm với item và các vật thể
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
            if (e instanceof Item){
                if (collide(e)) {
                    MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.POWER_UP);
                    powerUpAudio.play();
                    ((Item) e).Active();
                    return true;
                }
            }
            if (collide(e)) {
                return false;
            }
        }
        // xét va chạm với enemy
        for (Entity e : Sandbox.getEntities()) {
            if (e instanceof Bomber) continue;
            if (collide(e)) {
                return false;
            }
        }
        // xét va chạm với flame, flamesegments
        for (Bomb obj : Sandbox.getBomber().getBombs()){
            for (Flame obj1 : obj.getFlames()){
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
        // xét va chạm với bom
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
        --this.countImageAfterKill;
    }

    private void animationsInSameDirection(Direction direction) {
        if (!this.alive){
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
