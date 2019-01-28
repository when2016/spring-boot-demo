package com.lmcat.constrainered;

import org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class TestMain {
    public static void main(String[] args) {
//        System.out.println(Math.sqrt(2));
//        System.out.println(Math.pow(2,3));

        double [] xDouble = new double [] {24.0, 35.0, 67.0, 78.0,79.0};
        double [] yDouble = new double [] {13.0, 45.0, 8.0, 45.0, 23.0};

        AkimaSplineInterpolator asi = new AkimaSplineInterpolator();
        PolynomialSplineFunction psf = asi.interpolate(xDouble, yDouble);

        for (PolynomialFunction pf : psf.getPolynomials()) {
            System.out.println(pf.polynomialDerivative());
        }

        System.out.println(2*3);
        //https://jetcracker.wordpress.com/2014/12/26/constrained-cubic-spline-java/
        ConstrainedSplineInterpolator splineInterpolator = new ConstrainedSplineInterpolator();
        splineInterpolator.interpolate(xDouble,yDouble);

    }
}
