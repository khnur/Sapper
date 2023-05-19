package frames;

import panels.GamePanel;

import javax.swing.*;

public class NoviceFrame extends GamePanel {
    public NoviceFrame(Game jFrame) {
        super(600, 800, 9, 8, 5, jFrame);
        createGUI();
        startGame();
    }
}
