import frames.Game;
import frames.MenuFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuFrame();
            }
        });
    }
}