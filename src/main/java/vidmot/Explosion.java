package vidmot;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Explosion extends ImageView {
    public Explosion(){
        FXML_Lestur.lesa(this, "explosion.fxml");
    }
}
