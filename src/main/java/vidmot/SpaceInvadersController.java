package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import vinnsla.Data;
import vinnsla.Game;

import java.security.Key;
import java.util.HashMap;

public class SpaceInvadersController {


    private final Game game = new Game();
    private static final HashMap<KeyCode, Direction> map = new HashMap<>();
    private Timeline t;
    private final Data data = Data.getInstance();

    public void initialize(){

    }

    public void orvatakkar(Scene scene) {
    }

    public void hefjaLeik() {
        game.newGame();
        KeyFrame k = new KeyFrame(Duration.millis(game.getIntervall()), event -> {

        });
    }

    public void setStefna(Direction d, int x){
        for (int i = 0; i < x; i++){

        }
    }
}