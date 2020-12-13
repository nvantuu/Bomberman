package uet.oop.bomberman.entities.other.bomb;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.entities.character.Character;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    public int timeToExplode = 120;
    public boolean explode = false;
    public Image img;
    public int countImageBomb = 0;
    public boolean through = true;
    public List<Flame> flames = new ArrayList<>();

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public void explode(){
        explode = true;
        for(Entity e : Sandbox.getEntities()){
            if (e.getX() == this.getX() && e.getY() == this.getY()){
                ((Character) e).killed();
            }
        }
        flames.add(new Flame(this.getX(), this.getY(), img));
        GlobalConstants.addBombRate(1);

        MyAudioPlayer explodeAudio = new MyAudioPlayer(MyAudioPlayer.EXPLOSION);
        explodeAudio.play();
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, 32, 32);
    }

    public void render(GraphicsContext gc){
        if (explode){
            img = Sprite.bomb_exploded.getFxImage();
            renderFlame(Sandbox.getGc());
        }
        gc.drawImage(img, x, y);
    }

    public void animationBomb(){
        if (countImageBomb == 0){
            img = Sprite.bomb.getFxImage();
        }
        else if (countImageBomb == 40){
            img = Sprite.bomb_1.getFxImage();
        }
        else if (countImageBomb == 80){
            img = Sprite.bomb_2.getFxImage();
        }
        countImageBomb++;
    }

    public void renderFlame(GraphicsContext gc){
        for (Flame f : flames) {
            f.render(gc);
        }
        gc.drawImage(img, x, y);
    }

    public void updateFlame() {
        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).countImageFlame == 180) {
                flames.remove(i);
            } else {
                flames.get(i).update();
            }
        }
    }

    @Override
    public void update() {
        if (timeToExplode > 1){
            timeToExplode--;
        }
        else {
            if (!explode){
                explode();
            }
            else {
                updateFlame();
            }
        }
        animationBomb();
    }

    /**
     * xét va chạm với player khi vừa mới đặt bom
     */
    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber){
            int diffX = e.getXCanvas() - this.getXCanvas();
            int diffY = e.getYCanvas() - this.getYCanvas();

            if (!(diffX < 32 && diffX > -26 && Math.abs(diffY) < 32)){
                through = false;
            }
        }

        return through;
    }

    public List<Flame> getFlames() {
        return flames;
    }
}
