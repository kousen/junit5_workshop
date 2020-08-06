package com.oreilly.vintage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MultipleFailures {
    private final List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);

    @Test
    public void checkMultipleProperties() {
        assertTrue(numbers.stream().allMatch(n -> n % 2 == 1));
    }

    @Test
    public void checkSize() {
        assertEquals(6, numbers.size());
    }
}
