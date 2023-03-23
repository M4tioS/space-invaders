package vidmot;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

public class Loftstein extends ImageView {

    public double OFFSET = 0.5;
    public Loftstein(){
        FXML_Lestur.lesa(this, "lofstein.fxml");

    }


    public void moveMeteor(){
        setY(getY()+OFFSET);
    }
}
