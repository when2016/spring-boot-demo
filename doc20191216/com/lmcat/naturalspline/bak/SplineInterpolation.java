//package com.lmcat.naturalspline;
//
//public class SplineInterpolation {
//    double x[], y[];//采样点的(x,y)
//    int n = 0;//采样点的数目
//    //s1(x)=y1+b1(x-x1)+c1(x-x1)^2+d1(x-x1)^3
//    // 只需要求出a[], b[], c[], d[]就可以得出n-1个自然三次样条曲线方程
//    double a[], b[], c[], d[];
//    //y1=a1
//    // xx=xi+1 - xi
//    // yy=yi+1 - yi
//    double xx[], yy[];
//
//    public SplineInterpolation(double x[], double y[], int n) {
//        this.n = n;
//        this.x = new double[this.n];
//        this.y = new double[this.n];
//        this.x = x;
//        this.y = y;
//        this.a = new double[this.n - 1];
//        this.b = new double[this.n - 1];
//        this.c = new double[this.n];
//        this.d = new double[this.n - 1];
//        this.xx = new double[this.n - 1];
//        this.yy = new double[this.n - 1];
//    }
//
//    public void Interpolation() {
//
//        //解三角线方程组 Ax=f 可化为求解两个三角形方程组 => A=PQ
//        // Py=f Qx=y
//        // p1=b1  qi=ci/pi(i=1,2...,n-1) pi=bi-ai*qi-1(i=2,3...,n)
//        // bi相当于(xx[i-1]+xx[i])*2  ci相当于xx[i] ai相当于xx[i-1]
//        // 此处的a, b, c和上边的方程系数不是一回事
//        double a2[], b2[], c2[];
//        a2 = new double[n];
//        b2 = new double[n];
//        c2 = new double[n - 1];
//        double f[];
//        f = new double[n];
//        for (i = 0; i
//        {
//            if (i == 0) {
//                b2[i] = 1;
//                c2[i] = 0;
//                f[i] = 0;
//            } else if (i == n - 1) {
//                a2[i] = 0;
//                b2[i] = 1;
//                f[i] = 0;
//            } else {
//                a2[i] = xx[i - 1];
//                b2[i] = 2 * (xx[i - 1] + xx[i]);
//                c2[i] = xx[i];
//                f[i] = 3 * (yy[i] / xx[i] - yy[i - 1] / xx[i - 1]);
//            }
//        }
//        double p[], q[];
//        p = new double[n];
//        q = new double[n - 1];
//        p[0] = b2[0];
//        for (i = 0; i < n; i++) {
//            if (i > 0) {
//                p[i] = b2[i] - a2[i] * q[i - 1];
//            }
//            if (i < n - 1) {
//                q[i] = c2[i] / p[i];
//            }
//        }
////求解Py=f
////y1=f1/p1
////yi=(fi-ai*yi-1)/pi (i=2,3...,n)
//        double y2[];
//        y2 = new double[n];
//        y2[0] = f[0] / p[0];
//        for (i = 1; i
//        {
//            y2[i] = (f[i] - a2[i] * y2[i - 1]) / p[i];
//        }
////求解Qx=y x就是要求的c[]
////xn=yn
////xi=yi-qi*xi+1
//        c[n - 1] = y2[n - 1];
//        for (i = n - 2; i >= 0; i--) {
//            c[i] = y2[i] - q[i] * c[i + 1];
//        }
//
//        public double computeY ( double inputX)
//        {
//            int i = 0;
//            for (i = 0; i
//            {
//                if (inputX >= x[i] && inputX < x[i + 1]) {
//                    break;
//                }
//            }
////计算相应曲线中的Y坐标
//            double returnY = 0.0;
//            double inputX_x_1 = inputX - x[i];
//            double inputX_x_2 = inputX_x_1 * inputX_x_1;
//            double inputX_x_3 = inputX_x_1 * inputX_x_2;
//            returnY = a[i] + b[i] * inputX_x_1 + c[i] * inputX_x_2 + d[i] * inputX_x_3;
//            return returnY;
//        }
//
//    }
//
