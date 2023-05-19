package frames;

import panels.GamePanel;

import javax.swing.*;

public class MenuFrame extends GamePanel {
    public MenuFrame(Game jFrame) {
        super(300, 400, 1, 3, 10, jFrame);
        createGUI();
    }

}
