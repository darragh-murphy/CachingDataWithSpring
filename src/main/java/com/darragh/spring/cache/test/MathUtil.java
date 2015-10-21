package com.darragh.spring.cache.test;

import java.math.BigInteger;

public class MathUtil {

    public static double arithmeticMean(long[] m) {
        long sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return sum / m.length;
    }

    public static BigInteger fibonacci(long n) {

        if (n < 0) throw new IllegalArgumentException();

        BigInteger n0 = BigInteger.ZERO;
        BigInteger n1 = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            BigInteger temp = n1;
            n1 = n1.add(n0);
            n0 = temp;
        }
        return n0;
    }
}
