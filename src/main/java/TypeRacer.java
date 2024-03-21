import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypeRacer {
    private int gameLength;
    public int mistakesCount;
    public int wordCount;
    public int letterCount;
    private JFrame GameFrame;
    public TimeLabel TimeLabel;
    public JLabel HeaderLabel;
    public GameInputField InputField;
    private JButton buttonReset;
    public GameResults gameResults;

    public TypeRacer(int gameLen) {
        this.gameLength = gameLen;
        this.wordCount = 0;
        this.letterCount = 0;
        this.mistakesCount = 0;
        this.GameFrame = new JFrame("Type Monkey");
        this.HeaderLabel = new JLabel("Type below when ready.");
        this.InputField = new GameInputField(this);
        this.TimeLabel = new TimeLabel(this, gameLength);
        this.buttonReset = new JButton("Restart (Alt+R)");
        this.gameResults = new GameResults(gameLength);
    }

    public void gameRestart() {
        HeaderLabel.setText("Type below when ready.");
        mistakesCount = 0;
        letterCount = 0;
        wordCount = 0;
        TimeLabel.cancelTimer();
        InputField.restartField();
    }

    public void RunGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Could make ButtoReset to be another class but its small enough for now not to
         */
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameRestart();
            }
        });
        buttonReset.setMnemonic('R');

        /* Game Panel setup */
        GridLayout layout = new GridLayout(4, 1, 10, 10);
        Container kontener = GameFrame.getContentPane();
        kontener.setLayout(layout);
        JPanel headertime = new JPanel();
        headertime.add(HeaderLabel);
        headertime.add(TimeLabel);
        kontener.add(headertime);
        kontener.add(InputField);
        kontener.add(buttonReset);

        /* End setup and show the Main frame */
        GameFrame.setLocationRelativeTo(null);
        GameFrame.pack();
        GameFrame.setVisible(true);
    }
}
