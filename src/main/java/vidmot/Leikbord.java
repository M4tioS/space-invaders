package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Leikbord extends Pane {
    @FXML
    private Geimskip fxGeimskip;
    private Timeline t;
    private Timeline objT;
    private Timeline ammoT;
    private Timeline deleteAmmoT;
    private int score = 0;
    private boolean gameOver = false;
    private final ObservableList<Ammo> ammo = FXCollections.observableArrayList();
    private final ObservableList<Loftstein> meteors = FXCollections.observableArrayList();

    /**
     * Creates new ship, meteors at specific y and random x
     * Start all KeyFrames that are needed
     */
    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord.fxml"); // read in fxml and set this as a controller
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

    /**
     * Check if meteor hit the ship
     * @param m meteor to check
     * @return boolean true if hit
     */
    private boolean meteorHitSpaceShip(Loftstein m){

        return fxGeimskip.getBoundsInParent().intersects(m.getBoundsInParent());
    }

    /**
     * Check if a shot hit meteor with a listener
     */
    public void shootHitMeteor() {
        for (Ammo value : ammo) {
            value.yProperty().addListener((observable, oldValue, newValue) -> {
                for (int i = 0; i < getMeteors().size(); i++) {
                    if (didHit(getMeteors().get(i))) {
                        deleteMeteor(getMeteors().get(i));
                        deleteAmmo(value);
                        setExtraScore();
                        harderMode();
                    }
                }
            });
        }
    }

    /**
     * Adding score after hit meteor
     */
    public void setExtraScore(){
        score += 50;
    }

    /**
     * Getter for score
     * @return score
     */
    public int getScore(){
        return score;
    }

    /**
     * Delete shots
     * @param a a shot to delete
     */
    private void deleteAmmo(Ammo a){
        getChildren().remove(a);
    }

    /**
     * KeyFram for shooting
     */
    public void shootingAmmo(){
        KeyFrame k = new KeyFrame(Duration.millis(750), e->shoot());
        ammoT = new Timeline(k);
        ammoT.setCycleCount(Timeline.INDEFINITE);
        ammoT.play();
    }

    /**
     * KeyFram for deleting ammo
     */
    public void deletingAmmo(){
        KeyFrame k = new KeyFrame(Duration.millis(750), e-> deleteShoot());
        deleteAmmoT = new Timeline(k);
        deleteAmmoT.setCycleCount(Timeline.INDEFINITE);
        deleteAmmoT.setDelay(Duration.millis(3750));
        deleteAmmoT.play();
    }

    /**
     * Adding shots
     */
    private void shoot(){
        Ammo a = new Ammo();
        this.getChildren().add(a);
        ammo.add(a);
        a.setY(getShip().getY());
        a.setX(getShip().getX()+18);
        a.moveAmmo();
    }

    /**
     * Deleting shots
     */
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
        KeyFrame k = new KeyFrame(Duration.millis(1300), e-> newMeteor());
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
                    meteorDeleteOfMap();
                    for(Loftstein m: meteors) if (meteorHitSpaceShip(m)){
                        closeGame();
                    }
                });
        objT = new Timeline(k);
        objT.setCycleCount(Timeline.INDEFINITE);
        objT.play();
    }

    /**
     * Checks position of each meteor
     * @return meteor out of the map or null
     */
    private Loftstein meteorOfMap(){
        for(Loftstein m: meteors){
            if(m.getY()>435) return m;
        }
        return null;
    }

    /**
     * Calls the check position of meteor
     * if there is a meteor out of the map it is deleted
     */
    private void meteorDeleteOfMap(){
        if(meteorOfMap() != null){
            deleteMeteor(meteorOfMap());
        }
    }

    /**
     * Stop all animation when game is finished
     */
    public void closeGame(){
        t.stop();
        objT.stop();
        ammoT.stop();
        deleteAmmoT.stop();
        gameOver = true;
    }

    /**
     * Checks if the game is finished or not
     * @return true if closeGame() has been called and game if finished
     */
    public boolean isGameOver(){
        return gameOver;
    }

    /**
     * Move shots up
     */
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
     */
    public void newSpaceship(){
        if(fxGeimskip != null){
            getChildren().remove(fxGeimskip);
        }
        fxGeimskip = new Geimskip();
        getChildren().add(fxGeimskip);
        System.out.println(getHeight() + " og " + getWidth());
        fxGeimskip.setY(400);
        fxGeimskip.setX(this.getWidth()/2);
    }

    /**
     * Speeds up keyframe for meteors movement and spawn
     */
    public void harderMode(){
            t.setRate(t.getRate()* 1.05);
            objT.setRate(objT.getRate()*1.05);
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
