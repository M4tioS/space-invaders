package vidmot;


import javafx.scene.shape.Rectangle;

public class Ammo extends Rectangle {


    public int OFFSET = 3;
    public Ammo(){
        FXML_Lestur.lesa(this, "Ammo.fxml");

    }

    public void moveAmmo(){
        setY(getY()-OFFSET);
    }
}
