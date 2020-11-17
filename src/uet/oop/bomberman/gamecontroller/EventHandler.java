package uet.oop.bomberman.gamecontroller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class EventHandler {
    protected static ArrayList<String> inputEventList = new ArrayList<>();

    public static void catchInputEvents(Scene s) {
        s.setOnKeyReleased(event -> EventHandler.inputEventList.remove(event.getCode().toString()));
        s.setOnKeyPressed(event -> {
            if (!EventHandler.inputEventList.contains(event.getCode().toString())) {
                EventHandler.inputEventList.add(event.getCode().toString());
            }
        });
    }

    public static ArrayList<String> getInputEventList() {
        return inputEventList;
    }
}