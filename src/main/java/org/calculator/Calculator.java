package org.calculator;

public class Calculator {
    public double squareRoot(double x) {
        return Math.sqrt(x);
    }

    public long factorial(int x) {
        long fact = 1;
        for (int i = 1; i <= x; i++) fact *= i;
        return fact;
    }

    public double log(double x) {
        return Math.log(x);
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}
