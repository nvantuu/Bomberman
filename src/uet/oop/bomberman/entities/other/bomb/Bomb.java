package uet.oop.bomberman.entities.other.bomb;

import com.sun.corba.se.spi.activation._ServerStub;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.GlobalConstants;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.enemies.Enemy;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.other.Brick;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.entities.other.LayeredEntity;
import uet.oop.bomberman.entities.other.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

import java.util.Random;

public class Bomb extends Entity {
    public int timeExplode = 120;
    public int timeAfter = 0;

    protected Flame[] _flame;
    protected boolean explode = false;
    public boolean _allowedToPassThru = true;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        if (Sandbox.getStillObjectsAt(x, y) instanceof LayeredEntity){
            LayeredEntity tmp = (LayeredEntity) Sandbox.getEntityAt(x, y);
            Sandbox.addStillObjects(new LayeredEntity(x, y, Sprite.grass.getFxImage(), tmp.getTopEntity()));
        }
        else {
            Sandbox.addStillObjects(new LayeredEntity(x, y, Sprite.bomb.getFxImage(), new Grass(x, y, Sprite.grass.getFxImage())));
        }
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    @Override
    public void update() {
        if (timeExplode > 0){
            timeExplode--;
        }
        else{
            if (!explode){
                exploxe();
            }
            else {
                updateFlame();
            }

            if (timeAfter > 0){
                timeAfter--;
            }
            else remove();
        }

        this.collide(Sandbox.getStillObjectsAt(this.x, this.y));

        animateBomb();
    }

    public void updateFlame(){
        for (int i = 0; i < _flame.length; i++){
            _flame[i].update();
        }
    }

    public void animateBomb(){
        if (timeExplode <= 120){
            img = Sprite.bomb_1.getFxImage();
        }
        else if (timeExplode <= 60){
            img = Sprite.bomb_2.getFxImage();
        }
        else if (timeExplode <= 10){
            img = Sprite.bomb.getFxImage();
        }
    }

    public void exploxe(){
        explode = true;
        int leftRadius = 0, rightRadius = 0, upRadius = 0, downRadius = 0;

        for (int i = this.getX() + 1; i <= this.getX()+GlobalConstants.BOMB_RADIUS; i++){
            if (Sandbox.getStillObjectsAt(i, this.getY()) instanceof LayeredEntity){
                LayeredEntity tmp =(LayeredEntity) Sandbox.getStillObjectsAt(i, this.getY());
                if (tmp.getTopEntity() instanceof Brick){
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                }
                else if (tmp.getTopEntity() instanceof Bomb){
                    Bomb b = (Bomb) tmp.getTopEntity();
                    if (!b.explode) b.exploxe();
                    break;
                }
            }
            if (!(Sandbox.getStillObjectsAt(i, this.getY()) instanceof Wall)){
                rightRadius++;
            }
            else break;
        }

        for (int i = this.getX() - 1; i >= this.getX()-GlobalConstants.BOMB_RADIUS; i--){
            if (Sandbox.getStillObjectsAt(i, this.getY()) instanceof LayeredEntity){
                LayeredEntity tmp =(LayeredEntity) Sandbox.getStillObjectsAt(i, this.getY());
                if (tmp.getTopEntity() instanceof Brick){
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                }
                else if (tmp.getTopEntity() instanceof Bomb){
                    Bomb b = (Bomb) tmp.getTopEntity();
                    if (!b.explode) b.exploxe();
                    break;
                }
            }
            if (!(Sandbox.getStillObjectsAt(i, this.getY()) instanceof Wall)){
                leftRadius++;
            }
            else break;
        }

        for (int i = this.getY() + 1; i <= this.getY()+GlobalConstants.BOMB_RADIUS; i++){
            if (Sandbox.getStillObjectsAt(this.getX(), i) instanceof LayeredEntity){
                LayeredEntity tmp =(LayeredEntity) Sandbox.getStillObjectsAt(this.getX(), i);
                if (tmp.getTopEntity() instanceof Brick){
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                }
                else if (tmp.getTopEntity() instanceof Bomb){
                    Bomb b = (Bomb) tmp.getTopEntity();
                    if (!b.explode) b.exploxe();
                    break;
                }
            }
            if (!(Sandbox.getStillObjectsAt(this.getX(), i) instanceof Wall)){
                downRadius++;
            }
            else break;
        }

        for (int i = this.getY() - 1; i <= this.getY()-GlobalConstants.BOMB_RADIUS; i--){
            if (Sandbox.getStillObjectsAt(this.getX(), i) instanceof LayeredEntity){
                LayeredEntity tmp =(LayeredEntity) Sandbox.getStillObjectsAt(this.getX(), i);
                if (tmp.getTopEntity() instanceof Brick){
                    Brick b = (Brick) tmp.getTopEntity();
                    b.destroy();
                    break;
                }
                else if (tmp.getTopEntity() instanceof Bomb){
                    Bomb b = (Bomb) tmp.getTopEntity();
                    if (!b.explode) b.exploxe();
                    break;
                }
            }
            if (!(Sandbox.getStillObjectsAt(this.getX(), i) instanceof Wall)){
                upRadius++;
            }
            else break;
        }

        // tao flame
        _flame = new Flame[4];
        _flame[0] = new Flame(this.getX(), this.getY(),Sprite.explosion_vertical2.getFxImage(), 0, upRadius);
        _flame[1] = new Flame(this.getX(), this.getY(),Sprite.explosion_vertical2.getFxImage(), 0, downRadius);
        _flame[2] = new Flame(this.getX(), this.getY(),Sprite.explosion_horizontal2.getFxImage(), 0, leftRadius);
        _flame[3] = new Flame(this.getX(), this.getY(),Sprite.explosion_horizontal2.getFxImage(), 0, rightRadius);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Flame || e instanceof FlameSegment){
            this.exploxe();
            return true;
        }
        if (e instanceof Enemy){
            return true;
        }
        if (!this._allowedToPassThru && e instanceof Bomber){
            return true;
        }

        return false;
    }
}
