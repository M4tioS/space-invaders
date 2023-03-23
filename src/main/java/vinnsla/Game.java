package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
    private IntegerProperty points = new SimpleIntegerProperty();
    private final int Interval = 20;
    public void newGame() {
    }

    public int getIntervall() { return Interval;
    }
}
