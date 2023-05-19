package event_listeners;

import panels.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellButtonActionListener implements ActionListener {
    private final int row;
    private final int col;
    private final Panel panel;

    public CellButtonActionListener(int row, int col, Panel panel) {
        this.panel = panel;
        this.row = row;
        this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.revealCell(row, col);
    }
}
