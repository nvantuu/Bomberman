package uet.oop.bomberman.constants;

import uet.oop.bomberman.graphics.Sprite;

public class GlobalConstants {
    public static int CELL_SIZE = 32; //Cells are square
    public static int CANVAS_WIDTH;
    public static int CANVAS_HEIGHT;
    public static String GAME_NAME = "Bomberman";
    public static String GAME_VERSION = " --v1.0";
    public static int SPEED_BOMBER = 1;

    public static int pixelToTile(int i){
        return (int) (i / Sprite.DEFAULT_SIZE);
    }
}
