
public class Main {
    public static void main(String[] args) {
        int GameLen;
        try {
            GameLen = (args.length == 0) ? 10 : Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            GameLen = 10;
        }
        TypeRacer newGame = new TypeRacer(GameLen);
        newGame.RunGUI();
        return;
    }
}