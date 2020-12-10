package uet.oop.bomberman.entities.other;

import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public int countImageFlame = 0;
    Direction currentDirection = Direction.IN_PLACE;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, null);
    }

    public Flame(int xUnit, int yUnit, Image img, Direction direction) {
        super(xUnit, yUnit, null);
        this.currentDirection = direction;
    }

    @Override
    public void update() {
        switch (currentDirection) {
            case UP:
                animationsFlame(Sprite.explosion_vertical_top_last2.getFxImage(),
                                Sprite.explosion_vertical_top_last1.getFxImage(),
                                Sprite.explosion_vertical_top_last.getFxImage());
                break;
            case DOWN:
                animationsFlame(Sprite.explosion_vertical_down_last2.getFxImage(),
                                Sprite.explosion_vertical_down_last1.getFxImage(),
                                Sprite.explosion_vertical_down_last.getFxImage());
                break;

            case LEFT:
                animationsFlame(Sprite.explosion_horizontal_left_last2.getFxImage(),
                                Sprite.explosion_horizontal_left_last1.getFxImage(),
                                Sprite.explosion_horizontal_left_last.getFxImage());
                break;
            case RIGHT:
                animationsFlame(Sprite.explosion_horizontal_right_last2.getFxImage(),
                                Sprite.explosion_horizontal_right_last1.getFxImage(),
                                Sprite.explosion_horizontal_right_last.getFxImage());

                break;
            default:
                animationsFlame(Sprite.bomb_exploded2.getFxImage(),
                                Sprite.bomb_exploded1.getFxImage(),
                                Sprite.bomb_exploded.getFxImage());
                break;
        }
    }

    private void animationsFlame(Image i1, Image i2, Image i3) {
        if (countImageFlame == 110) {
            //img = Sprite.bomb_exploded.getFxImage();
            img = i1;
        }
        if (countImageFlame == 130) {
            //img = Sprite.bomb_exploded1.getFxImage();
            img = i2;
        }
        if (countImageFlame == 150) {
            //img = Sprite.bomb_exploded2.getFxImage();
            img = i3;
        }
        countImageFlame++;
    }
}
