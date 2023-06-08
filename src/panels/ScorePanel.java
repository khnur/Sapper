package panels;

import frames.Game;
import main.Button;
import main.Manager;

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
        this.timer = new Timer(1000, e -> {
            elapsedSeconds++;
            timeLabel.setText("Time: " + elapsedSeconds);
        });
        this.game = game;
        this.background = new Color(0, 100, 0);
        this.gameLevel = gameLevel;

        setBackground(background);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        createScorePanel();
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
                (records[gameLevel] == Integer.MAX_VALUE ? "NA" : records[gameLevel]));
        recordLabel.setForeground(Color.WHITE);
        recordLabel.setFont(font);

        levelLabel = new JLabel(Game.levelmap.get(gameLevel));
        levelLabel.setFont(font);
        levelLabel.setBackground(Color.WHITE);
        levelLabel.setForeground(Color.BLACK);

        add(Box.createRigidArea(new Dimension(2 * LABEL_WIDTH, 0)));

        JPanel westPanel = new JPanel();
        westPanel.add(Box.createRigidArea(new Dimension(LABEL_WIDTH, 0)));
        westPanel.add(levelLabel);
        westPanel.add(Box.createRigidArea(new Dimension(LABEL_WIDTH, 0)));
        westPanel.setToolTipText("Level");
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.X_AXIS));
        add(westPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.add(Box.createRigidArea(new Dimension(2 * LABEL_WIDTH, 0)));
        centerPanel.add(mineCountLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(LABEL_WIDTH, 0)));
        centerPanel.add(timeLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(LABEL_WIDTH, 0)));
        centerPanel.add(recordLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(2 * LABEL_WIDTH, 0)));
        centerPanel.setBackground(background);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        add(centerPanel, BorderLayout.CENTER);

        Button newGame = new Button("New Game", 100, 30, Color.CYAN);
        newGame.setToolTipText("New Game");
        newGame.addActionListener(e -> {
            Manager.newFrame(game, 0);
        });

        Button exitGame = new Button("Exit", 60, 30, Color.LIGHT_GRAY);
        exitGame.setToolTipText("Exit Game");
        exitGame.addActionListener(e -> {
            Manager.newFrame(game, -1);
        });

        JPanel eastPanel = new JPanel();
        eastPanel.add(newGame);
        eastPanel.add(exitGame);
        eastPanel.setBackground(background);
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS));
        add(eastPanel, BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(2 * LABEL_WIDTH, 0)));

    }

    public void startTimer() {
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
