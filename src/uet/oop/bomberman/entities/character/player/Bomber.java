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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bomber extends Character {
    private int countImage = 0;
    private int bombCount = 1;

    private boolean alive = true;

    protected List<Bomb> bombList = new ArrayList<Bomb>();
    protected List<Flame> flameList = new ArrayList<Flame>();

    public void setBombAndFlame() {
        int xUnit = Math.round((float) x/32);
        int yUnit = Math.round((float) y/32);

        bombList.add(new Bomb(xUnit, yUnit, Sprite.bomb.getFxImage()));
        flameList.add(new Flame(xUnit, yUnit, Sprite.bomb_exploded.getFxImage()));
        // check xUnit - 1, yUnit
        // check XUnit + 1, yUnit
        // check xUnit, yUnit + 1
        // check xUnit, yUnit -1

        if (checkObjInPos(xUnit -1, yUnit) != '#' ) {
            flameList.add(new Flame(xUnit -1, yUnit,
                    Sprite.explosion_vertical.getFxImage(), Direction.LEFT));
        }
        if (checkObjInPos(xUnit +1, yUnit) != '#') {
            flameList.add(new Flame(xUnit + 1, yUnit,
                    Sprite.explosion_vertical.getFxImage(), Direction.RIGHT));
        }
        if (checkObjInPos(xUnit, yUnit-1) != '#') {
            flameList.add(new Flame(xUnit, yUnit -1,
                    Sprite.explosion_horizontal.getFxImage(), Direction.UP));
        }
        if (checkObjInPos(xUnit, yUnit-1) != '#') {
            flameList.add(new Flame(xUnit, yUnit + 1,
                    Sprite.explosion_horizontal.getFxImage(), Direction.DOWN));
        }
    }

    public char checkObjInPos(int j, int i) {
        String s = null;
        try {
            Scanner sc = new Scanner(new File("D:\\Game\\Bomberman\\res\\levels\\Level1.txt"));
            s = sc.nextLine();
            while (i-- >= 0) {
                s = sc.nextLine();
            }
        } catch (FileNotFoundException f) {
            System.out.println("Khong load dc ban do");
        }
        return s.charAt(j);
    }

    public void setBomberSpeed(int speed) {
        this.speed = speed;
    }

    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        EventHandlersManager.handleBomberEvents();

        if (!alive && countImage == 60) {
            System.exit(1);
        }

        // update list bomb
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).countImageBomb == 110) {
                bombList.remove(i);
                incrementBombCount();
            } else {
                bombList.get(i).update();
            }
        }

        // update list flame
        for (int i = 0; i < flameList.size(); i++) {
            if (flameList.get(i).countImageFlame == 180) {
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
        countImage = 0;
        alive = false;
    }

    @Override
    public void move() {

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

        // hoạt ảnh chuyển động nhân vật theo hướng đi hiện tại
        animationsInSameDirection(direction);
    }

    // To make it feel like the bomber moves easily,
    // the rectangle around the bomber slightly smaller than the bomber
    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x + 2, y + 3, 21, 27);
    }

    @Override
    public boolean canMove() {
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
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

    public List<Flame> getFlameList() {
        return flameList;
    }

    public boolean canSetBomb() {
        return bombCount > 0;
    }

    public void incrementBombCount(){
        ++bombCount;
    }

    public void decrementBombCount(){
        --bombCount;
    }

}
