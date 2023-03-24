package vidmot;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

public class Geimskip extends ImageView {
    private IntegerProperty health;
    private int OFFSET = 1;


    public Geimskip(){
        health = new SimpleIntegerProperty();
        newSkip();
        FXML_Lestur.lesa(this, "geimskip.fxml");

    }

    private void newSkip(){
        setHealth(3);
    }

    private void hitByMeteor(){
        health.set(getHealth()-1);
    }

    // Getter and setter fyrir geimskip
    public int getHealth() {
        return health.get();
    }
    public void setHealth(int health) {
        this.health.set(health);
    }

    // Health property, skilar venjulegt int af health
    public IntegerProperty healthProperty() {
        return health;
    }

    public void moveShip(){
        Leikbord p = (Leikbord) this.getParent();
        setX((int)(getX() + p.getWidth() + (int) Math.cos(Math.toRadians(getRotate()))*OFFSET) % (int)p.getWidth());
        setY((int)(getY() + p.getHeight() - (int) Math.sin(Math.toRadians(getRotate())) * OFFSET) % (int)p.getHeight());
    }


}
