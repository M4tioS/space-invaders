package vidmot;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Leikbord extends Pane {

    // Tilviksbreytur
    @FXML
    private Geimskip fxGeimskip;
    private Timeline t;
    private int count = 0;
    private ObservableList<Loftstein> meteors = FXCollections.observableArrayList();



    // Constructor
    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord.fxml");
        newSpaceship();
        newMeteors();
        shootHitMeteor();
        lykkjaMeteor();
    }


    // Byr til nyja geimskip. Ef núverandi skip er til, hann er eyð útt og
    // bætt við nýja í staðinn.
    public void newSpaceship(){
        if(fxGeimskip != null){
            getChildren().remove(fxGeimskip);
        }
        fxGeimskip = new Geimskip();
        getChildren().add(fxGeimskip);
    }
    public void newMeteors(){
        int count = 10;
        for (int i = 0; i < 1000; i+=200){
            Loftstein m = new Loftstein();
            Random r = new Random();
            m.setX(i);
            m.setY(count);
            this.getChildren().add(m);
            meteors.add(m);
            m.moveMeteor();
            count += 10;
        }

    }

    public void shootHitMeteor(){

    }

    public void lykkjaMeteor(){

    }

    public Geimskip getShip(){
        return fxGeimskip;
    }
}
