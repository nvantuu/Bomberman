package uet.oop.bomberman.gamecontroller;

import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.scene.Sandbox;
import uet.oop.bomberman.constants.GlobalConstants;

import java.util.ArrayList;

public class EventHandlersManager {
    public static void handleBomberMovements() {
        ArrayList<String> keyBoardInputs = EventHandler.getInputEventList();
        Bomber bomber = Sandbox.getBomber();
        if (keyBoardInputs.contains("UP") || keyBoardInputs.contains("W")) {
            Sandbox.getBomber().move(0, -GlobalConstants.SPEED_BOMBER, Direction.UP);
            //System.out.println("up up up!!!");
        }

        if (keyBoardInputs.contains("DOWN") || keyBoardInputs.contains("S")) {
            Sandbox.getBomber().move(0,GlobalConstants.SPEED_BOMBER, Direction.DOWN);
            //System.out.println("down down down!!!");
        }

        if (keyBoardInputs.contains("LEFT") || keyBoardInputs.contains("A")) {
            Sandbox.getBomber().move(-GlobalConstants.SPEED_BOMBER, 0, Direction.LEFT);
            //System.out.println("left left left!!!");
        }

        if (keyBoardInputs.contains("RIGHT") || keyBoardInputs.contains("D")) {
            Sandbox.getBomber().move(GlobalConstants.SPEED_BOMBER, 0, Direction.RIGHT);
            //System.out.println("right right right!!!");
        }

        if( !keyBoardInputs.contains("LEFT") &&
                !keyBoardInputs.contains("RIGHT") &&
                !keyBoardInputs.contains("UP") &&
                !keyBoardInputs.contains("DOWN") &&
                !keyBoardInputs.contains("W") &&
                !keyBoardInputs.contains("A") &&
                !keyBoardInputs.contains("S") &&
                !keyBoardInputs.contains("D")
        ) {
            bomber.move(0,0, Direction.DOWN);
        }
    }

    public static void handleCollisions() {

    }
}
