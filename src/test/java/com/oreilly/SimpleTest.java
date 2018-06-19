package com.oreilly;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {
    @Test
    void firstTest() {
        assertEquals(2, 1 + 1);
    }

    @Test @Disabled("Demo of string failure message")
    void failureWithAStringMessage() {
        int x = 2;
        int y = 3;
        assertEquals(2, x + y, "Sum should have been 2");
    }
}
