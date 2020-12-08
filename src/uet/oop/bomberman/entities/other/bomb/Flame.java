package uet.oop.bomberman.entities.other.bomb;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.entities.other.LayeredEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.entities.character.Character;

public class Flame extends Entity {
    public static Flame[] _flame;
    public int direction;
    public int radius;
    public int startX, startY;
    public FlameSegment[] flameSegments = new FlameSegment[0];

    public Flame(int xUnit, int yUnit, Image img, int direction, int radius) {
        super(xUnit, yUnit, img);
        startX = xUnit;
        startY = yUnit;
        this.direction = direction;
        this.radius = radius;
        createFlameSegments();
    }

    public void createFlameSegments(){
        flameSegments = new FlameSegment[calculatePermittedDistance()];

        if (direction == 0){ //up
            for (int i = 0; i < flameSegments.length; i++){
                if (i == flameSegments.length - 1){
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, true);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
                else {
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, false);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
            }
        }

        if (direction == 1){ //down
            for (int i = 0; i < flameSegments.length; i++){
                if (i == flameSegments.length - 1){
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, true);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
                else {
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, false);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
            }
        }

        if (direction == 2){ //left
            for (int i = 0; i < flameSegments.length; i++){
                if (i == flameSegments.length - 1){
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, true);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
                else {
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, false);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
            }
        }

        if (direction == 3){ // right
            for (int i = 0; i < flameSegments.length; i++){
                if (i == flameSegments.length - 1){
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, true);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
                else {
                    flameSegments[i] = new FlameSegment(this.getX(), this.getY() - i - 1, direction, false);

                    if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                        LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());
                        tmp.addToTop(flameSegments[i]);
                    }
                    else Sandbox.addEntities(flameSegments[i]);
                }
            }
        }
    }

    public int calculatePermittedDistance(){
        return radius;
    }

    public FlameSegment flameSegmentAt (int x, int y){
        for (int i = 0; i < flameSegments.length; i++){
            if (flameSegments[i].getX() == x && flameSegments[i].getY() == y){
                return flameSegments[i];
            }
        }
        return null;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    @Override
    public void update() {
        if (this.getBoundary().intersects(Sandbox.getBomber().getBoundary())){
            Sandbox.getBomber().collide(this);
        }

        for (int i = 0; i < flameSegments.length; i++){
            if (Sandbox.getBombsAt(flameSegments[i].getX(), flameSegments[i].getY()) != null){
                Sandbox.getBombsAt(flameSegments[i].getX(), flameSegments[i].getY()).collide(this);
            }
        }

        for (int i = 0; i < flameSegments.length; i++){
            if (Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY()) instanceof LayeredEntity){
                LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(flameSegments[i].getX(), flameSegments[i].getY());

                if (tmp.getTopEntity() instanceof FlameSegment){
                    while (!(tmp.getTopEntity() instanceof Grass)){
                        tmp.getTopEntity().remove();
                        tmp.update();
                    }
                }
                else if (tmp.getTopEntity() instanceof Grass){
                    Sandbox.addStillObjects(new Grass(flameSegments[i].getX(), flameSegments[i].getY(), Sprite.grass.getFxImage()));
                }
            }
        }

        if (Sandbox.getEntityAt(this.getX(), this.getY()) instanceof LayeredEntity){
            //LayeredEntity tmp = (LayeredEntity) Sandbox.getStillObjectsAt(this.x, this.y);
            Sandbox.addStillObjects(new Grass(this.x, this.y, Sprite.grass.getFxImage()));
        }

        for (Entity obj : Sandbox.getEntities()){
            if (obj instanceof FlameSegment){
                for (Entity obj1 : Sandbox.getEntities()){
                    if (obj1 instanceof Character){
                        if (obj.getBoundary().intersects(obj1.getBoundary())){
                            obj.collide(obj1);
                        }
                    }
                }
                Sandbox.addStillObjects(new Grass(obj.getX(), obj.getY(), Sprite.grass.getFxImage()));
            }
        }
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
