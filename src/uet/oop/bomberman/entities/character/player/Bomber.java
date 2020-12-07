package uet.oop.bomberman.entities.character.player;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemies.Enemy;
import uet.oop.bomberman.entities.other.Bomb;
import uet.oop.bomberman.entities.other.Flame;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.gamecontroller.EventHandlersManager;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private int countImage = 0;

    private List<Bomb> bombList = new ArrayList<Bomb>();
    private List<Flame> flameList = new ArrayList<Flame>();

    public void setBomb() {
        bombList.add(new Bomb(Math.round((float) x/32),
                Math.round((float) y/32), Sprite.bomb.getFxImage()));
        flameList.add(new Flame(Math.round((float) x/32),
                Math.round((float) y/32), Sprite.bomb_exploded.getFxImage()));
    }

    public void setBomberSpeed(int speed) {
        this.speed = speed;
    }

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        // update list bomb
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).countImageBomb == 200) {
                bombList.remove(i);
            } else {
                bombList.get(i).update();
            }
        }

        // update list flame
        for (int i = 0; i < flameList.size(); i++) {
            if (flameList.get(i).countImageFlame == 220) {
                flameList.remove(i);
            } else {
                flameList.get(i).update();
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        for (Bomb b : bombList) {
            b.render(gc);
        }
        for (Flame f : flameList) {
            f.render(gc);
        }
        gc.drawImage(img, x, y);
    }

    @Override
    public void killed() {

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

    public Entity createBomb() {
        return new Bomb(x, y, Sprite.bomb_2.getFxImage());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
