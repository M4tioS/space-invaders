package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import vidmot.Geimskip;

public class Game {


    private IntegerProperty points = new SimpleIntegerProperty();


    private IntegerProperty level = new SimpleIntegerProperty();
    private final int Interval = 20;

    // Getter and setter points
    public int getPoints() {
        return points.get();
    }

    public IntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }


    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    // Getter and setter level
    public void setLevel(int level) {
        this.level.set(level);
    }


    /**
     *  Byr til nyja leik, endurstillur points og level. Byr til nyja skip
     */
    public void newGame() {
        points.set(0);
        level.set(1);
    }

    public int getIntervall() { return Interval;
    }


    public void addScore(){
        points.set(getPoints()+1);
    }

    public void levelUp(){
        level.set(getLevel()+1);
    }
}
