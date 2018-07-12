package com.oreilly.vintage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JUnit4ListTests {
    private List<String> strings = Arrays.asList("this", "is", "a",
                                         "list", "of", "strings");

    @Test
    public void size() {
        assertEquals(6, strings.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void accessBeyondEndThrowsException() {
        strings.get(99);
        assertEquals(6, strings.size());
    }
}
