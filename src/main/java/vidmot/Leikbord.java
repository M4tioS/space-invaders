package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Leikbord extends Pane {

    // Tilviksbreytur
    @FXML
    private Geimskip fxGeimskip;
    @FXML
    private Ammo fxAmmo;
    private Timeline t;
    private int count = 0;
    private ObservableList<Loftstein> meteors = FXCollections.observableArrayList();



    // Constructor
    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord.fxml");
        newSpaceship();
        newMeteors();
        //shootHitMeteor();
        lykkjaMeteor();
    }



    // Býr til nýjar loftsteinnar á x-ás frá 0 úpp í 1000 á random stað.
    // og y-ás er byrjuð frá 10 og fer úpp í 50 pixlar.
    public void newMeteors(){
        int count = 10;
        for (int i = 10; i < 1000; i+=200){
            Loftstein m = new Loftstein();
            Random r = new Random();
            m.setX(r.nextInt(i));
            m.setY(count);
            this.getChildren().add(m);
            meteors.add(m);
            m.moveMeteor();
            count += 10;
        }

    }

    /*
    Er að vakta y-ás fyrir viðmótshlut ammo


        public void shootHitMeteor(){
        fxAmmo.yProperty().addListener((observable, oldValue, newValue) -> {
            for(int i = 0; i < getMeteors().size(); i++){
                if(didHit(getMeteors().get(i))){
                    deleteMeteor(i);
                }
            }
        });
    }
     */


    /**
     * Eyðir út lofsteinn
     * @param m lofsteinn sem er tekinn út ur fall (shootHitMeteor)
     */
    private void deleteMeteor(int m){
        meteors.remove(m);
        this.getChildren().remove(m);
    }


    // Hreinsa lofsteinar af leikbord
    public void clearTable(){
        for(Loftstein m: meteors) getChildren().remove(m);
        meteors.clear();
    }


    // Býr til nýja leik
    public void newGame(){
        clearTable();
        fxGeimskip = newSpaceship();
        newMeteors();
        //shootHitMeteor();
        lykkjaMeteor();
    }

    /**
     * Athugar hvort fxAmmo er stefna á sama y-ás eins og lofsteinn
     * @param m Lofsteinn
     * @return true eða false
     */
    public boolean didHit(Loftstein m){
        return fxAmmo.getBoundsInParent().intersects(m.getBoundsInParent());
    }

    // Lykkja fyrir lofsteinar
    public void lykkjaMeteor(){
        KeyFrame k = new KeyFrame(Duration.millis(1300),
                e-> {

                    newMeteors();
                    double currentRate = t.getRate();
                    if(this.count % 6 == 0){
                        t.setRate(currentRate+currentRate*0.05);
                    }
                    this.count++;
                });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    // Skilar viðmótshlut af skip
    public Geimskip getShip(){
        return fxGeimskip;
    }

    // Byr til nyja geimskip. Ef núverandi skip er til, hann er eyð útt og
    // bætt við nýja í staðinn.
    public Geimskip newSpaceship(){
        if(fxGeimskip != null){
            getChildren().remove(fxGeimskip);
        }
        fxGeimskip = new Geimskip();
        getChildren().add(fxGeimskip);
        return fxGeimskip;
    }

    // Skilar lista af lofteinum
    public ObservableList<Loftstein> getMeteors(){
        return meteors;
    }

    // Hreyfir allar lofsteinnar niður á y-ásnum
    public void moveAllMeteors(){
        for(int i = 0; i < meteors.size(); i++){
            meteors.get(i).moveMeteor();
        }
    }
}
