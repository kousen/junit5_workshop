package com.oreilly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

// One instance of test for all tests
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringTests {
    private String demo;

    @BeforeEach  // reset the attribute
    void reinitializeDemoString() {
        demo = "this is my demo string";
    }

    @Test
    void testStringLength() {
        String s = "this is a string";
        assertNotNull(s);
        assertEquals(16, s.length());
    }

    @Test
    void testStringLengthUsingAssertAll() {
        String s = "this is a string";
        assertAll(() -> assertNotNull(s),
                  () -> assertEquals(16, s.length()));
        // could add another assertion here
    }

    @Test
    void demoStringHasNotChanged() {
        // deferred creation of error string, using Supplier<String>
        assertEquals("this is my demo string", demo,
                     () -> "string should not have changed");
    }

    @Test  // Dirties state (context)
    void demoStringIsChanged() {
        demo += " and more";
        String[] strings = demo.split(" ");
        assertEquals(7, strings.length);
    }

}
