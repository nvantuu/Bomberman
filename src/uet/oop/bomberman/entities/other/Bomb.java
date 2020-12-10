package uet.oop.bomberman.entities.other;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    public int countImageBomb = 0;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);

    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }


    @Override
    public void update() {
        animationsBomb();
    }

    private void animationsBomb() {
        if (countImageBomb == 0) {
            img = Sprite.bomb.getFxImage();
        }
        else if (countImageBomb == 40) {
            img = Sprite.bomb_1.getFxImage();
        }
        else if (countImageBomb == 80) {
            img = Sprite.bomb_2.getFxImage();
        }
        countImageBomb++;
    }
}
