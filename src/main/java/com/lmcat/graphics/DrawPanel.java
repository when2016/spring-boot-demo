package com.lmcat.graphics;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    public void paint(Graphics g_t) {
        // TODO 自动生成的方法存根
        super.paint(g_t);
        //使用Graphics2D 来完成画图   Graphics 无法设置画图线条的粗细
        Graphics2D g2_t = (Graphics2D) g_t;
        g2_t.setColor(Color.blue);
        g2_t.setPaintMode();
        //设置线条的粗细
        BasicStroke stroke01 = new BasicStroke(5);
        g2_t.setStroke(stroke01);
//        g2_t.drawOval(10, 10, OVAL_WID, OVAL_HEI);
//        g2_t.drawOval(80, 10, OVAL_WID, OVAL_HEI);
//        g2_t.drawOval(150, 10, OVAL_WID, OVAL_HEI);
//        g2_t.drawOval(50, 70, OVAL_WID, OVAL_HEI);
//        g2_t.drawOval(120, 70, OVAL_WID, OVAL_HEI);
        //g.fillOval(x, y, width, height);
    }
}
