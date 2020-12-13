package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected boolean _removed = false;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract Rectangle2D getBoundary();

    public abstract void update();

    public Image getImg() {
        return img;
    }

    public int getX() {
        return Math.round((float) x / 32);
    }

    public int getY() {
        return Math.round((float) y / 32);
    }

    public int getXCanvas(){
        return x;
    }

    public int getYCanvas(){
        return y;
    }

    public abstract boolean collide (Entity e);
}
