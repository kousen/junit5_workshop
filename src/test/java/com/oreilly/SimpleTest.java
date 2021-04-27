package com.oreilly;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {
    @Test
    void firstTest() {
        assertEquals(2, 1 + 1);
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "Sum sound have been 5";
    }

    @Test // @Disabled("Demo of string failure message")
    void failureWithAStringMessage() {
        int x = 2;
        int y = 3;
        assertEquals(5, x + y, () -> "Sum should be " + (x + y));
    }
}
