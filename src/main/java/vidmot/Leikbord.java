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
    @FXML
    private Leikbord fxLeikbord;
    private Game game;

    private Timeline t;
    private Timeline objT;
    private Timeline ammoT;
    private Timeline deleteAmmoT;

    private boolean gameover = false;
    private int count = 0;
    private ObservableList<Ammo> ammo = FXCollections.observableArrayList();
    private ObservableList<Loftstein> meteors = FXCollections.observableArrayList();



    // Constructor
    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord.fxml");
        newSpaceship();
        startGameNewMeteor();
        shootingAmmo();
        deletingAmmo();
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

    private boolean meteorHitSpaceShip(Loftstein m){

        return fxGeimskip.getBoundsInParent().intersects(m.getBoundsInParent());
    }

    public void shootHitMeteor() {
        for (Ammo value : ammo) {
            value.yProperty().addListener((observable, oldValue, newValue) -> {
                for (int i = 0; i < getMeteors().size(); i++) {
                    if (didHit(getMeteors().get(i))) {
                        deleteMeteor(getMeteors().get(i));
                        deleteAmmo(value);
                    }
                }
            });
        }

    }

    private void deleteAmmo(Ammo a){
        getChildren().remove(a);
    }
    public void shootingAmmo(){
        KeyFrame k = new KeyFrame(Duration.millis(500),
                e-> {
                    shoot();
                });
        ammoT = new Timeline(k);
        ammoT.setCycleCount(Timeline.INDEFINITE);
        ammoT.play();
    }

    public void deletingAmmo(){
        KeyFrame k = new KeyFrame(Duration.millis(500),
                e-> {
                    deleteShoot();
                });
        deleteAmmoT = new Timeline(k);
        deleteAmmoT.setCycleCount(Timeline.INDEFINITE);
        deleteAmmoT.setDelay(Duration.millis(5000));
        deleteAmmoT.play();
    }

    private void shoot(){
        Ammo a = new Ammo();
        this.getChildren().add(a);
        ammo.add(a);
        a.setY(getShip().getY());
        a.setX(getShip().getX());
        a.moveAmmo();
    }

    private void deleteShoot(){
        ammo.remove(ammo.get(0));
        this.getChildren().remove(ammo.get(0));

    }
        /**
         * Eyðir út lofsteinn sem var við árekstur
         * @param m lofsteinn sem er tekinn út ur fall (shootHitMeteor)
         */
    private void deleteMeteor(Loftstein m){
        getChildren().remove(m);
        getMeteors().remove(m);
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
        shootHitMeteor();
        newMeteorLoop();
        moveObjects();
    }

    /**
     * Athugar hvort fxAmmo er stefna á sama y-ás eins og lofsteinn
     * @param m Lofsteinn
     * @return true eða false
     */
    public boolean didHit(Loftstein m){
        for (Ammo value : ammo) {
            if (value.getBoundsInParent().intersects(m.getBoundsInParent())) {
                return true;
            }
        }
        return false;
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
                    moveAmmo();
                    shootHitMeteor();
                    checkAmmoOutOfMap();

                    for(Loftstein m: meteors) if (meteorHitSpaceShip(m)){
                        closeGame();
                    }
                    System.out.println("Meteor fjoldi: " + meteors.size());
                    System.out.println("Ammo fjoldi: " + ammo.size());
                });
        objT = new Timeline(k);
        objT.setCycleCount(Timeline.INDEFINITE);
        objT.play();
    }


    public void closeGame(){
        t.stop();
        objT.stop();
        ammoT.stop();
        deleteAmmoT.stop();
        gameover = true;
    }

    public boolean isGameover(){
        return gameover;
    }
    private void checkAmmoOutOfMap(){
        for(Ammo a : ammo){
            if(a.getY()==0){
                getChildren().remove(a);
            }
        }
    }
    private void moveAmmo(){
        for (Ammo value : ammo) {
            value.moveAmmo();
        }
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
        getChildren().add(fxGeimskip);
        System.out.println(getHeight() + " og " + getWidth());
        setPoistionSpaceShip();
        return fxGeimskip;
    }

    private void setPoistionSpaceShip(){
        fxGeimskip.setY(425);
        fxGeimskip.setX(180);
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
