package frames;

import main.Manager;
import panels.GamePanel;
import panels.ScorePanel;
import sounds.Sound;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Game extends JFrame {
    public static final String title = "Sapper";
    private final ScorePanel scorePanel;
    private final GamePanel gamePanel;
    public static final Map<Integer, String> levelmap = new HashMap<>() {
        {
            put(1, "Beginner");
            put(2, "Medium");
            put(3, "Hard");
        }
    };

    public Game(int width, int height, int rows, int cols, int totalMines, int level) {
        Sound.playStart();

        setTitle("Sapper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        gamePanel = new GamePanel(width, height, rows, cols, totalMines, this);
        scorePanel = new ScorePanel(this, totalMines, level);

        add(scorePanel);
        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void gameOver(String message, boolean won) {
        if (won) {
            scorePanel.updateRecord();
            Sound.playWin();
        } else {
            Sound.playMine();
        }
        int time = scorePanel.stopTimer();
        message += "\nTime: " + time + "\nRecord: " + (scorePanel.getRecord() == Integer.MAX_VALUE ? "NA" : scorePanel.getRecord());

        int choice = JOptionPane.showOptionDialog(this, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Replay", "OK"}, "OK");

        if (choice == 0) {
            scorePanel.restartTimer();
            gamePanel.resetGame();
        } else if (choice == 1) {
            Manager.newFrame(this, 0);
        }
    }

    public void calcMine(boolean plus) {
        if (plus) scorePanel.removeMine();
        else scorePanel.addMine();
    }
    public void startGamePlay() {
        scorePanel.startTimer();
    }
}
