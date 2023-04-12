package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
    private final IntegerProperty points = new SimpleIntegerProperty();
    private final IntegerProperty level = new SimpleIntegerProperty();
    private static final int INTERVAL = 20;

    /**
     * Getter fyrir stig
     * @return sitg
     */
    public int getPoints() {
        return points.get();
    }

    /**
     * Setter fyrir stig
     * @param points stig
     */
    public void setPoints(int points) {
        this.points.set(points);
    }

    /**
     * Getter fyrir level
     * @return level
     */
    public int getLevel() {
        return level.get();
    }


    /**
     * Getter for Interval
     * @return Interval for keyframe
     */
    public int getInterval() { return INTERVAL;}


    /**
     * Hækka stig um 1
     */
    public void addScore(){
        points.set(getPoints()+1);
    }

    /**
     * Hækka level um 1
     */
    public void levelUp(){
        level.set(getLevel()+1);
    }

    /**
     *  Byr til nyja leik, endurstillur points og level. Byr til nyja skip
     */
    public void newGame() {
        points.set(0);
        level.set(1);
    }
}
