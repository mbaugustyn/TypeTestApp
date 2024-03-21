import java.awt.event.*;
import javax.swing.*;

public class TimeLabel extends JLabel {
    private int gameLen;
    private int currTime;
    private TypeRacer game;

    public TimeLabel(TypeRacer game, int gameLen) {
        super();
        this.game = game;
        this.gameLen = gameLen;
        this.currTime = 0;
        setText("Time remaining: " + gameLen + "s");
        timer.setInitialDelay(0);
    }

    private ActionListener timerListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            setText("Time remaining: " + Integer.toString(gameLen - currTime) +
                    "s");
            if (currTime == gameLen) { /* Game Over! */
                cancelTimer();

                JOptionPane.showMessageDialog(
                        null, "Games up! WPM = " +
                                game.gameResults.calculateWPM(game.wordCount) +
                                " Accuracy = " +
                                (int) game.gameResults.calculateAcc(
                                        game.mistakesCount, game.letterCount)
                                +
                                "%.");

                game.gameRestart();
            }
            currTime++;
        }
    };

    javax.swing.Timer timer = new javax.swing.Timer(1000, timerListener);

    public void startTimer() {
        timer.start();
    }

    public void cancelTimer() {
        timer.stop();
        setText("Time remaining: " + gameLen + "s");
        currTime = 0;
    }
}
