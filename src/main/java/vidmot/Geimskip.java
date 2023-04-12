package vidmot;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

public class Geimskip extends ImageView {
    private IntegerProperty health;


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

    public void moveRight(int offset){
        Leikbord p = (Leikbord) this.getParent();
        if (p.getWidth() == getX()+50){
            setX(getX() - offset*2);
        } else setX(getX() + offset);

    }

    public void moveLeft(int offset){
        if (0 == getX()){
            System.out.println("X test left");
            setX(getX() + offset*2);
        } else setX(getX() - offset);

    }


}
