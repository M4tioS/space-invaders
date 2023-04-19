package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
    private final IntegerProperty points = new SimpleIntegerProperty();
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
     * Getter for Interval
     * @return Interval for keyframe
     */
    public int getInterval() { return INTERVAL;}
}
