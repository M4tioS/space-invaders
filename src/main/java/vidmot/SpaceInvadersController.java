package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import vinnsla.Data;
import vinnsla.Game;

import java.security.Key;
import java.util.HashMap;

public class SpaceInvadersController {

    @FXML
    private Leikbord leikbord;
    private final Game game = new Game();
    private static final HashMap<KeyCode, Direction> map = new HashMap<>();
    private Timeline t;
    private final Data data = Data.getInstance();

    public void initialize(){

    }

    public void orvatakkar(Scene scene) {
        map.put(KeyCode.RIGHT, Direction.RIGHT);
        map.put(KeyCode.LEFT, Direction.LEFT);
        scene.addEventFilter(KeyEvent.ANY,
                event -> {
                    try {
                        if ((event.getCode()) == KeyCode.RIGHT){
                            setStefna(Direction.RIGHT, 5);
                        } else if ((event.getCode()) == KeyCode.LEFT){
                            setStefna(Direction.LEFT, 5);
                        }

                    } catch (NullPointerException e){
                        event.consume();
                    }

        });
    }

    public void hefjaLeik() {
        game.newGame();
        KeyFrame k = new KeyFrame(Duration.millis(game.getIntervall()), event -> {
            setStefna(Direction.DOWN, 5);
            setLevel();
        });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    public void setStefna(Direction d, int x){
        for (int i = 0; i < x; i++){
            leikbord.getShip().setRotate(d.getDeg());
            leikbord.getShip().moveShip();
        }
    }

    private void setLevel(){
        int i = game.getPoints();
        if (i % 50 == 0 && i > 1){
            game.levelUp();
            double current = t.getRate();
            t.setRate(current * 1.2);
        }
    }
}