package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.gamecontroller.EventHandlersManager;

public class GameLoop {
    private static GraphicsContext graphicsContext;
    public static void start(GraphicsContext gc) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphicsContext = gc;
                updateGame();
                renderGame(graphicsContext);
            }
        };
        timer.start();
    }

    public static void renderGame(GraphicsContext gc) {
        gc.clearRect(0, 0, Sandbox.getCanvas().getWidth(), Sandbox.getCanvas().getHeight());
        Sandbox.getStillObjects().forEach(g -> g.render(gc));
        Sandbox.getEntities().forEach(g -> g.render(gc));
        EventHandlersManager.handleBomberMovements();

    }

    public static void updateGame() {
        Sandbox.getEntities().forEach(Entity::update);

    }
}
