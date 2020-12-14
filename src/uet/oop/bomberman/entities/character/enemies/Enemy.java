package uet.oop.bomberman.entities.character.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.Character;

public abstract class Enemy extends Character {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
