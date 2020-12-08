package uet.oop.bomberman.entities.other;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.other.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;

public class CanDestroy extends StillObjects{
    protected boolean _destroy = false;

    public CanDestroy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void addBelowSprite (Image image){
        img = image;
    }

    public boolean destroy(){
        return _destroy = true;
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
        if (e instanceof FlameSegment){
            this._destroy = true;
        }

        if (!(e instanceof Grass)){
            return true;
        }

        return false;
    }
}
