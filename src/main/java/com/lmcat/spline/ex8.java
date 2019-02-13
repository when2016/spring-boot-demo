package com.lmcat.spline;

public class ex8 {

    public static double[][] value(double a[], double b[]) {
        int l = a.length;
        double re[][] = new double[l][l];
        re[0][0] = re[l - 1][l - 1] = 2;
        re[0][1] = re[l - 1][l - 2] = 1;
        double h[] = new double[l - 1];
        double u[] = new double[l - 2];
        double λ[] = new double[l - 2];
        double d[] = new double[l];
        /*
         * 采用自然边界条件，d[0]=d[n-1]=0
         */
        for (int i = 0; i < l - 1; i++) {
            h[i] = a[i + 1] - a[i];

        }
        for (int i = 0; i < l - 2; i++) {
            u[i] = h[i] / (h[i] + h[i + 1]);
            λ[i] = 1 - u[i];
            d[i + 1] = 6 * ((b[i + 2] - b[i + 1]) - (b[i + 1] - b[i])) / (h[i] + h[i + 1]);
        }
        d[0] = 6 * (b[1] - b[0]) / h[0];
        d[l - 1] = -6 * (b[l - 1] - b[l - 2]) / h[l - 2];
//		d[0]=6*(b[1]-b[0]-0.2)/h[0];
//		d[l-1]=6*(-1-b[l-1]+b[l-2])/h[l-2];
        for (int i = 1; i < l - 1; i++) {
            re[i][i - 1] = u[i - 1];
            re[i][i] = 2;
            re[i][i + 1] = λ[i - 1];
        }
//		for(int i=0;i<l;i++){
//			for(int j=0;j<l;j++){
//				System.out.print(re[i][j]+"  ");
//				if(j==l-1)
//					System.out.println();
//			}
//
//		}
        double t1[][] = LU(re, d);
//		System.out.println(d[2]);
        USlove(t1, d);
        return re;

    }

    public static double[][] LU(double a[][], double b[]) {
        int ii;
        double m[][] = new double[a.length][a.length - 1];
        for (int i = 0; i < a.length - 1; i++) {
            for (ii = i + 1; ii < a.length; ii++) {
                m[ii][i] = a[ii][i] / a[i][i];
                b[ii] = b[ii] - 1 * m[ii][i] * b[i];

            }
            ii--;
            for (int i1 = i + 1; i1 < a.length; i1++) {
                for (int i2 = i + 1; i2 < a.length; i2++) {
                    a[i2][i1] = a[i2][i1] - (m[i2][i]) * (a[i][i1]);
                }
            }
        }
        return a;
    }

    // 下三角方程组的前代法
    public static double[] LSlove(double p[][], double s[]) {
        double y[] = new double[p.length];
        for (int j = 0; j < p.length; j++) {
            if (p[j][j] == 0)
                break;
            y[j] = s[j] / p[j][j];
            // s[j]=y[j];
            for (int i = j + 1; i < p.length; i++) {
                s[i] = s[i] - p[i][j] * y[j];
            }

        }
        System.out.print("矩阵的二阶导数为：");
        for (int i = 0; i < p.length; i++)
            System.out.print(y[i] + "  ");
        System.out.println();
        return y;
    }

    // 上三角方程组的回代法
    public static double[] USlove(double q[][], double t[]) {
        double x[] = new double[q.length];
        for (int j = q.length - 1; j >= 0; j--) {
            if (q[j][j] == 0)
                break;
            x[j] = t[j] / q[j][j];
            for (int i = 0; i <= j - 1; i++) {
                t[i] = t[i] - q[i][j] * x[j];
            }
        }
        System.out.print("矩阵的二阶导数为：");
        for (int i = 0; i < q.length; i++)
            System.out.print(x[i] + "  ");
        System.out.println();
        return x;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //http://www.pudn.com/Download/item/id/1539787.html
        //根据（0.0,0.0）（0.5,1.6）（1.0,2.0）（6.0,2.0）（7.0,1.5）（9.0,0.0）六个点使用三次样条差值求多项式并作图

        double x[] = {0.0, 0.5, 1.0, 6.0, 7.0, 9.0};
        double y[] = {0.0, 1.6, 2.0, 2.0, 1.5, 0.0};


        double[][] result = value(x, y);



    }

}
