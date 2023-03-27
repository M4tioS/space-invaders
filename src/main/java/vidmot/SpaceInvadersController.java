package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import vinnsla.Data;
import vinnsla.Game;

import java.security.Key;
import java.util.HashMap;
import java.util.Optional;

public class SpaceInvadersController {

    // Tilviksbreytur
    @FXML
    private Label fxScoreMain;
    @FXML
    private Leikbord fxGamePane;
    public static final String VILTU_HALDA_AFRAM = ": Viltu halda áfram?";
    private final Game game = new Game();
    private static final HashMap<KeyCode, Direction> map = new HashMap<>();
    private Timeline t;
    private final Data data = Data.getInstance();

    public void initialize(){
        fxGamePane.getStyleClass().add("bord");
        startTheGame();
    }

    /**
     * Örva
     * @param s
     */
    public void orvatakkar(Scene s) {
        map.put(KeyCode.RIGHT, Direction.RIGHT);
        map.put(KeyCode.LEFT, Direction.LEFT);
        s.addEventFilter(KeyEvent.ANY,
                event -> {
                    try {
                        if ((event.getCode()) == KeyCode.RIGHT){
                           moveShipRight();
                        } else if ((event.getCode()) == KeyCode.LEFT){
                            moveShipLeft();
                        }
                    } catch (NullPointerException e){
                        event.consume();
                    }
        });
    }

    public void leikLokid(){
        t.stop();
        Platform.runLater(() -> {
            try {
                synaAlert("GAMOVER");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    private void synaAlert(String s) throws Exception {
        Alert a = new AdvorunDialog("GAMEOVER", SpaceInvadersApplication.TITLE, s + VILTU_HALDA_AFRAM);
        Optional<ButtonType> u = a.showAndWait();
        if (u.isPresent() && !u.get().getButtonData().isCancelButton()) System.out.println("Nyr leikur");
        else System.out.println("Ekki nyr leikur");

    }


    /*
    Færir geimskipið til hægri um 5 pixlar
     */
    private void moveShipRight(){
        fxGamePane.getShip().setX(fxGamePane.getShip().getX()+5);
    }
    /*
    Færir geimskipið til vinstri um 5 pixlar
   */
    private void moveShipLeft(){
        fxGamePane.getShip().setX(fxGamePane.getShip().getX()-5);
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
                    if(fxGamePane.isGameover()){
                        leikLokid();
                    }
                });
        t = new Timeline(k);           // tengjum timeline og tímabilið
        t.setCycleCount(Timeline.INDEFINITE);// hve lengi tímalínan keyrist
        t.play();
    }
}