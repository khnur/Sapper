package panels;

import frames.Game;

public class NovicePanel extends GamePanel {
    public NovicePanel(Game game) {
        super(600, 800, 9, 8, 5, game);
        createGUI();
        startGame();
    }
}
