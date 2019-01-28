package com.lmcat.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawLineForSpline extends JFrame {
    private static final long serialVersionUID = 1L;
    static List<myPoint> plist;

    public static class myPoint {
        int x;
        int y;

        public myPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public DrawLineForSpline() {
        init();
    }

    public DrawLineForSpline(ArrayList plist) {
        init();
        this.plist = plist;

    }

    private void init() {

        this.setTitle("drawline");
        this.setBounds(200, 200, 500, 400);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        plist = new ArrayList();
        plist.add(new myPoint(50, 80));
        plist.add(new myPoint(50, 120));
        plist.add(new myPoint(80, 50));
        plist.add(new myPoint(150, 10));
        plist.add(new myPoint(180, 80));
        plist.add(new myPoint(230, 200));

    }


    public class Mypanel extends JPanel {
        public void paint(Graphics g) {
            myPoint fromP = new myPoint(50, 80);
            myPoint toP = new myPoint(370, 240);
            for (int i = 0; i < plist.size() - 1; i++) {
                g.drawLine(plist.get(i).x, plist.get(i).y, plist.get(i + 1).x, plist.get(i + 1).y);
            }
        }
    }

    public static void main(String[] args) {
        DrawLineForSpline d = new DrawLineForSpline();
        Mypanel myp = d.new Mypanel();
        d.add(myp);
    }

}
