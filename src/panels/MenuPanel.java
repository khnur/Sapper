package panels;

import main.Button;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    private final int rows = 4;
    private final int cols = 1;
    private final Button[][] buttons;
    private final JFrame jFrame;

    public MenuPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        buttons = new Button[rows][cols];

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(rows, cols));
        startMenu();
    }
    private void startMenu() {
        String[] texts = new String[] {"Beginner", "Medium", "Advanced", "Exit the Game"};

        for (int i = 0; i < rows; i++) {
            Color buttonColor;
            if (i == 0) {
                buttonColor = Color.YELLOW;
            } else if (i == 1) {
                buttonColor = Color.GREEN;
            } else if (i == 2) {
                buttonColor = Color.RED;
            } else {
                buttonColor = Color.GRAY;
            }
            buttons[i][0] = new Button(texts[i], buttonColor);
            int j = i;
            buttons[i][0].addActionListener(e -> {
                Main.newFrame(jFrame, j + 1);
            });
            add(buttons[i][0], BorderLayout.CENTER);
        }
    }
}
