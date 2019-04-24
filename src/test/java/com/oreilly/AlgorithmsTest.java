package com.oreilly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {
    private Algorithms algorithms = new Algorithms();

    @Test
    @DisplayName("Checking for small numbers")
    void checkFactorialForSmallNumbers() {
        assertAll(
                () -> assertEquals(1, algorithms.factorial(0)),
                () -> assertEquals(1, algorithms.factorial(1)),
                () -> assertEquals(2, algorithms.factorial(2)),
                () -> assertEquals(6, algorithms.factorial(3)),
                () -> assertEquals(24, algorithms.factorial(4)),
                () -> assertEquals(120, algorithms.factorial(5))
        );

        // Show problem with using long for result
        assertTrue(algorithms.factorial(50) < 0);
    }

    @ParameterizedTest(name = "The factorial of {0} is {1}")
    @CsvSource({"0, 1", "1, 1",
            "2, 2", "3, 6",
            "4, 24", "5, 120"})
    void checkFactorialFromCSVSource(long num, long expected) {
        assertEquals(expected, algorithms.fact(num));
    }

    @Test
    void checkExceptionForNegativeArg() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                () -> algorithms.fact(-1));
        assertEquals("Argument must be positive", ex.getMessage());
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testBigIntegerFactorial(AlgoTestData data) {
        assertEquals(BigInteger.valueOf(data.getResult()),
                algorithms.factReduce(data.getNum()));
    }

    private static Stream<AlgoTestData> testData() {
        return Stream.of(new AlgoTestData(0, 1),
                new AlgoTestData(1, 1),
                new AlgoTestData(2, 2),
                new AlgoTestData(3, 6),
                new AlgoTestData(4, 24),
                new AlgoTestData(5, 120));
    }

    @Test
    @Tag("slow")
    void showReduceMethodWithBigIntegerWorksForLargeValues() {
        assertEquals( 35_660, getResultLength(10_000));
        assertEquals( 77_338, getResultLength(20_000));
        assertEquals(213_237, getResultLength(50_000));
        assertTimeout(Duration.ofSeconds(5),
                () -> assertEquals(456_574, getResultLength(100_000)));
    }

    private int getResultLength(int i) {
        return algorithms.factReduce(i).toString().length();
    }
}
