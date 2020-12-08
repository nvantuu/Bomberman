package uet.oop.bomberman.constants;

import uet.oop.bomberman.graphics.Sprite;

/**
 * thêm BOMB_RADIUS, BOMB_RATE
 * addBombRate để cập nhật số bom đc đặt trên màn hình
 */
public class GlobalConstants {
    public static int CELL_SIZE = 32; //Cells are square
    public static int CANVAS_WIDTH;
    public static int CANVAS_HEIGHT;
    public static String GAME_NAME = "Bomberman";
    public static String GAME_VERSION = " --v1.0";
    public static int SPEED_BOMBER = 1;
    public static int BOMB_RADIUS = 1;
    public static int BOMB_RATE = 1;

    public static int pixelToTile(int i){
        return i / Sprite.DEFAULT_SIZE;
    }

    public static void addBombRate (int i){
        BOMB_RATE += 1;
    }
}
