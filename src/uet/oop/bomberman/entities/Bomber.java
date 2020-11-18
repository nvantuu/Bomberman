package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

public class Bomber extends Entity {
    Direction currentDirection = Direction.DOWN;
    private static int indexImage = 0;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public void move(int deltaX, int deltaY, Direction direction ) {
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
            animationsOnSameDirection(direction);
        }
    }

    private boolean canMove() {
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
            if (intersects(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Rectangle2D getBoundary() {

        // To make it feel like the bomber moves easily,
        // the rectangle around the bomber slightly smaller than the bomber
        return new Rectangle2D(x + 3 , y + 3, 18, 26);
    }

    private boolean intersects(Entity that) {
        return this.getBoundary().intersects(that.getBoundary());
    }

    protected void animationsOnSameDirection(Direction direction) {
        switch (direction) {
            case UP:
                if (currentDirection != Direction.UP) {
                    img = Sprite.player_up.getFxImage();
                    currentDirection = Direction.UP;
                }
                else if (indexImage == 0) {
                    img = Sprite.player_up.getFxImage();
                }
                else if (indexImage == 10) {
                    img = Sprite.player_up_1.getFxImage();
                }
                else if (indexImage == 20) {
                    img = Sprite.player_up_2.getFxImage();
                }
                indexImage++;
                if (indexImage == 31) {
                    indexImage = 0;
                }
                break;

            case DOWN:
                if (currentDirection != Direction.DOWN) {
                    img = Sprite.player_down.getFxImage();
                    currentDirection = Direction.DOWN;
                }
                else if (indexImage == 0){
                    img = Sprite.player_down.getFxImage();
                }
                else if (indexImage == 10) {
                    img = Sprite.player_down_1.getFxImage();
                }
                else if (indexImage == 20) {
                    img = Sprite.player_down_2.getFxImage();
                }
                indexImage++;
                if (indexImage == 31) {
                    indexImage = 0;
                }
                break;

            case LEFT:
                if (currentDirection != Direction.LEFT) {
                    img = Sprite.player_left.getFxImage();
                    currentDirection = Direction.LEFT;
                }
                else if (indexImage == 0) {
                    img = Sprite.player_left.getFxImage();
                }
                else if (indexImage == 10) {
                    img = Sprite.player_left_1.getFxImage();
                }
                else if (indexImage == 20) {
                    img = Sprite.player_left_2.getFxImage();
                }
                indexImage++;
                if (indexImage == 31){
                    indexImage = 0;
                }
                break;

            case RIGHT:
                if (currentDirection != Direction.RIGHT) {
                    img = Sprite.player_right.getFxImage();
                    currentDirection = Direction.RIGHT;
                }
                else if (indexImage == 0) {
                    img = Sprite.player_right.getFxImage();
                }
                else if (indexImage == 10) {
                    img = Sprite.player_right_1.getFxImage();
                }
                else if (indexImage == 20) {
                    img = Sprite.player_right_2.getFxImage();
                }
                indexImage++;
                if (indexImage == 31) {
                    indexImage = 0;
                }
                break;
        }
    }
}
