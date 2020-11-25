package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.scene.Sandbox;

public class GameLoop {
    static double oldGameTime;
    static double deltaTime;
    final static long startNanoTime = System.nanoTime();

    public static void start(GraphicsContext gc) {
        new AnimationTimer()
        {
            @Override
            public void handle(long now) {
                oldGameTime = now;
                now = (now - startNanoTime) / 1000000000;
                deltaTime = now - oldGameTime;
                updateGame();
                renderGame(gc);
            }
        }.start();
    }

    /**
     * Hàm vẽ các đối tượng
     */
    public static void renderGame(GraphicsContext gc) {
        gc.clearRect(0, 0, Sandbox.getCanvas().getWidth(), Sandbox.getCanvas().getHeight());
        Sandbox.getStillObjects().forEach(g -> g.render(gc));
        Sandbox.getEntities().forEach(g -> g.render(gc));
    }

    /**
     * Hàm cập nhật lại các đối tượng.
     */
    public static void updateGame() {
        Sandbox.getEntities().forEach(Entity::update);
    }
}
