package event_listeners;

import panels.Panel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellButtonMouseListener extends MouseAdapter {
    private final int row;
    private final int col;
    private final Panel panel;

    public CellButtonMouseListener(int row, int col, Panel panel) {
        this.row = row;
        this.col = col;
        this.panel = panel;
    }

    public void mouseClicked(MouseEvent e) {
        if (!SwingUtilities.isRightMouseButton(e)) return;
        panel.flagCell(row, col);

    }
}
