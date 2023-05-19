import panels.Panel;

import javax.swing.*;

public class Game extends JFrame {
    public Game() {
//        add(new Panel());
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
