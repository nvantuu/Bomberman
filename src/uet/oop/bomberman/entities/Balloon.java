package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.constants.Direction;
import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.entities.enemy.RandomDirection;
import uet.oop.bomberman.scene.Sandbox;

import java.util.Random;

public class Balloon extends Entity {
    public Direction currentdirection = Direction.LEFT;
    public Random random = new Random();
    public int r;
    public int X_distance = 32 * 4;
    public int Y_distance = 32 * 4;
    public boolean chasing;

    public double MaxStep = 16;
    public double step = MaxStep;
    public double rest = 0.5;
    public boolean moving = false;
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x , y, 32, 32);
    }

    @Override
    public void update() {
        BalloonMove();
    }

    public int calculateDirection(){
        chasing = Math.abs(Sandbox.getBomber().x - Sandbox.getBalloon().x) <= X_distance &&
                Math.abs(Sandbox.getBomber().y - Sandbox.getBalloon().y) <= Y_distance;

        if (chasing){
            int vertical = random.nextInt(2);
            if (vertical == 0){
                if (Sandbox.getBomber().x < Sandbox.getBalloon().x) return 0;
                else return 1;
            }
            else {
                if (Sandbox.getBomber().y < Sandbox.getBalloon().y) return 2;
                else return 3;
            }
        }
        else {
            return random.nextInt(4);
        }
    }

    public void RandomDirection(){
        r = calculateDirection();
        if (r == 0 && moving == true){
            currentdirection = Direction.LEFT;
        }
        if (r == 1 && moving == true){
            currentdirection = Direction.RIGHT;
        }
        if (r == 2 && moving == true){
            currentdirection = Direction.UP;
        }
        if (r == 3 && moving == true){
            currentdirection = Direction.DOWN;
        }
    }

    /*public void moveLeft() {
        this.x -= 1;
        if (!BalloonCanMove()){
            this.x += 1;
            currentdirection = Direction.RIGHT;
        }
    }

    public void moveRight() {
        this.x += 1;
        if (!BalloonCanMove()){
            this.x -= 1;
            currentdirection = Direction.LEFT;
        }
    }*/

    public void BalloonMove (){
        if (step <= 0){
            RandomDirection();
            step = MaxStep;
        }

        if (currentdirection == Direction.LEFT){
            if (BalloonCanMove()){
                step -= rest;
                Move(-GlobalConstants.SPEED_BALLOON, 0);
                moving = true;
                //System.out.println("left");
            }
            else {
                step = 0;
                moving = false;
            }
        }

        if (currentdirection == Direction.RIGHT){
            if (BalloonCanMove()){
                step -= rest;;
                Move(GlobalConstants.SPEED_BALLOON, 0);
                moving = true;
                //System.out.println("right");
            }
            else {
                step = 0;
                moving = false;
            }
        }

        if (currentdirection == Direction.UP){
            if (BalloonCanMove()){
                step -= rest;;
                Move(0, -GlobalConstants.SPEED_BALLOON);
                moving = true;
                //System.out.println("up");
            }
            else {
                step = 0;
                moving = false;
            }
        }

        if (currentdirection == Direction.DOWN){
            if (BalloonCanMove()){
                step -= rest;;
                Move(0, GlobalConstants.SPEED_BALLOON);
                moving = true;
                //System.out.println("down");
            }
            else {
                step = 0;
                moving = false;
            }
        }
    }

    public void Move(int deltaX, int deltaY){
        this.x += deltaX;
        this.y += deltaY;
        if (!BalloonCanMove()){
            this.x -= deltaX;
            this.y -= deltaY;
        }
    }

    public boolean BalloonCanMove(){
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
}
