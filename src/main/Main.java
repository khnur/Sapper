package main;

import frames.AdvancedFrame;
import frames.MediumFrame;
import frames.MenuFrame;
import frames.NoviceFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFrame::new);
    }
    public static void newFrame(JFrame existing, int frame) {
        existing.dispose();
        switch (frame) {
            case 0 -> SwingUtilities.invokeLater(MenuFrame::new);
            case 1 -> SwingUtilities.invokeLater(NoviceFrame::new);
            case 2 -> SwingUtilities.invokeLater(MediumFrame::new);
            case 3 -> SwingUtilities.invokeLater(AdvancedFrame::new);
            default -> System.exit(0);
        }
    }
}