package uet.oop.bomberman.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import uet.oop.bomberman.GameLoop;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.character.enemies.Balloon;
import uet.oop.bomberman.entities.character.enemies.Oneal;
import uet.oop.bomberman.entities.other.Brick;
import uet.oop.bomberman.entities.other.Grass;
import uet.oop.bomberman.entities.other.Wall;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.other.item.BombItem;
import uet.oop.bomberman.entities.other.item.FlameItem;
import uet.oop.bomberman.entities.other.item.Item;
import uet.oop.bomberman.gamecontroller.EventHandler;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Sandbox {
    static Canvas canvas;
    static Group root;
    static Scene scene;
    static GraphicsContext gc;
    private static boolean sceneStarted;
    static {
        sceneStarted = false;
    }
    static Bomber bomber;

    private static final List<Entity> entities = new ArrayList<>();
    private static final List<Entity> stillObjects = new ArrayList<>();

    public static void setupScene(){
        if(!sceneStarted){
            init();
            sceneStarted=true;
        }
    }

    private static void init() {
        root = new Group();
        scene = new Scene(root);
        canvas = new Canvas();
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        GameLoop.start(gc);
        try {
            createMap(new File("D:\\Game\\Bomberman\\res\\levels\\Level1.txt"));
        } catch (IOException e) {
            System.err.println("Unable to load map file");
            System.exit(1);
        }

        // The event capture task was created at the end, when the objects were completely initialized.
        EventHandler.catchInputEvents(scene);
    }

    private static void createMap(File file) throws IOException {
        Scanner sc = new Scanner(file);
        String str = sc.nextLine();
        String[] numeral = str.split("\\s+");
        int level = Integer.parseInt(numeral[0]);
        int row = Integer.parseInt(numeral[1]);
        int col = Integer.parseInt(numeral[2]);

        // setup Canvas depending on the map.
        GlobalConstants.CANVAS_HEIGHT = row * 32;
        GlobalConstants.CANVAS_WIDTH = col * 32;
        canvas.setWidth(GlobalConstants.CANVAS_WIDTH);
        canvas.setHeight(GlobalConstants.CANVAS_HEIGHT);

        for (int i = 0; i < row; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < col; j++) {
                Entity object;
                switch (s.charAt(j)) {
                    case '#':
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                        break;

                    case '*':  // Brick
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(object);
                        break;

                    case 'p':
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        setBomber(j, i);
                        break;

                    case 'b':
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(object);
                        break;

                    case 'f':
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                        stillObjects.add(object);
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(object);
                        break;

                    case '1': // Balloon
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        entities.add(object);
                        break;

                    case '2': // Oneal
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                        entities.add(object);
                        break;

                    default:
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        break;

                }
            }
        }
        sc.close();

    }

    private static void setBomber(int x, int y) {
        bomber = new Bomber(x, y, Sprite.player_down.getFxImage());
        bomber.setBomberSpeed(GlobalConstants.SPEED_BOMBER);
        entities.add(bomber);
    }


    public static List<Entity> getEntities() {
        return entities;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static Entity getStillObjectsAt (int x, int y){
        for (Entity obj : stillObjects){
            if (obj instanceof Grass) continue;
            if (obj instanceof Item) continue;
            if (x == obj.getX() && y == obj.getY()){
                return obj;
            }
        }

        return null;
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
}
