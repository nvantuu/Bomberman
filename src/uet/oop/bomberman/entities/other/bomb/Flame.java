package uet.oop.bomberman.entities.other.bomb;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.other.Brick;
import uet.oop.bomberman.entities.other.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

import java.util.ArrayList;
import java.util.List;

public class Flame extends Entity {
    public int countImageFlame = 0;
    public List<FlameSegment> flameSegments = new ArrayList<>();

    public Flame(int xUnit, int yUnit,Image img) {
        super(xUnit, yUnit, null);
        setFlameSegments();
    }

    private void animationFlame() {
        if (countImageFlame == 10) {
            img = Sprite.bomb_exploded.getFxImage();
        }
        if (countImageFlame == 40) {
            img = Sprite.bomb_exploded1.getFxImage();
        }
        if (countImageFlame == 80) {
            img = Sprite.bomb_exploded2.getFxImage();
        }
        countImageFlame++;
    }

    public void setFlameSegments(){
        createFlameSegmentsUp();
        createFlameSegmentsDown();
        createFlameSegmentsLeft();
        createFlameSegmentsRight();
    }

    /**
     *  tạo ra flamesegment hướng lên trên
     */
    public void createFlameSegmentsUp(){
        FlameSegment[] flameSegmentsUp = new FlameSegment[calculatePermittedDistance(0)];

        boolean _last = false;
        int _x = this.getX();
        int _y = this.getY();
        for (int i = 0; i < flameSegmentsUp.length; i++){
            _y--;

            if (i == flameSegmentsUp.length - 1){
                _last = true;
                flameSegmentsUp[i] = new FlameSegment(_x, _y, Sprite.explosion_vertical_top_last2.getFxImage(), 0, _last);
            }
            else {
                flameSegmentsUp[i] = new FlameSegment(_x, _y, Sprite.explosion_vertical2.getFxImage(), 0, _last);
            }
        }

        // xét có va chạm với brick không
        if(flameSegmentsUp.length < GlobalConstants.BOMB_RADIUS || flameSegmentsUp.length == 0){
            for (Entity obj : Sandbox.getStillObjects()){
                if (obj instanceof Brick && obj.getX() == _x && obj.getY() == _y - 1){
                    ((Brick) obj).Destroy();
                }
            }
        }

        for (int i = 0; i < flameSegmentsUp.length; i++){
            this.flameSegments.add(flameSegmentsUp[i]);
        }
    }

    /**
     *  tạo ra flamesegment hướng xuống dưới
     */
    public void createFlameSegmentsDown(){
        FlameSegment[] flameSegmentsDown = new FlameSegment[calculatePermittedDistance(2)];

        boolean _last = false;
        int _x = this.getX();
        int _y = this.getY();
        for (int i = 0; i < flameSegmentsDown.length; i++){
            _y++;

            if (i == flameSegmentsDown.length - 1){
                _last = true;
                flameSegmentsDown[i] = new FlameSegment(_x, _y, Sprite.explosion_vertical_down_last2.getFxImage(), 2, _last);
            }
            else {
                flameSegmentsDown[i] = new FlameSegment(_x, _y, Sprite.explosion_vertical2.getFxImage(), 2, _last);
            }
        }

        // xét có va chạm với brick không
        if(flameSegmentsDown.length < GlobalConstants.BOMB_RADIUS || flameSegmentsDown.length == 0){
            for (Entity obj : Sandbox.getStillObjects()){
                if (obj instanceof Brick && obj.getX() == _x && obj.getY() == _y + 1){
                    ((Brick) obj).Destroy();
                }
            }
        }

        for (int i = 0; i < flameSegmentsDown.length; i++){
            this.flameSegments.add(flameSegmentsDown[i]);
        }
    }

    /**
     *  tạo ra flamessegment hướng sang trái
     */
    public void createFlameSegmentsLeft(){
        FlameSegment[] flameSegmentsLeft = new FlameSegment[calculatePermittedDistance(3)];

        boolean _last = false;
        int _x = this.getX();
        int _y = this.getY();
        for (int i = 0; i < flameSegmentsLeft.length; i++){
            _x--;

            if (i == flameSegmentsLeft.length - 1){
                _last = true;
                flameSegmentsLeft[i] = new FlameSegment(_x, _y, Sprite.explosion_horizontal_left_last2.getFxImage(), 3, _last);
            }
            else {
                flameSegmentsLeft[i] = new FlameSegment(_x, _y, Sprite.explosion_horizontal2.getFxImage(), 3, _last);
            }
        }

        // xét có va chạm với brick không
        if(flameSegmentsLeft.length < GlobalConstants.BOMB_RADIUS || flameSegmentsLeft.length == 0){
            for (Entity obj : Sandbox.getStillObjects()){
                if (obj instanceof Brick && obj.getX() == _x - 1 && obj.getY() == _y){
                    ((Brick) obj).Destroy();
                }
            }
        }

        for (int i = 0; i < flameSegmentsLeft.length; i++){
            this.flameSegments.add(flameSegmentsLeft[i]);
        }
    }

    /**
     *  tạo ra flamessegment hướng sang phải
     */
    public void createFlameSegmentsRight(){
        FlameSegment[] flameSegmentsRight = new FlameSegment[calculatePermittedDistance(1)];

        boolean _last = false;
        int _x = this.getX();
        int _y = this.getY();
        for (int i = 0; i < flameSegmentsRight.length; i++){
            _x++;

            if (i == flameSegmentsRight.length - 1){
                _last = true;
                flameSegmentsRight[i] = new FlameSegment(_x, _y, Sprite.explosion_horizontal_right_last2.getFxImage(), 1, _last);
            }
            else {
                flameSegmentsRight[i] = new FlameSegment(_x, _y, Sprite.explosion_horizontal2.getFxImage(), 1, _last);
            }
        }

        // xét có va chạm với brick không
        if(flameSegmentsRight.length < GlobalConstants.BOMB_RADIUS || flameSegmentsRight.length == 0){
            for (Entity obj : Sandbox.getStillObjects()){
                if (obj instanceof Brick && obj.getX() == _x + 1 && obj.getY() == _y){
                    ((Brick) obj).Destroy();
                }
            }
        }

        for (int i = 0; i < flameSegmentsRight.length; i++){
            this.flameSegments.add(flameSegmentsRight[i]);
        }
    }

    /**
     * tính toán độ dài của mỗi flamesegment
     */
    public int calculatePermittedDistance(int direction){
        int radius = 0;
        int _x = this.getX();
        int _y = this.getY();

        while (radius < GlobalConstants.getBombRadius()){
            if (direction == 0) _y--;
            if (direction == 1) _x++;
            if (direction == 2) _y++;
            if (direction == 3) _x--;

            Entity a = Sandbox.getStillObjectsAt(_x, _y);

            if (a instanceof Wall){
                break;
            }

            if (a instanceof Brick){
                break;
            }

            radius++;
        }

        return radius;
    }

    public void render (GraphicsContext gc){
        for (FlameSegment fu : flameSegments) {
            fu.render(gc);
        }
        gc.drawImage(img, x, y);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    @Override
    public void update() {
        for (int i = 0; i < flameSegments.size(); i++){
            if (flameSegments.get(i).countFlameSegment == 180){
                flameSegments.remove(i);
            }
            else flameSegments.get(i).update();
        }
        animationFlame();
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

    public List<FlameSegment> getFlameSegments() {
        return flameSegments;
    }
}
