package vidmot;

import javafx.scene.image.ImageView;

public class Geimskip extends ImageView {
    private static final int OFFSET = 5;
    private static final int EDGE_OFFSET = 10;


    /**
     * Create new ship with 3 health
     */
    public Geimskip(){
        FXML_Lestur.lesa(this, "geimskip.fxml"); // read in fxml and set this as a controller
    }


    /**
     * Move ship to the right by 5 pixels
     * and Move ship to the left if at the edge of map
     */
    public void moveRight(){
        Leikbord p = (Leikbord) this.getParent();
        if (p.getWidth() == getX()+50){
            setX(getX() - EDGE_OFFSET);
        } else setX(getX() + OFFSET);
    }

    /**
     * Move ship to the left by 5 pixels
     * and Move ship to the right if at the edge of map
     */
    public void moveLeft(){
        if (0 == getX()){
            setX(getX() + EDGE_OFFSET);
        } else setX(getX() - OFFSET);
    }


}
