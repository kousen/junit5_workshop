package com.oreilly;

import java.util.stream.IntStream;

public class UtilityMethods {
    public static boolean isPrime(int value) {
        return value == 2 ||
                IntStream.rangeClosed(2, (int) (Math.sqrt(value) + 1))
                        .noneMatch(i -> value % i == 0);
    }

}
