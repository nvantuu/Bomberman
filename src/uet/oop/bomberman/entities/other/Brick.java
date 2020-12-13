package uet.oop.bomberman.entities.other;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public boolean destroy = false;
    public int countImageBrick = 111;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    public void Destroy (){
        destroy = true;
    }

    public void afterDestroy(){
        if (countImageBrick > 0){
            countImageBrick--;
        }
        if (countImageBrick == 10){
            img = Sprite.brick_exploded2.getFxImage();
        }
        else if (countImageBrick == 50){
            img = Sprite.brick_exploded1.getFxImage();
        }
        else if (countImageBrick == 110){
            img = Sprite.brick_exploded.getFxImage();
        }
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D( x , y , 32, 32);
    }

    @Override
    public void update() {
        if (destroy){
            afterDestroy();
        }
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public boolean isDestroy(){
        return this.destroy;
    }
}
