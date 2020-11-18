package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import uet.oop.bomberman.constants.GlobalConstants;
import uet.oop.bomberman.scene.Sandbox;

public class BombermanGame extends Application {

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(GlobalConstants.GAME_NAME + GlobalConstants.GAME_VERSION);
        Sandbox.setupScene();
        Scene s = Sandbox.getScene();
        stage.setScene(s);
        stage.show();
    }
}
