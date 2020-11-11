package uet.oop.bomberman.gamecontroller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.scene.Sandbox;

import java.util.HashSet;

public class EventHandler {
    protected static HashSet<String> inputEventList = new HashSet<>();

    public static void catchEvent(Scene s) {
        //KeyPressHandler kph = new KeyPressHandler();
        //KeyReleaseHandler krh = new KeyReleaseHandler();
        s.setOnKeyPressed(new KeyPressHandler());
        s.setOnKeyReleased(new KeyReleaseHandler());
    }

    public static boolean isKeyDown(KeyCode k) {
        return inputEventList.contains(k.toString());
    }

    public static HashSet<String> getInputEventList() {
        return inputEventList;
    }
}

class KeyPressHandler implements javafx.event.EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
        EventHandler.inputEventList.add(event.getCode().toString());
    }
}

class KeyReleaseHandler implements javafx.event.EventHandler<KeyEvent> {

    @Override
    public  void handle(KeyEvent event) {
        EventHandler.inputEventList.remove(event.getCode().toString());
    }
}
