package frames;

import panels.GamePanel;
import panels.ScorePanel;

import javax.swing.*;

public abstract class Game extends JFrame {
    public static final String title = "Sapper";
    private final ScorePanel scorePanel;
    private final GamePanel gamePanel;
    public Game(int width, int height, int rows, int cols, int totalMines) {
        setTitle("Sapper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        gamePanel = new GamePanel(width, height, rows, cols, totalMines, this);
        scorePanel = new ScorePanel(this, totalMines);

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
//        gamePanel.resetGame();

        this.dispose();
        new MenuFrame();
    }
    public void calcMine(char c) {
        if (c == '+') scorePanel.removeMine();
        else scorePanel.addMine();
    }
}
