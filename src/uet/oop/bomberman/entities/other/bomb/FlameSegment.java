package uet.oop.bomberman.entities.other.bomb;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.other.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.scene.Sandbox;

public class FlameSegment extends Entity {
    public int countFlameSegment = 0;
    public int _direction;
    public boolean _last; // có phải là phần tử flame cuối cùng không
    public FlameSegment(int xUnit, int yUnit, Image img, int direction, boolean last) {
        super(xUnit, yUnit, img);
        _direction = direction;
        _last = last;
    }

    public void animationFlameSegment (int direction, boolean last){
        switch (direction){
            case 0:
                if (last){
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_vertical_top_last.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_vertical_top_last1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_vertical_top_last2.getFxImage();
                    }
                }
                else {
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_vertical.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_vertical1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_vertical2.getFxImage();
                    }
                }
                break;

            case 1:
                if (last){
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_horizontal_right_last.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_horizontal_right_last1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_horizontal_right_last2.getFxImage();
                    }
                }
                else {
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_horizontal.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_horizontal1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_horizontal2.getFxImage();
                    }
                }
                break;

            case 2:
                if (last){
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_vertical_down_last.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_vertical_down_last1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_vertical_down_last2.getFxImage();
                    }
                }
                else {
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_vertical.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_vertical1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_vertical2.getFxImage();
                    }
                }
                break;

            case 3:
                if (last){
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_horizontal_left_last.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_horizontal_left_last1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_horizontal_left_last2.getFxImage();
                    }
                }
                else {
                    if (countFlameSegment == 0){
                        img = Sprite.explosion_horizontal.getFxImage();
                    }
                    if (countFlameSegment == 40){
                        img = Sprite.explosion_horizontal1.getFxImage();
                    }
                    if (countFlameSegment == 80){
                        img = Sprite.explosion_horizontal2.getFxImage();
                    }
                }
                break;
        }

        countFlameSegment++;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    @Override
    public void update() {
        animationFlameSegment(this._direction,this._last);
        collideBomber();
    }

    /**
     * xét va chạm với player
     */
    public void collideBomber(){
        if (this.getBoundary().intersects(Sandbox.getBomber().getBoundary())){
            Sandbox.getBomber().killed();
        }
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }
}
