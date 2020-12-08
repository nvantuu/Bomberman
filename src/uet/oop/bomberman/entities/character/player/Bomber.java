package uet.oop.bomberman.entities.character.player;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import jdk.nashorn.internal.objects.Global;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemies.Enemy;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.entities.other.LayeredEntity;
import uet.oop.bomberman.entities.other.bomb.Bomb;
import uet.oop.bomberman.gamecontroller.EventHandler;
import uet.oop.bomberman.gamecontroller.EventHandlersManager;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.entities.*;

import java.util.Iterator;
import java.util.List;

public class Bomber extends Character {
    private int countImage = 0;
    public int timeBetweenPutBombs = 0;
    public List<Bomb> bombs;

    public void setBomberSpeed(int speed) {
        this.speed = speed;
    }

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        //clearBomb();

        if (timeBetweenPutBombs < -7500)
            timeBetweenPutBombs = 0;
        else timeBetweenPutBombs--;

        detectPlaceBomb();
        //updateBombCollision();

        move();
    }

    @Override
    public void killed() {

    }

    @Override
    public void move() {
        EventHandlersManager.handleBomberEvents();
    }

    /**
     * kiểm tra xem có đúng là nhấn SPACE, thời gian giữa hai lần đặt bom còn ko và BOMB_RATE > 0
     */
    public void detectPlaceBomb(){
        if (EventHandler.getInputEventList().contains("SPACE") && timeBetweenPutBombs < -25 && GlobalConstants.BOMB_RATE > 0){
            this.placeBomb(this.getX(), this.getY());
            GlobalConstants.addBombRate(-1);
            timeBetweenPutBombs = 0;
        }
    }

    public void placeBomb(int x, int y){
        bombs.add(new Bomb(Math.round((float) x/32),Math.round((float) y/32), Sprite.bomb_1.getFxImage()));
    }

    /**
     * bom biến mất thì remove và BOMB_RATE tăng lên
     */
    /*public void clearBomb (){
        Iterator<Bomb> bs = this.bombs.iterator();

        Bomb b;
        while (bs.hasNext()){
            b = bs.next();
            if (b.isRemoved()){
                bs.remove();
                GlobalConstants.addBombRate(1);
                Sandbox.addStillObjects(new Grass(b.getX(), b.getY(), Sprite.grass.getFxImage()));
            }
        }
    }

     */

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
        return new Rectangle2D(x + 3, y + 3, 19, 26);
    }

    @Override
    public boolean canMove() {
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
            if (collide(e)) {
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

    private void animationsInSameDirection(Direction direction) {
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

    /*public void updateBombCollision(){
        for (int i = 0; i < this.bombs.size(); i++){
            if (timeBetweenPutBombs < -25) {
                this.bombs .get(i)._allowedToPassThru = false;
            }
        }
    }

     */
}
