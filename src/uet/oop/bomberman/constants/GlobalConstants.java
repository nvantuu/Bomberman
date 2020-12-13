package uet.oop.bomberman.constants;

public class GlobalConstants {
    public static int CELL_SIZE = 32; //Cells are square
    public static int CANVAS_WIDTH;
    public static int CANVAS_HEIGHT;
    public static String GAME_NAME = "Bomberman";
    public static String GAME_VERSION = " --v1.0";
    public static int SPEED_BOMBER = 1;
    public static int BOMB_RADIUS = 1; // độ dài của flame ban đầu là một
    public static int BOMB_RATE = 1; // số bom được đặt ban đầu là một

    public static void addBombRate (int i){
        BOMB_RATE += i;
    }

    public static void increaseBombRadius () {
        BOMB_RADIUS = 2;
    }

    public static int getBombRate() {
        return BOMB_RATE;
    }

    public static int getBombRadius() {
        return BOMB_RADIUS;
    }
}
