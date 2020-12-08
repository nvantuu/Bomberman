package uet.oop.bomberman.entities.other;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.other.bomb.Bomb;

import java.util.LinkedList;

/**
 * các entity xếp chồng lên nhau
 */
public class LayeredEntity extends Entity {
    protected LinkedList<Entity> _entities = new LinkedList<>();

    public LayeredEntity(int xUnit, int yUnit, Image img, Entity... entities) {
        super(xUnit, yUnit, img);

        for (int i = 0; i < entities.length; i++){
            _entities.add(entities[i]);

            if (i > 1){
                if (entities[i] instanceof CanDestroy){
                    ((CanDestroy) entities[i]).addBelowSprite(entities[i - 1].getImg());
                }
            }
        }
    }

    public Entity getTopEntity(){
        return _entities.getLast();
    }

    public Entity getBelowTopEntity(){
        if (this.getLayeredSize() >= 2){
            return _entities.get(this.getLayeredSize() - 2);
        }

        return null;
    }

    public void clearRemove(){
        Entity top = getTopEntity();
        if (top.isRemoved()){
            _entities.removeLast();
        }
    }

    public int getLayeredSize(){
        return _entities.size();
    }

    public void addToTop (Entity e){
        _entities.add(e);
    }

    @Override
    public Rectangle2D getBoundary() {
        return null;
    }

    @Override
    public void update() {
        clearRemove();
        getTopEntity().update();
    }

    @Override
    public boolean collide(Entity e) {
        Entity topMost = this.getTopEntity();
        if (!(topMost instanceof Grass))
        {
            return topMost.collide(e);
        }
        else if (this.getBelowTopEntity() instanceof Bomb)
        {
            return this.getBelowTopEntity().collide(e);
        }
        else return false;
    }
}
