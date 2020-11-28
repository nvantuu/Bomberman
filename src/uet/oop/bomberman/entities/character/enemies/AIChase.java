package uet.oop.bomberman.entities.character.enemies;

import uet.oop.bomberman.entities.character.player.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.scene.Sandbox;

import java.util.Random;

public class AIChase {
    Bomber _bomber;
    Enemy _e;
    private final int distanceToChase = 5;

    public AIChase(Bomber bomber, Enemy e){
        _bomber = bomber;
        _e = e;
    }

    public int calculateDirection(){
        int XDistance = _bomber.getXTile() - _e.getXTile();
        int YDistance = _bomber.getYTile() - _e.getYTile();
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
