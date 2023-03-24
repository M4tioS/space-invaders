package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import vinnsla.Game;

import java.util.Random;

public class Leikbord extends Pane {

    // Tilviksbreytur
    @FXML
    private Geimskip fxGeimskip;
    private Game game;
    @FXML
    private Ammo fxAmmo;
    private Timeline t;
    private Timeline objT;
    private int count = 0;
    private ObservableList<Loftstein> meteors = FXCollections.observableArrayList();



    // Constructor
    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord.fxml");
        newSpaceship();
        startGameNewMeteor();
        //shootHitMeteor();
        newMeteorLoop();
        moveObjects();
    }


    /**
     * Býr til 5 steinar í upphafi leiks
     */
    public void startGameNewMeteor(){
        int count = -50;
        for (int i = 0; i < 5; i++){
            Loftstein m = new Loftstein();
            Random r = new Random();
            m.setX(r.nextInt(290));
            System.out.println(this.getWidth());
            m.setY(count);
            this.getChildren().add(m);
            meteors.add(m);
            m.moveMeteor();
            count -= 50;
        }
    }

/*
    //Er að vakta y-ás fyrir viðmótshlut ammo
    public void shootHitMeteor(){
        fxAmmo.yProperty().addListener((observable, oldValue, newValue) -> {
            for(int i = 0; i < getMeteors().size(); i++){
                if(didHit(getMeteors().get(i))){
                    deleteMeteor(i);
                }
            }
        });
    }*/



    /**
     * Eyðir út lofsteinn sem var við árekstur
     * @param m lofsteinn sem er tekinn út ur fall (shootHitMeteor)
     */
    private void deleteMeteor(int m){
        meteors.remove(m);
        this.getChildren().remove(m);
    }


    /**
     * Eyðir út öllum loftsteinum
     */
    public void clearTable(){
        for(Loftstein m: meteors) getChildren().remove(m);
        meteors.clear();
    }


    /**
     * Þegar ný leikur er byrjað er hreinsað allt og búið upp á nýtt
     */
    public void newGame(){
        clearTable();
        fxGeimskip = newSpaceship();
        startGameNewMeteor();
        //shootHitMeteor();
        newMeteorLoop();
        moveObjects();
    }

    /**
     * Athugar hvort fxAmmo er stefna á sama y-ás eins og lofsteinn
     * @param m Lofsteinn
     * @return true eða false
     */
    public boolean didHit(Loftstein m){
        return fxAmmo.getBoundsInParent().intersects(m.getBoundsInParent());
    }

    /**
     * Lykja til að búa til nýta lofsteina
     */
    public void newMeteorLoop(){
        KeyFrame k = new KeyFrame(Duration.millis(1300),
                e-> {
                    newMeteor();
                });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    /**
     * Spawning new meteors after the game starts
     */
    private void newMeteor(){
        Loftstein loftstein = new Loftstein();
        Random r = new Random();
        loftstein.setX(r.nextInt((int)this.getWidth()));
        loftstein.setY(-150);
        this.getChildren().add(loftstein);
        meteors.add(loftstein);
        loftstein.moveMeteor();
    }

    /**
     * Loop to move objects such as ammo and Meteors
     */
    public void moveObjects(){
        KeyFrame k = new KeyFrame(Duration.millis(20),
                e-> {
                    moveAllMeteors();
                });
        objT = new Timeline(k);
        objT.setCycleCount(Timeline.INDEFINITE);
        objT.play();
    }

    /**
     * Getter fyrir skip
     * @return geimskip hlut
     */
    public Geimskip getShip(){
        return fxGeimskip;
    }

    /**
     * Gera nýtt geimskip ef núverandi er til eyða þvi
     * @return nýtt geimskip
     */
    public Geimskip newSpaceship(){
        if(fxGeimskip != null){
            getChildren().remove(fxGeimskip);
        }
        fxGeimskip = new Geimskip();
        System.out.println("new ship: " + this.getWidth() + " " + this.getHeight());
        getChildren().add(fxGeimskip);
        return fxGeimskip;
    }

    /**
     * Skilar listan af loftsteinum
     * @return obsv list af loftsteinum
     */
    public ObservableList<Loftstein> getMeteors(){
        return meteors;
    }

    /**
     * Hreyfa allar loftsteinar
     */
    public void moveAllMeteors(){
        for (Loftstein meteor : meteors) {
            meteor.moveMeteor();
        }
    }
}
