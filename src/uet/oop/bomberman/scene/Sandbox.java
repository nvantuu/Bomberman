package uet.oop.bomberman.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.gamecontroller.EventHandler;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Sandbox {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    static Canvas canvas;
    static Group root;
    static Scene scene;
    static GraphicsContext gc;
    private static boolean sceneStarted;
    static {
        sceneStarted = false;
    }
    static Bomber bomber;

    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();

    public static void setupScene(){
        if(!sceneStarted){
            init();
            sceneStarted=true;
        }
    }

    private static void init() {
        root = new Group();
        scene = new Scene(root, GlobalConstants.SCENE_WIDTH, GlobalConstants.SCENE_HEIGHT);
        canvas = new Canvas(GlobalConstants.CANVAS_WIDTH, GlobalConstants.CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        GameLoop.start(gc);
        createMap();
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomber);
        EventHandler.catchEvent(scene);
    }

    private static void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }


    public static List<Entity> getEntities() {
        return entities;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static Canvas getCanvas() {
        return canvas;
    }

    public static Group getRoot() {
        return root;
    }

    public static Scene getScene() {
        return scene;
    }

    public static GraphicsContext getGc() {
        return gc;
    }

    public static Bomber getBomber() {
        return bomber;
    }

    public static void setBomber(Bomber newBomber) {
        bomber = newBomber;
    }
}
