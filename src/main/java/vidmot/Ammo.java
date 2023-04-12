package vidmot;


import javafx.scene.shape.Rectangle;

public class Ammo extends Rectangle {
    public static final int OFFSET = 3;
    public Ammo(){
        FXML_Lestur.lesa(this, "Ammo.fxml"); // read in fxml and set this as a controller
    }

    /**
     * Move shots from ship by 3 pixels up
     */
    public void moveAmmo(){
        setY(getY()-OFFSET);
    }
}
