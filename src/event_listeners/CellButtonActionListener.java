package event_listeners;

import panels.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellButtonActionListener implements ActionListener {
    private final int row;
    private final int col;
    private final GamePanel gamePanel;

    public CellButtonActionListener(int row, int col, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gamePanel.getIsMarked(row, col)) return;
        gamePanel.revealCell(row, col);
    }
}
