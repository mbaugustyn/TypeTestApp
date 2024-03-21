public class GameResults {
    private int seconds;

    public GameResults(int s) { this.seconds = s; }

    public float calculateWPM(int words) {
        return ((float)words * (float)60) / (float)seconds;
    }
    public float calculateAcc(int mistakes, int letterCount) {
        return (letterCount == 0)
            ? -1
            : (1 - (float)mistakes / (float)letterCount) * 100;
    }
}
