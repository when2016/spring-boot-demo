package com.lmcat.naturalspline.bak;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 样条插值法
 *
 * @author 91911
 */
public class SplineInterpolatorImpl {
    public static void main(String[] args) {
//        String[] source = new String[]{
//                "0,1,2,3    0,1,1,0    2.5",
//                "0,1,2,3    0,1,1,0    1.5"
//        };
        // 判断传入参数的长度，必须输入两个参数（输入文件和输出文件），否则报错
        if (args.length != 2) {
            System.out.println("请输入原文件和输出文件的路径！！");
            System.exit(0);
        }
        SplineInterpolatorImpl splineInterpolatorImpl = new SplineInterpolatorImpl();
        List<String> source = splineInterpolatorImpl.getFileContent(args[0]);
        File file = new File(args[1]);
//        List<String> source = splineInterpolatorImpl.getFileContent("C:/Users/91911/Desktop/test.txt");
//        File file = new File("C:/Users/91911/Desktop/result.txt");
        for (String s1 : source) {
            String splited[] = s1.split("\t");
            double[] x = splineInterpolatorImpl.String2Double(splited[0]);
            double[] y = splineInterpolatorImpl.String2Double(splited[1]);
            double z = Double.parseDouble(splited[2]);
            double result = splineInterpolatorImpl.caculate(x, y, z);
            exportFile(s1 + "\t" + result, file);
//            System.out.println(splineInterpolatorImpl.caculate(x, y, z));
        }
    }

    // 读取配置文档
    public static List<String> getFileContent(String filepath) {
        List<String> list = new ArrayList<>();
        BufferedReader br;
        String rec;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath)), "GBK"));
            while ((rec = br.readLine()) != null) {
                if (StringUtils.isNotEmpty(rec.trim())) {
                    list.add(rec);
                }
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            System.out.println("转码出错！");
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("未找到配置文件 " + filepath + " ，请检查该路径是否正确！");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //写文件
    public static void exportFile(String content, File file) {
        try {
            FileWriter out = new FileWriter(file, true);
            out.write(content + "\r\n");
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("！IO异常，写文件异常");
        }
    }

    //样条计算法
    public double caculate(double[] x, double[] y, double z) {
        SplineInterpolator sp = new SplineInterpolator();
        PolynomialSplineFunction f = sp.interpolate(x, y);
        return f.value(z);
    }

    //将字符型转换为double型
    public static double[] String2Double(String str) {
        double[] d = {1};
        if (str.contains(",")) {
            String[] arr = str.split(",");
            d = new double[arr.length];
            for (int i = 0; i < arr.length; i++) {
                // System.out.println(arr[i]);
                d[i] = Double.valueOf(arr[i].trim());
            }
        }
        return d;
    }
}
