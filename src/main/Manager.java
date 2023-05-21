package main;

import frames.AdvancedFrame;
import frames.MediumFrame;
import frames.MenuFrame;
import frames.NoviceFrame;
import sounds.Sound;

import javax.swing.*;

public class Manager {
    public static void start() {
        Sound.startSound();
        SwingUtilities.invokeLater(MenuFrame::new);
    }
    public static void newFrame(JFrame existing, int frame) {
        Sound.playClick();

        existing.dispose();
        switch (frame) {
            case 0 -> SwingUtilities.invokeLater(MenuFrame::new);
            case 1 -> SwingUtilities.invokeLater(NoviceFrame::new);
            case 2 -> SwingUtilities.invokeLater(MediumFrame::new);
            case 3 -> SwingUtilities.invokeLater(AdvancedFrame::new);
            default -> {
                Sound.dispose();
                System.exit(0);
            }
        }
    }
}