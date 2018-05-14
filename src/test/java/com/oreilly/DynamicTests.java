package com.oreilly;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTests {
    private List<Person> beatles = Arrays.asList(
            new Person("John", "Lennon", LocalDate.of(1940, Month.OCTOBER, 9)),
            new Person("Paul", "McCartney", LocalDate.of(1942, Month.JUNE, 18)),
            new Person("George", "Harrison", LocalDate.of(1943, Month.FEBRUARY, 25)),
            new Person("Ringo", "Starr", LocalDate.of(1940, Month.JULY, 7)));

    @TestFactory
    Stream<DynamicTest> generateTestStream() {
        return IntStream.iterate(0, n -> n + 2)
                .limit(10)
                .mapToObj(n -> dynamicTest(
                        n + " is even", () -> assertTrue(n % 2 == 0)));
    }

    @TestFactory
    Collection<DynamicTest> minAgeTests() {
        return beatles.stream()
                .map(beatle -> dynamicTest(String.format("%s is over 18", beatle.getName()),
                                           () -> assertTrue(beatle.getAge() >= 18)))
                .collect(Collectors.toList());
    }


}
