//package com.lmcat.naturalspline;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//
//public class DrawSplineLine extends JPanel implements MouseListener, MouseMotionListener {
//    public SplineInterpolation sp;//声明对象
//    int n = 0;//原采样点集合的大小
//    int maxNum = 10000; //扩大后的采样点集合的大小，目前设为10000
//    double maxY[]; //扩大后的采样点y集合
//    double maxX[]; //扩大后的采样点x集合
//    double x[]; //源采样点x集合
//    double y[]; //源采样点y集合
//
//    public DrawSplineLine(double x[], double y[], int n, SplineInterpolation sp)
//    {
//        this.sp = sp;
//        this.n = n;
//        this.x = new double[n];
//        this.y = new double[n];
//        this.x = x;
//        this.y = y;
////鼠标事件监听，一定不要忘加这两个函数
//        addMouseListener(this);
//        addMouseMotionListener(this);
//    }
//    public void Paint(Graphics2D g)
//    {
//        g.setColor(Color.RED);
//        g.setStroke(new BasicStroke(4.0f));
//        for(int i=0; i
//        {
////将每个坐标点扩大为自身的100倍，之后在屏幕上显示
//            g.drawLine((int)(maxX[i]*100), (int)(maxY[i]*100), (int)(maxX[i+1]*100), (int)(maxY[i+1]*100));
//        }
//    }
//    @Override
//    protected void paintComponent(Graphics arg0) {
//        Graphics2D g = (Graphics2D) arg0;
//        g.setColor(Color.WHITE);
//        g.fill3DRect(0, 0, getWidth(), getHeight(), true);
//        Paint(g);
//    }
//
//    public void ChangeXY()
//    {
//        maxNum = 10000;
//        maxX = new double[maxNum];
//        maxY = new double[maxNum];
//        int maxFlag = 0;
//        for(int i=0; i
//        {
//            maxX[maxFlag] = x[i];
//            maxY[maxFlag] = y[i];
//            if(i
//            {
//                while(maxX[maxFlag]+0.05 < x[i+1])
//                {
//                    maxFlag ++;
//                    maxX[maxFlag] = maxX[maxFlag-1] + 0.05;
//                    maxY[maxFlag] = sp.computeY(maxX[maxFlag]);
//                }
//            }
//            maxFlag ++;
//        }
//        maxNum = maxFlag;
//    }
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        int i = 0;
//        for(i=0; i
//        {
//            if(Math.abs(e.getX()-x[i]*100)<20 && Math.abs(e.getY()-y[i]*100)<20)
//            {
//                //生成新的采样点
//                x[i] = (double)e.getX()/100;
//                y[i] = (double)e.getY()/100;
//                break;
//            }
//        }
////输出鼠标焦点的坐标
//        System.out.println("x="+e.getX()+":y="+e.getY());
//        //实时的更新大采样点集合
//        ChangeXY();
////重画调用函数
//        repaint();
//    }
//    public static void main(String[] args) {
//        //测试数据 x[] y[]
//        double x[] = {1, 2, 4, 5};
//        double y[] = {2, 1, 4, 3};
//        int n = 4; //测试数据集的大小
//
//        SplineInterpolation sp = new  SplineInterpolation(x, y, n);
//        sp.Interpolation();
//        //输出n-1个曲线方程的a,b,c,d参数
//        //曲线方程 Sn(x)=a+b(x-xn)+c(x-xn)^2+d(x-xn)^3
//        for(int i=0; i
//        {
//            System.out.println(sp.a[i]+" "+sp.b[i]+" "+sp.c[i]+" "+sp.d[i]);
//        }
//        //*************绘制曲线部分***********************
//        DrawSplineLine ds = new DrawSplineLine(x, y, n, sp);
//        //根据源采样点集合生成大的数据集
//        ds.ChangeXY();
//        //============画图老套路===========
//        JFrame jf = new JFrame("自然三次样条插值曲线(鼠标可拖拽版)");
//        jf.setSize(800, 800);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setVisible(true);
//        jf.add(ds);
//        //===============================
//        //*************************************
//    }
//
//    //***********************无用函数***********************
//    @Override
//    public void mouseMoved(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//    }
//    @Override
//    public void mousePressed(MouseEvent e) {
//    }
//    @Override
//    public void mouseReleased(MouseEvent e) {
//    }
//    @Override
//    public void mouseEntered(MouseEvent e) {
//    }
//    @Override
//    public void mouseExited(MouseEvent e) {
//    }
//}
