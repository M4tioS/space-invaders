package vidmot;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

public class Geimskip extends ImageView {
    private final IntegerProperty health = new SimpleIntegerProperty();
    private static final int OFFSET = 5;
    private static final int EDGE_OFFSET = 10;


    /**
     * Create new ship with 3 health
     */
    public Geimskip(){
        newSkip();
        FXML_Lestur.lesa(this, "geimskip.fxml"); // read in fxml and set this as a controller
    }

    /**
     * When new ship is created set health to 3
     */
    private void newSkip(){
        setHealth(3);
    }

    /**
     * When ship is hit take away one life
     */
    private void hitByMeteor(){
        health.set(getHealth()-1);
    }

    /**
     * Getter fyrir health
     * @return health
     */
    public int getHealth() {
        return health.get();
    }

    /**
     * Setter fyrir health
     * @param health h
     */
    public void setHealth(int health) {
        this.health.set(health);
    }

    /**
     * Health property for ship
     * @return health
     */
    public IntegerProperty healthProperty() {
        return health;
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
