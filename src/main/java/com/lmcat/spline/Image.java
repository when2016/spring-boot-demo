package com.lmcat.spline;

import javax.swing.*;
import java.awt.*;

//double x[]={0.0,0.5,1.0,6.0,7.0,9.0};
//double y[]={0.0,1.6,2.0,2.0,1.5,0.0};
//
public class Image extends Canvas {
    double re;
    double x[] = {0.0, 0.5, 1.0, 6.0, 7.0, 9.0};
    double y[] = {0.0, 1.6, 2.0, 2.0, 1.5, 0.0};
    double m[] = {13.051620947630925, -6.903241895261847,
            0.161346633416459, -0.144638403990025,
            -2.071072319201995, 3.2855361596009973};
    double h[] = {0.5, 0.5, 5.0, 1.0, 2.0};

    public double cal1(double xx, int i) {
        double re = 0;
//		for(int i=0;i<x.length-1;i++){
        re = y[i] + ((y[i + 1] - y[i]) / h[i] - (2 * m[i] + m[i + 1]) * h[i] / 6) * (xx - x[i]) + m[i] * (xx - x[i]) * (xx - x[i]) / 2
                + (m[i + 1] - m[i]) * Math.pow((xx - x[i]), 3) / (6 * h[i]);
//		}
        return re;
    }

    public void paint(Graphics g) {
        int width = getSize().width;//获取画布的宽度
        int height = getSize().height;//获取画布的高度
        // draw x-axis
        g.drawLine(0, height / 2, width, height / 2);
        for (int ix = 0; ix < width; ix += width / 9)
            g.drawLine(ix, height / 2 - 5, ix, height / 2);
        g.drawString("" + 0, 5, height / 2 + 10);
        for (int i = 0; i <= 9; i++) {
            g.drawString("" + i, i * width / 9 - 10, height / 2 + 10);//x轴上各点的坐标
        }
        g.drawString("x", width - 10, height / 2 + 20);


        // draw y-axis
        g.drawLine(1, 0, 1, height);
        for (int iy = 0; iy < height; iy += height / 8)
            g.drawLine(0, iy, 5, iy);
        g.drawString("4", 2, 10);
        g.drawString("2", 2, height / 4);
        g.drawString("-4", 2, height - 5);
        g.drawString("y", 10, 10);
        // draw the function
        double x_step = 9. / width;//注意这里的数是double型的，若换成4就不正确了
        double y_step = 8. / height;//刻度
//		double xx,yy[i];
        double yy[] = new double[x.length - 1];
        int x0, y0, x1, y1;
        double k = 0;
        x0 = 0;//初始起点
        double xx = 0;//

        //画图
        for (int i = 0; i < x.length - 1; i++) {
            yy[i] = cal1(xx, i);
            y0 = (yy[i] >= 0) ? (height / 2 - (int) (yy[i] / y_step))
                    : (height / 2 + (int) (Math.abs(yy[i]) / y_step));
            xx += x_step;
//		0.0,0.5,1.0,6.0,7.0,9.0
            if (i == 0)
                k = 0.5;
            else if (i == 1)
                k = 1;
            else if (i == 2)
                k = 6;
            else if (i == 3)
                k = 7;
            else if (i == 4)
                k = 9;
            for (; xx <= k; xx += x_step) {
                x1 = x0 + 1;
                yy[i] = cal1(xx, i);
                y1 = (yy[i] >= 0) ? (height / 2 - (int) (yy[i] / y_step))
                        : (height / 2 + (int) (Math.abs(yy[i]) / y_step));
                g.drawLine(x0, y0, x1, y1);
                x0 = x1;
                y0 = y1;
            }
        }
        g.fillOval((int) (0.0 * width / 9), (int) (4 * (height / 8)), 5, 5);
        g.fillOval((int) (0.5 * width / 9), (int) (2.4 * (height / 8)), 5, 5);
        g.fillOval((int) (1 * width / 9), (int) (2. * (height / 8)), 5, 5);
        g.fillOval((int) (6 * width / 9), (int) (2. * (height / 8)), 5, 5);
        g.fillOval((int) (7 * width / 9), (int) (2.5 * (height / 8)), 5, 5);
        g.fillOval((int) (9 * width / 9 - 4), (int) (4 * (height / 8)), 5, 5);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("曲线图");
        window.setSize(488, 392);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new Image());
        window.setVisible(true);
    }

    ;


}
