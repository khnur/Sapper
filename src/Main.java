import frames.MenuFrame;
import sounds.Sound;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFrame::new);
        Sound.startSound();
    }
}
