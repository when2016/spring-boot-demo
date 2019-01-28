package com.lmcat.jframe;

import javax.swing.*;
import java.awt.*;

public class QuitButtonEx extends JFrame {
    public QuitButtonEx() {
        initUI();
    }

    private void initUI() {
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener((e -> System.exit(0)));
        createLayout(quitButton);

        setTitle("Quit button");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            QuitButtonEx ex = new QuitButtonEx();
            ex.setVisible(true);
        });
    }
}
