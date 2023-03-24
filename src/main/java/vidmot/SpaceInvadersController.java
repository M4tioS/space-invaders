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

    // Tilviksbreytur
    @FXML
    private Label fxScoreMain;
    @FXML
    private Leikbord fxGamePane;
    private final Game game = new Game();
    private static final HashMap<KeyCode, Direction> map = new HashMap<>();
    private Timeline t;
    private final Data data = Data.getInstance();

    public void initialize(){
        fxGamePane.getStyleClass().add("bord");
        startTheGame();
    }

    public void orvatakkar(Scene s) {
        map.put(KeyCode.RIGHT, Direction.RIGHT);
        map.put(KeyCode.LEFT, Direction.LEFT);
        s.addEventFilter(KeyEvent.ANY,
                event -> {
                    try {
                        if ((event.getCode()) == KeyCode.RIGHT){
                            // move ship to the right
                            //setStefna(Direction.RIGHT, 5);
                            System.out.println("Right");
                        } else if ((event.getCode()) == KeyCode.LEFT){
                            //move ship to the left
                            //setStefna(Direction.LEFT, 5);
                            System.out.println("Left");
                        }

                    } catch (NullPointerException e){
                        event.consume();
                    }
        });
    }

    /**
     * Hækka level eftir 50 stig og auka hraða
     */
    public void setLevel(){
        int i = game.getPoints();
        if (i % 53 == 0 && i > 1){ //53 is a prime
            game.levelUp();
            double current = t.getRate();
            t.setRate(current * 1.2);
        }
    }

    public void startTheGame(){
        KeyFrame k = new KeyFrame(Duration.millis(game.getIntervall()),
                e-> {
                    setLevel();
                    fxScoreMain.setText(String.valueOf(game.getPoints()));
                });
        t = new Timeline(k);           // tengjum timeline og tímabilið
        t.setCycleCount(Timeline.INDEFINITE);// hve lengi tímalínan keyrist
        t.play();
    }
}