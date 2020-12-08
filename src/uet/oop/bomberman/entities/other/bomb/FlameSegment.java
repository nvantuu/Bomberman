package uet.oop.bomberman.entities.other.bomb;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.character.Character;

public class FlameSegment extends Entity {
    public boolean last;


    public FlameSegment(int x, int y, int direction, boolean last){
        super(x, y);
        this.last = last;
        switch (direction)
        {
            case 0: // up
                if (!last)
                {
                    img = Sprite.explosion_vertical2.getFxImage();
                }
                else
                {
                    img = Sprite.explosion_vertical_top_last.getFxImage();
                }
                break;
            case 1: // down
                if (!last){
                    img = Sprite.explosion_vertical2.getFxImage();
                }
                else{
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                }
                break;
            case 2: // left
                if (!last){
                    img = Sprite.explosion_horizontal2.getFxImage();
                }
                else{
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                }
                break;
            case 3: // right
                if (!last){
                    img = Sprite.explosion_horizontal2.getFxImage();
                }
                else{
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                }
                break;
        }
    }

    public FlameSegment(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public Rectangle2D getBoundary() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Character){
            ((Character) e).killed();
            return true;
        }
        return false;
    }
}
