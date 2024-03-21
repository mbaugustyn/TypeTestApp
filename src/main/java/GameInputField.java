import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class GameInputField extends JTextField {
    private int pos;
    private int lineLen;
    private String lineCurr;
    private boolean gameStarted;
    private RandomWords wordsGenerator;
    private AudioPlayer audioPlayer;

    private void disableKeys(InputMap inputMap) {
        /* This has no real impact on functionality but
         helps with "fat fingers"*/
        String[] keys = {"UP", "DOWN", "LEFT", "RIGHT"};
        for (String key : keys) {
            inputMap.put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    private void colorSelection(int start, int end, Color c) {
        setSelectionStart(start);
        setSelectionEnd(end);
        setSelectionColor(c);
    }

    public GameInputField(TypeRacer game) {
        super();
        this.gameStarted = false;
        this.setEditable(false);
        wordsGenerator = new RandomWords();
        audioPlayer =
            new AudioPlayer(".." + File.separator + "resources" +
                            File.separator + "sounds" + File.separator);
        fetchNewLine();
        this.setColumns(30);
        this.setBackground(Color.CYAN);
        this.setCaretColor(Color.red);
        this.setFont(new Font("Courier", Font.PLAIN, 20));
        disableKeys(this.getInputMap());

        this.addKeyListener(new KeyListener() {
            boolean IgnoreKeyEvent = false;
            @Override
            public void keyPressed(KeyEvent e) {
                /* Handle Alt + R combo which triggers Reset Button */
                IgnoreKeyEvent =
                    ((e.getKeyChar() == 'r' || e.getKeyChar() == 'R') &&
                     e.isAltDown());
            }

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyTyped(KeyEvent e) {

                if (IgnoreKeyEvent)
                    return;

                char inputChar = e.getKeyChar();
                game.letterCount++;
                if (!gameStarted) {
                    gameStarted = true;
                    game.HeaderLabel.setText("Go!");
                    game.TimeLabel.startTimer();
                }

                char textChar = lineCurr.charAt(pos);
                textChar = (textChar == '_') ? ' ' : textChar;

                if (inputChar == textChar) {
                    /* Correct input */
                    setCaretPosition(pos);
                    pos++;
                    if (pos == lineLen) {
                        game.wordCount++;
                        fetchNewLine();
                    } else if (textChar == ' ')
                        game.wordCount++;

                    colorSelection(0, pos, Color.GREEN);
                } else {
                    /* Incorrect input */
                    audioPlayer.playTrack("click.wav");
                    game.mistakesCount++;
                }
            }
        });
    }

    private void fetchNewLine() {
        pos = 0;
        lineCurr = wordsGenerator.getNextLine();
        lineLen = lineCurr.length();
        setText(lineCurr);
        setCaretPosition(0);
    }

    public void restartField() {
        requestFocusInWindow();
        gameStarted = false;
        wordsGenerator.ResetText();
        fetchNewLine();
    }
}