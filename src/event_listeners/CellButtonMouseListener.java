package event_listeners;

import panels.GamePanel;
import sounds.Sound;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellButtonMouseListener extends MouseAdapter {
    private final int row;
    private final int col;
    private final GamePanel gamePanel;
    private final String MINE_HERE = "M";

    public CellButtonMouseListener(int row, int col, GamePanel gamePanel) {
        this.row = row;
        this.col = col;
        this.gamePanel = gamePanel;
    }

    public void mouseClicked(MouseEvent e) {
        if (!SwingUtilities.isRightMouseButton(e)) return;
        flagCell(row, col);
    }
    public void flagCell(int row, int col) {
        if (gamePanel.getRevealed(row, col)) return;
        Sound.playMouse();

        JButton cellButton = gamePanel.getGridButtons(row, col);
        boolean changeMine;

        if (gamePanel.getIsMarked(row, col)) {
            cellButton.setText("");
            changeMine = false;
        } else {
            cellButton.setText(MINE_HERE);
            changeMine = true;
        }

        gamePanel.setIsMarked(row, col);
        gamePanel.calcMine(changeMine);
    }
}
