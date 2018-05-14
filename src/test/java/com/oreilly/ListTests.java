package com.oreilly;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTests {
    private List strings = Arrays.asList("this", "is", "a",
                                         "list", "of", "strings");

    @Test
    void thereAreSixStrings() {
        assertEquals(6, strings.size());
    }
}
