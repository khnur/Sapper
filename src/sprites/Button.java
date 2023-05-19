package sprites;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;

public class Button extends JButton {
    public static final int DEFAULT_WIDTH = 40;
    public static final int DEFAULT_HEIGHT = 40;

    public Button(String text, Color color) {
        super(text);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setBackground(color);
    }

    public Button(String text, int width, int height, Color color) {
        super(text);
        setPreferredSize(new Dimension(width, height));
        setBackground(color);
    }
    public void setColor(Color color) {
        setBackground(color);
    }
}