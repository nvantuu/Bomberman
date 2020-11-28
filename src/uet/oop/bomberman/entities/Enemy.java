package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.enemy.RandomDirection;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.scene.Sandbox;

public abstract class Enemy extends Entity{
    public Enemy (int xUnit, int yUnit, Image img){
        super(xUnit, yUnit, img);
    }

    /*@Override
    public void update() {
        calculateMove();
    }

    public void calculateMove(){
        if (_step <= 0){
            _direction = RandomDirection.RandomDirection();
            _step = _MaxStep;
        }

        if (_direction == 0){
            if (EnemycanMove()){
                _step--;
                Enemymove(0, _speed);
                _move = true;
            }
            else {
                _step = 0;
                _move = false;
            }
        }

        if (_direction == 1){
            if (EnemycanMove()){
                _step--;
                Enemymove(_speed, 0);
                _move = true;
            }
            else {
                _step = 0;
                _move = false;
            }
        }

        if (_direction == 2){
            if (EnemycanMove()){
                _step--;
                Enemymove(-_speed, 0);
                _move = true;
            }
            else {
                _step = 0;
                _move = false;
            }
        }

        if (_direction == 3){
            if (EnemycanMove()){
                _step--;
                Enemymove(0, -_speed);
                _move = true;
            }
            else {
                _step = 0;
                _move = false;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img ,x, y);
    }

    @Override
    public boolean EnemycanMove(){
        for (Entity e : Sandbox.getStillObjects()) {
            if (e instanceof Grass) continue;
            if (intersects(e)) {
                return false;
            }
        }
        return true;
    }

    private boolean intersects(Entity that) {
        return this.getBoundary().intersects(that.getBoundary());
    }

    public void Enemymove(int xe, int ye){
        x += xe;
        y += ye;
    }*/
}
