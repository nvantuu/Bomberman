package uet.oop.bomberman.entities.character.enemies;

import uet.oop.bomberman.entities.character.player.Bomber;

import java.util.Random;

public class AIChase {
    Bomber _bomber;
    Enemy _e;
    private final int distanceToChase = 5;

    public AIChase(Bomber bomber, Enemy e){
        _bomber = bomber;
        _e = e;
    }

    /**
     *  Tính toán khoảng cách của người chơi và enemy
     *  Nếu người chơi nằm trong phạm vi đuổi, enemy sẽ dò đường để đuổi theo người chơi
     *  Nếu ngoài phạm vi thì sẽ di chuyển ngẫu nhiên
     */
    public int calculateDirection(){
        int XDistance = _bomber.getX() - _e.getX();
        int YDistance = _bomber.getY() - _e.getY();
        boolean chasing = Math.abs(XDistance) <= distanceToChase && Math.abs(YDistance) <= distanceToChase;

        Random random = new Random();
        int r;
        if (chasing) {
            r = random.nextInt(2);
            if (r == 0){
                if (XDistance < 0){
                    return 2;
                }
                else {
                    return 3;
                }
            }
            else {
                if (YDistance < 0){
                    return 0;
                }
                else {
                    return 1;
                }
            }
        }
        else{
            return random.nextInt(4);
        }
    }
}
