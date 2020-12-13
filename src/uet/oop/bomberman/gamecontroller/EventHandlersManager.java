package uet.oop.bomberman.gamecontroller;

import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.other.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EventHandlersManager {
    public static void handleBomberEvents() {

        ArrayList<String> keyBoardInputs = EventHandler.getInputEventList();

        Bomber player = Sandbox.getBomber();

        if (keyBoardInputs.contains("UP") || keyBoardInputs.contains("W")) {
            player.move(Direction.UP);
            //Sandbox.getBomber().setCurrentDirection(Direction.UP);
        }

        if (keyBoardInputs.contains("DOWN") || keyBoardInputs.contains("S")) {
            player.move(Direction.DOWN);
        }

        if (keyBoardInputs.contains("LEFT") || keyBoardInputs.contains("A")) {
            player.move(Direction.LEFT);
        }

        if (keyBoardInputs.contains("RIGHT") || keyBoardInputs.contains("D")) {
            player.move(Direction.RIGHT);
        }

        if (keyBoardInputs.contains("SPACE")) {
<<<<<<< HEAD
            Sandbox.getBomber().setBomb();
=======
            if (player.canSetBomb()) {
                player.setBombAndFlame();
                player.decrementBombCount();
            }

>>>>>>> 2ed22847d1aa2dad65c484c8374ce25198219cc8
        }

        if (!keyBoardInputs.contains("LEFT") &&
                !keyBoardInputs.contains("RIGHT") &&
                !keyBoardInputs.contains("UP") &&
                !keyBoardInputs.contains("DOWN") &&
                !keyBoardInputs.contains("W") &&
                !keyBoardInputs.contains("A") &&
                !keyBoardInputs.contains("S") &&
                !keyBoardInputs.contains("D") &&
                !keyBoardInputs.contains("SPACE")
        ) {
        }
    }
}
