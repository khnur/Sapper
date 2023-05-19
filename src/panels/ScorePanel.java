package panels;

import frames.Game;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private int remainingMines;
    private final int totalMines;
    private JLabel timeLabel;
    private JLabel mineCountLabel;
    Timer timer;
    private int elapsedSeconds;
    private final Game game;

    public ScorePanel(Game game, int totalMines) {
        this.remainingMines = totalMines;
        this.totalMines = totalMines;
        this.elapsedSeconds = 0;
        this.game = game;

        createScorePanel();
        startTimer();
    }

    private void createScorePanel() {
        JPanel scorePanel = new JPanel();
        timeLabel = new JLabel("Time: " + elapsedSeconds);
        mineCountLabel = new JLabel("Remaining Mines: " + remainingMines);

        scorePanel.add(mineCountLabel);
        scorePanel.add(timeLabel);

        add(scorePanel, BorderLayout.NORTH);
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            elapsedSeconds++;
            timeLabel.setText("Time: " + elapsedSeconds);
        });
        timer.start();
    }
    public int stopTimer() {
        timer.stop();
        return elapsedSeconds;
    }
    public void restartTimer() {
        timeLabel.setText("Time: 0");
        mineCountLabel.setText("Remaining Mines: " + totalMines);

        remainingMines = totalMines;
        elapsedSeconds = 0;
        timer.restart();
    }

    public void addMine() {
        if (remainingMines >= totalMines) return;
        remainingMines++;
        mineCountLabel.setText("Remaining Mines: " + remainingMines);
    }
    public void removeMine() {
        if (remainingMines <= 0) return;
        remainingMines--;
        mineCountLabel.setText("Remaining Mines: " + remainingMines);
    }

}
