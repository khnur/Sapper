package frames;

import panels.GamePanel;
import panels.NovicePanel;
import panels.ScorePanel;

import javax.swing.*;

public class Game extends JFrame {
    public static final String title = "Sapper";
    private ScorePanel scorePanel;
    private GamePanel gamePanel;
    public Game() {
        setTitle("Sapper");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        createGame();
    }
    public void createGame() {
        gamePanel = new NovicePanel(this);
        scorePanel = new ScorePanel(this, gamePanel.getTotalMines());

        add(scorePanel);
        add(gamePanel);

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
    public void calcMine(char c) {
        if (c == '+') scorePanel.removeMine();
        else scorePanel.addMine();
    }
}
