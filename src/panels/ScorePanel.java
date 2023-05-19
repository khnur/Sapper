package panels;

import frames.Game;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private int calculatedMines;
    private JLabel timeLabel;
    private JLabel mineCountLabel;
    Timer timer;
    private int elapsedSeconds;
    private final Game game;

    public ScorePanel(Game game) {
        calculatedMines = 0;
        elapsedSeconds = 0;
        this.game = game;

        createScorePanel();
        startTimer();
    }

    private void createScorePanel() {
        JPanel scorePanel = new JPanel();
        timeLabel = new JLabel("Time: 0");
        mineCountLabel = new JLabel("Mines: " + calculatedMines);

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
        elapsedSeconds = 0;
        timer.restart();
    }

}
