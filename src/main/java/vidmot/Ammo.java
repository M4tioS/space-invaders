package vidmot;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ammo extends ImageView {


    public int OFFSET = 3;
    public Ammo(){
        FXML_Lestur.lesa(this, "Ammo.fxml");

    }



    public void moveAmmo(){
        setY(getY()-OFFSET);
    }
}
