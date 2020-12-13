package uet.oop.bomberman.gamecontroller;

import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.scene.Sandbox;

import java.util.ArrayList;

public class EventHandlersManager {
    public static void handleBomberEvents() {

        ArrayList<String> keyBoardInputs = EventHandler.getInputEventList();

        if (keyBoardInputs.contains("UP") || keyBoardInputs.contains("W")) {
            Sandbox.getBomber().move(Direction.UP);
            //Sandbox.getBomber().setCurrentDirection(Direction.UP);
        }

        if (keyBoardInputs.contains("DOWN") || keyBoardInputs.contains("S")) {
            Sandbox.getBomber().move(Direction.DOWN);
        }

        if (keyBoardInputs.contains("LEFT") || keyBoardInputs.contains("A")) {
            Sandbox.getBomber().move(Direction.LEFT);
        }

        if (keyBoardInputs.contains("RIGHT") || keyBoardInputs.contains("D")) {
            Sandbox.getBomber().move(Direction.RIGHT);
        }

        if (keyBoardInputs.contains("SPACE")) {
            Sandbox.getBomber().setBomb();
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
