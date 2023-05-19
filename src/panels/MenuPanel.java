package panels;

import frames.MenuFrame;
import sprites.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    private final int rows = 4;
    private final int cols = 1;
    private final Button[][] buttons;

    public MenuPanel() {
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
            buttons[i][0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            add(buttons[i][0], BorderLayout.CENTER);
        }
    }
}
