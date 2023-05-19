import panels.MenuPanel;
import panels.NovicePanel;

import javax.swing.*;

public class Game extends JFrame {
    public Game() {
        add(new NovicePanel());
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
