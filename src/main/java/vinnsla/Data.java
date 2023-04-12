package vinnsla;

public class Data {
    private static final Data instance = new Data();
    private int score;
    private int lvl;

    /**
     * Getter fyrir instance
     * @return instance
     */
    public static Data getInstance(){ return instance;}

    /**
     * getter fyrir level
     * @return level
     */
    public int getLvl() {
        return lvl;
    }

    /**
     * Setter fyrir level
     * @param lvl úr Leikur
     */
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

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
