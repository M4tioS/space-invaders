package vinnsla;

public class Data {
    private static final Data instance = new Data();
    private int score;

    /**
     * Getter fyrir instance
     * @return instance
     */
    public static Data getInstance(){ return instance;}

    /**
     * Getter fyrir stig
     * @return stig
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter fyrir stig
     * @param score úr Leikur
     */
    public void setScore(int score) {
        this.score = score;
    }
}
