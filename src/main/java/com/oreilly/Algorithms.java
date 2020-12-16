package com.oreilly;

import java.math.BigInteger;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Algorithms {

    public long factorial(long x) {
        if (x == 0 || x == 1) return 1;
        return x * factorial(x - 1);
    }

    public long fact(long n) {
        if (n < 0) throw new IllegalArgumentException(
                "Argument must be positive");
        if (n == 0 || n == 1) return 1;
        return LongStream.rangeClosed(2, n)
                .reduce(1, (acc, i) -> acc * i);
    }

    public BigInteger factReduce(long n) {
        if (n < 0) throw new IllegalArgumentException(
                "Argument must be positive");
        if (n == 0 || n == 1) return BigInteger.ONE;
        return Stream.iterate(BigInteger.valueOf(2), value -> value.add(BigInteger.ONE))
                .limit(n-1)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
