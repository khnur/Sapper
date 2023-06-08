package panels;

import event_listeners.CellButtonActionListener;
import event_listeners.CellButtonMouseListener;
import frames.Game;
import main.Button;
import sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private final int WIDTH;
    private final int HEIGHT;
    private final int rows;
    private final int cols;
    private final int totalMines;
    private boolean started;

    Random random;
    private char[][] adjacentMines;
    private Button[][] gridButtons;
    private boolean[][] mineGrid;
    private boolean[][] revealed;
    private boolean[][] isMarked;
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
        this.buttonColor = Color.GREEN;
        this.started = false;
        this.random = new Random();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(rows, cols));
        startGame();
    }

    public void startGame() {
        gridButtons = new Button[rows][cols];
        mineGrid = new boolean[rows][cols];
        revealed = new boolean[rows][cols];
        isMarked = new boolean[rows][cols];
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
    }

    private void placeMines(int row, int col) {
        int minesPlaced = 0;

        while (minesPlaced < totalMines) {
            int randomRow = (int) (random.nextDouble() * rows);
            int randomCol = (int) (random.nextDouble() * cols);

            if (mineGrid[randomRow][randomCol] || !checkInitialPlay(row, col, randomRow, randomCol)) continue;
            mineGrid[randomRow][randomCol] = true;
            minesPlaced++;
        }
    }

    private boolean checkInitialPlay(int row, int col, int mineRow, int colRow) {
        if (mineRow == row && colRow == col) return false;
        if (mineRow == row && colRow == col - 1) return false;
        if (mineRow == row && colRow == col + 1) return false;
        if (mineRow == row - 1 && colRow == col) return false;
        if (mineRow == row - 1 && colRow == col + 1) return false;
        if (mineRow == row - 1 && colRow == col - 1) return false;
        if (mineRow == row + 1 && colRow == col) return false;
        if (mineRow == row + 1 && colRow == col - 1) return false;
        if (mineRow == row + 1 && colRow == col + 1) return false;
        return true;
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

                adjacentMines[i][j] = count == 0 ? ' ' : (char) (count + '0');

            }
        }
    }


    public void revealCell(int row, int col) {
        if (!started) {
            Sound.playStartClick();
            placeMines(row, col);
            countAdjacentMines();
            game.startGamePlay();
            started = true;
        }
        if (revealed[row][col]) return;
        revealed[row][col] = true;

        if (isMarked[row][col]) calcMine(false);

        gridButtons[row][col].setEnabled(false);
        gridButtons[row][col].setColor(null);
        gridButtons[row][col].setText(String.valueOf(adjacentMines[row][col]));

        remainingCells--;

        if (mineGrid[row][col]) {
            gridButtons[row][col].setText("X");
            gridButtons[row][col].setColor(Color.RED);
            gameOver("Game Over", false);
        } else if (remainingCells == totalMines) {
            gameOver("You Won", true);
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

    private void gameOver(String message, boolean won) {
        Color color = won ? Color.CYAN : Color.RED;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mineGrid[i][j]) continue;
                gridButtons[i][j].setText("X");
                gridButtons[i][j].setColor(color);
            }
        }
        game.gameOver(message, won);
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

        remainingCells = rows * cols;
    }

    public boolean getRevealed(int row, int col) {
        return revealed[row][col];
    }

    public Button getGridButtons(int row, int col) {
        return gridButtons[row][col];
    }

    public boolean getIsMarked(int row, int col) {
        return isMarked[row][col];
    }

    public void setIsMarked(int row, int col) {
        isMarked[row][col] = !isMarked[row][col];
    }

    public void calcMine(boolean plus) {
        game.calcMine(plus);
    }
}
