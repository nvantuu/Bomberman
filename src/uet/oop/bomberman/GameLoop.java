package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
<<<<<<< HEAD
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.enemies.Enemy;
import uet.oop.bomberman.gamecontroller.EventHandlersManager;
=======
>>>>>>> 6d8b7c518b60032a061934f704099f1eae0a2b47
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.gamecontroller.EventHandlersManager;

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
        Sandbox.getBomber().render(gc);
    }

    /**
     * Hàm cập nhật lại các đối tượng.
     */
    public static void updateGame() {
<<<<<<< HEAD
        // Đối tương bomber quản lí list bom, flame, và vị trị của chính nó
        // các va chạm với các list nên được sử lí trước tại đây.
        Sandbox.getBomber().update();
        Sandbox.getStillObjects().forEach(Entity::update);
        Sandbox.getEntities().forEach(Entity::update);

=======
        // Trước khi update thì bắt sự kiện, cài đặt update
        EventHandlersManager.handleBomberMovements();

        //Đang duyệt trống, hàm k làm gì.
        //Sandbox.getEntities().forEach(Entity::update);
>>>>>>> 6d8b7c518b60032a061934f704099f1eae0a2b47
    }
}
