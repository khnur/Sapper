package panels;

import event_listeners.CellButtonActionListener;
import event_listeners.CellButtonMouseListener;
import frames.Game;
import sprites.Button;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class GamePanel extends JPanel {
    private final int WIDTH;
    private final int HEIGHT;
    private final int rows;
    private final int cols;
    private final int totalMines;
    private boolean running = false;

    Random random;
    private char[][] adjacentMines;
    private Button[][] gridButtons;
    private boolean[][] mineGrid;
    private boolean[][] revealed;
    private int remainingCells;
    private final Color buttonColor;
    private final Game game;

    public GamePanel(int width, int height, int rows, int cols, int totalMines, Game game) {
        this.game = game;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.buttonColor = Color.GREEN;
    }

    protected void createGUI() {
        setLayout(new GridLayout(rows, cols));
    }

    protected void startGame() {
        running = true;
        random = new Random();

        gridButtons = new Button[rows][cols];
        mineGrid = new boolean[rows][cols];
        revealed = new boolean[rows][cols];
        adjacentMines = new char[rows][cols];
        remainingCells = rows * cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gridButtons[i][j] = new Button("", buttonColor);
                gridButtons[i][j].addActionListener(new CellButtonActionListener(i, j, this));
                gridButtons[i][j].addMouseListener(new CellButtonMouseListener(i, j, this));
                add(gridButtons[i][j]);
            }
        }

        placeMines();
        countAdjacentMines();
    }

    private void placeMines() {
        int minesPlaced = 0;

        while (minesPlaced < totalMines) {
            int randomRow = (int) (random.nextDouble() * rows);
            int randomCol = (int) (random.nextDouble() * cols);

            if (mineGrid[randomRow][randomCol]) continue;
            mineGrid[randomRow][randomCol] = true;
            minesPlaced++;

        }
    }

    private void countAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                adjacentMines[i][j] = 'X';
                if (mineGrid[i][j]) continue;

                int count = 0;

                if (i > 0 && j > 0 && mineGrid[i - 1][j - 1])
                    count++;
                if (i > 0 && mineGrid[i - 1][j])
                    count++;
                if (i > 0 && j < cols - 1 && mineGrid[i - 1][j + 1])
                    count++;
                if (j > 0 && mineGrid[i][j - 1])
                    count++;
                if (j < cols - 1 && mineGrid[i][j + 1])
                    count++;
                if (i < rows - 1 && j > 0 && mineGrid[i + 1][j - 1])
                    count++;
                if (i < rows - 1 && mineGrid[i + 1][j])
                    count++;
                if (i < rows - 1 && j < cols - 1 && mineGrid[i + 1][j + 1])
                    count++;

//                    gridButtons[i][j].setText(count > 0 ? String.valueOf(count) : "");
                adjacentMines[i][j] = count == 0 ? ' ' : (char) (count + '0');

            }
        }
    }


    public void revealCell(int row, int col) {
        if (revealed[row][col]) return;
        revealed[row][col] = true;

        gridButtons[row][col].setEnabled(false);
        gridButtons[row][col].setColor(null);
        gridButtons[row][col].setText(String.valueOf(adjacentMines[row][col]));

        remainingCells--;

        if (mineGrid[row][col]) {
            gridButtons[row][col].setText("X");
            game.gameOver("Game Over");
        } else if (remainingCells == totalMines) {
            game.gameOver("You Win");
        } else if (adjacentMines[row][col] == ' ') {
            if (row > 0 && col > 0)
                revealCell(row - 1, col - 1);
            if (row > 0)
                revealCell(row - 1, col);
            if (row > 0 && col < cols - 1)
                revealCell(row - 1, col + 1);
            if (col > 0)
                revealCell(row, col - 1);
            if (col < cols - 1)
                revealCell(row, col + 1);
            if (row < rows - 1 && col > 0)
                revealCell(row + 1, col - 1);
            if (row < rows - 1)
                revealCell(row + 1, col);
            if (row < rows - 1 && col < cols - 1)
                revealCell(row + 1, col + 1);
        }
    }


    public void resetGame() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mineGrid[i][j] = false;
                revealed[i][j] = false;
                gridButtons[i][j].setEnabled(true);
                gridButtons[i][j].setColor(buttonColor);
                gridButtons[i][j].setText("");
            }
        }

        placeMines();
        countAdjacentMines();
        remainingCells = rows * cols;
    }

    public boolean getRevealed(int row, int col) {
        return revealed[row][col];
    }

    public Button getGridButtons(int row, int col) {
        return gridButtons[row][col];
    }
}
