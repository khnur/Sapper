package frames;


import panels.MenuPanel;

import javax.swing.*;
import java.awt.*;
public class MenuFrame extends JFrame {
    public MenuFrame() {
        setTitle("Sapper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                JLabel jLabel = new JLabel();
                jLabel.setText("Select the Game level: ");
                add(jLabel);
            }
        });

        add(new MenuPanel(this));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }


}
