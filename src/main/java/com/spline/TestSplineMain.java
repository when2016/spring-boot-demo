package com.spline;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSplineMain {

    //窗口
    public static class MyFrame extends JFrame {
        public static final String TITLE = "Java图形绘制";

        public static final int WIDTH = 1000;
        public static final int HEIGHT = 1000;

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            //设置窗口标题 和 窗口大小
            setTitle(TITLE);
            setSize(WIDTH, HEIGHT);

            //设置窗口关闭按钮的默认操作(点击关闭时退出进程)
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            //把窗口位置设置到屏幕的中心
            setLocationRelativeTo(null);

            //设置窗口的内容面板
            TestSplineMain.MyPanel panel = new TestSplineMain.MyPanel(this);
            setContentPane(panel);
        }
    }

    //内容面板
    public static class MyPanel extends JPanel {
        private MyFrame frame;

        public MyPanel(MyFrame frame) {
            super();
            this.frame = frame;
        }

        /**
         * 绘制面板的内容: 创建 JPanel 后会调用一次该方法绘制内容,
         * 之后如果数据改变需要重新绘制, 可调用 updateUI() 方法触发
         * 系统再次调用该方法绘制更新 JPanel 的内容。
         */
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 重新调用 Graphics 的绘制方法绘制时将自动擦除旧的内容
            // 4. 椭圆
            drawOval(g);

        }


        /**
         * 4. 椭圆 (实际上通过绘制360度的圆弧/扇形也能达到绘制圆/椭圆的效果)
         */
        private void drawOval(Graphics g) {
            frame.setTitle("4. 椭圆");
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //g2d.setColor(Color.RED);

            // 1. 绘制一个圆: 圆的外切矩形 左上角坐标为(0, 0), 宽高为100
            //g2d.drawOval(0, 0, 100, 100);

            //g2d.setColor(Color.GRAY);

            Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN,
                    Color.YELLOW, Color.CYAN};
            ;
            int colorsIndex = 0;
            int stroke = 5;
            int rate = 80;
            int w = 500;
            int h = 500;


            List<SplinePoint> splinePointList = new ArrayList<SplinePoint>();

            int amountOfPoints = 6;
            System.out.println("点数===" + amountOfPoints);
            Random rng = new Random();
            //1.随机生成几个点
//            for (int i = 0; i < amountOfPoints; ++i) {
//                int xPos = rng
//                        .nextInt(w
//                                - SplinePoint.GRAPHIC_POINT_DIAMETER)
//                        + SplinePoint.GRAPHIC_POINT_RADIUS;
//                int yPos = rng
//                        .nextInt(h
//                                - SplinePoint.GRAPHIC_POINT_DIAMETER)
//                        + SplinePoint.GRAPHIC_POINT_RADIUS;
//
//                Color color = colors[colorsIndex % colors.length];
//                System.out.println("x=" + xPos + ",y=" + yPos + ",color index=" + (colorsIndex % colors.length));
//
//                splinePointList.add(new SplinePoint(xPos, yPos, color));
//                colorsIndex++;
//            }

            Color color = colors[0 % colors.length];
            splinePointList.add(new SplinePoint(866,185,color));
            color = colors[1 % colors.length];
            splinePointList.add(new SplinePoint(967,719,color));
            color = colors[2 % colors.length];
            splinePointList.add(new SplinePoint(627,702,color));
            color = colors[3 % colors.length];
            splinePointList.add(new SplinePoint(83,194,color));
            color = colors[4 % colors.length];
            splinePointList.add(new SplinePoint(799,225,color));
            color = colors[5 % colors.length];
            splinePointList.add(new SplinePoint(654,439,color));



            Spline spline = new Spline();
            //2.取中心点的坐标
            /** Points of the spline. */
            ArrayList<Point2D.Double> splinePoints = spline.getSplinePointsCenters(splinePointList);
            spline.setSplinePoints(splinePoints);
            //3.两点之间生成曲线的点
            ArrayList<ArrayList<Point2D.Double>> curvePoints = spline.getCurvePoints();
            System.out.println(curvePoints.size());

            //4.绘图
            for (int i = 0; i < splinePoints.size(); ++i) {
                if (i == splinePoints.size() - 1) {
                    break;
                }
                System.out.println("x=" + splinePoints.get(i).getX() + ",y=" + splinePoints.get(i).getY());
                ArrayList<Point2D.Double> curve = curvePoints.get(i);
                for (Point2D.Double point : curve) {
                    //System.out.println("x==" + point.getX() + ",y==" + point.getY());
                    //g2d.setColor(splinePoints.get(i).getGraphicPointColor());
                    //g2d.fillOval((int) point.getX(), (int) point.getY(), 100, 3);
                    g2d.drawOval((int) point.getX(), (int) point.getY(), 3, 3);
                }

            }

            // 2. 填充一个椭圆
           ///g2d.fillOval(120, 100, 100, 150);

            g2d.dispose();
        }

    }



    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //创建窗口对象
                MyFrame frame = new MyFrame();
                //显示窗口
                frame.setVisible(true);
            }
        });
    }

}
