package vidmot;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Leikbord extends Pane {
    @FXML
    private Geimskip fxGeimskip;

    public Leikbord(){
        FXML_Lestur.lesa(this, "leikbord.fxml");
    }

    public Geimskip getShip(){
        return fxGeimskip;
    }
}
