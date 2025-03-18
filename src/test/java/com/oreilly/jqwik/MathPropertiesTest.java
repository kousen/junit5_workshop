package com.oreilly.jqwik;

import com.oreilly.Algorithms;
import com.oreilly.UtilityMethods;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MathPropertiesTest {

    @Property
    void absoluteValueOfIntegerIsAlwaysPositive(@ForAll int anInteger) {
        Assume.that(anInteger != Integer.MIN_VALUE);
        
        int result = Math.abs(anInteger);
        
        assertThat(result).isGreaterThanOrEqualTo(0);
    }
    
    @Property
    void multiplicationIsCommutative(@ForAll int a, @ForAll int b) {
        assertThat(a * b).isEqualTo(b * a);
    }
    
    @Property
    void additionIsCommutative(@ForAll int a, @ForAll int b) {
        assertThat(a + b).isEqualTo(b + a);
    }
    
    @Property
    void factorialAlgorithmsProduceSameResult(@ForAll @IntRange(min = 0, max = 20) int n) {
        Algorithms algorithms = new Algorithms();
        
        long recursiveFactorial = algorithms.factorial(n);
        long iterativeFactorial = algorithms.fact(n);
        BigInteger bigIntegerFactorial = algorithms.factReduce(n);
        
        assertThat(recursiveFactorial).isEqualTo(iterativeFactorial);
        assertThat(BigInteger.valueOf(recursiveFactorial)).isEqualTo(bigIntegerFactorial);
    }
    
    @Property
    void primeNumbersAreOnlyDivisibleByOneAndThemselves(
            @ForAll @IntRange(min = 2, max = 1000) int candidate) {
        
        if (UtilityMethods.isPrime(candidate)) {
            // Check divisibility only by 1 and itself
            for (int i = 2; i < candidate; i++) {
                assertThat(candidate % i).isNotEqualTo(0);
            }
        }
    }
    
    @Property
    void fibonacciPropertiesHold(@ForAll @IntRange(min = 0, max = 20) int n) {
        long fib = fibonacci(n);
        
        if (n > 1) {
            assertThat(fib).isEqualTo(fibonacci(n-1) + fibonacci(n-2));
        } else if (n == 0 || n == 1) {
            assertThat(fib).isEqualTo(n);
        }
    }
    
    // Helper method for Fibonacci calculation
    private long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}