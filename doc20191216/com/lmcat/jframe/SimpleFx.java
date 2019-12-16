package com.lmcat.jframe;

import javax.swing.*;
import java.awt.*;

public class SimpleFx extends JFrame {
    public SimpleFx() {
        initUI();
    }

    private void initUI() {
        setTitle("Simple example");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            SimpleFx ex=new SimpleFx();
            ex.setVisible(true);
        });
    }
}
