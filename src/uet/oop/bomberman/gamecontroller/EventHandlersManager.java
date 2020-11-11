package uet.oop.bomberman.gamecontroller;

import javafx.scene.input.KeyCode;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

import java.util.HashSet;

public class EventHandlersManager {
    public static void handleBomberMovements() {
        HashSet keyBoardInputs = EventHandler.getInputEventList();
        Bomber bomber = Sandbox.getBomber();
        if (keyBoardInputs.contains(KeyCode.UP.toString()) || keyBoardInputs.contains(KeyCode.W.toString())) {
            Sandbox.getGc().drawImage(Sprite.player_up.getFxImage(), 50,50);
            System.out.println("drawing!!!");
        }
        if (keyBoardInputs.contains(KeyCode.DOWN.toString()) || keyBoardInputs.contains(KeyCode.S.toString())) {
            Sandbox.getGc().drawImage(Sprite.player_down.getFxImage(), 100, 50);
            System.out.println("drawing!!!");
        }
        if (keyBoardInputs.contains(KeyCode.LEFT.toString()) || keyBoardInputs.contains(KeyCode.A.toString())) {
            Sandbox.getGc().drawImage(Sprite.player_left.getFxImage(), 50, 100);
            System.out.println("drawing!!!");
        }
        if (keyBoardInputs.contains(KeyCode.RIGHT.toString()) || keyBoardInputs.contains(KeyCode.D.toString())) {
            Sandbox.getGc().drawImage(Sprite.player_right.getFxImage(), 100, 100);
            System.out.println("drawing!!!");
        }
    }

    public static void handleCollisions() {

    }
}
