package frames;

import panels.GamePanel;
import panels.ScorePanel;

import javax.swing.*;

public class Game extends JFrame {
    public static final String title = "Sapper";
    private ScorePanel scorePanel;
    private GamePanel gamePanel;
    public Game() {
        scorePanel = new ScorePanel(this);
        gamePanel = new NoviceFrame(this);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(scorePanel);
        add(gamePanel);
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }
    public void gameOver(String message) {
        int time = scorePanel.stopTimer();
        message += "\nTime: " + time;
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        scorePanel.restartTimer();
        gamePanel.resetGame();
    }
}
