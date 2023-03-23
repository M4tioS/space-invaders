package vidmot;

public enum Direction {
    RIGHT(360),DOWN(270),LEFT(180);

    private final int deg;

    Direction(int s){deg = s;}

    public int getDeg(){return deg;}
}
