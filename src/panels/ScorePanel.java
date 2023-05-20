package panels;

import frames.Game;
import main.Button;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private static final int LABEL_WIDTH = 10;
    private int remainingMines;
    private final int totalMines;
    private JLabel timeLabel;
    private JLabel mineCountLabel;
    private JLabel levelLabel;
    private JLabel recordLabel;
    Timer timer;
    private final int gameLevel;
    private int elapsedSeconds;
    private static final int[] records = new int[] {-1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    private final Game game;
    private final Color background;

    public ScorePanel(Game game, int totalMines, int gameLevel) {
        super(new BorderLayout());
        this.remainingMines = totalMines;
        this.totalMines = totalMines;
        this.elapsedSeconds = 0;
        this.game = game;
        this.background = new Color(0, 100, 0);
        this.gameLevel = gameLevel;

        setBackground(background);
        createScorePanel();
        startTimer();
    }

    private void createScorePanel() {
        Font font = new Font("Arial", Font.BOLD, 16);

        timeLabel = new JLabel("Time: " + elapsedSeconds);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(font);

        mineCountLabel = new JLabel("Remaining Mines: " + remainingMines);
        mineCountLabel.setForeground(Color.WHITE);
        mineCountLabel.setFont(font);

        recordLabel = new JLabel("Record: " +
                (records[gameLevel] == Integer.MAX_VALUE ? Character.toString('∞') : records[gameLevel]));
        recordLabel.setForeground(Color.WHITE);
        recordLabel.setFont(font);

        levelLabel = new JLabel(Game.levelmap.get(gameLevel));
        levelLabel.setFont(font);
        levelLabel.setBackground(Color.WHITE);
        levelLabel.setForeground(Color.BLACK);

        JPanel westPanel = new JPanel();
        westPanel.add(levelLabel);
        westPanel.setToolTipText("Level");
        add(westPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.add(mineCountLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(LABEL_WIDTH, 0)));
        centerPanel.add(timeLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(LABEL_WIDTH, 0)));
        centerPanel.add(recordLabel);
        centerPanel.setBackground(background);
        add(centerPanel, BorderLayout.CENTER);

        Button newGame = new Button("New Game", 100, 30, Color.CYAN);
        newGame.setToolTipText("New Game");
        newGame.addActionListener(e -> {
            Main.newFrame(game, 0);
        });

        Button exitGame = new Button("Exit", 60, 30, Color.LIGHT_GRAY);
        exitGame.setToolTipText("Exit Game");
        exitGame.addActionListener(e -> {
            Main.newFrame(game, -1);
        });

        JPanel eastPanel = new JPanel();
        eastPanel.add(newGame);
        eastPanel.add(exitGame);
        eastPanel.setBackground(background);
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS));
        add(eastPanel, BorderLayout.EAST);
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

    public void updateRecord() {
        records[gameLevel] = Math.min(records[gameLevel], elapsedSeconds);
        recordLabel.setText("Record: " + records[gameLevel]);
    }
    public int getRecord() {
        return records[gameLevel];
    }
}
