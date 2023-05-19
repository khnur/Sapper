package panels;

import event_listeners.CellButtonActionListener;
import event_listeners.CellButtonMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Panel extends JPanel {
    private static final String title = "Sapper";
    private final int WIDTH;
    private final int HEIGHT;
    private final int rows;
    private final int cols;
    private boolean running = false;

    Random random;
    private final JButton[][] gridButtons;
    private final boolean[][] mineGrid;
    private final boolean[][] revealed;
    private int remainingCells;

    public Panel(int w, int h, int rows, int cols) {
        this.WIDTH = w;
        this.HEIGHT = h;
        this.rows = rows;
        this.cols = cols;
        random = new Random();

        gridButtons = new JButton[rows][cols];
        mineGrid = new boolean[rows][cols];
        revealed = new boolean[rows][cols];
        remainingCells = rows * cols;
    }

    private void createGUI() {
        setLayout(new GridLayout(rows, cols));
    }

    private void startGame() {
        running = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gridButtons[i][j] = new JButton();
                gridButtons[i][j].setPreferredSize(new Dimension(40, 40));
                gridButtons[i][j].addActionListener(new CellButtonActionListener(i, j, this));
                gridButtons[i][j].addMouseListener(new CellButtonMouseListener(i, j, this));
                add(gridButtons[i][j]);
            }
        }
    }

    public void revealCell(int row, int col) {
    }

    public void flagCell(int row, int col) {

    }
}
