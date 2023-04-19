package vidmot;

import javafx.scene.image.ImageView;

public class Loftstein extends ImageView {

    public static final double OFFSET = 0.5;

    /**
     * Reads fxml file
     * Sets up a controller
     */
    public Loftstein(){
        FXML_Lestur.lesa(this, "loftstein.fxml"); //reads fxml file and sets this as a controller
    }

    /**
     * Moves meteor up by 0.5
     * Does that 5 times for smooth illusion
     */
    public void moveMeteor(){
        for (int i = 0; i < 5; i++){
            setY(getY()+OFFSET);
        }

    }
}
