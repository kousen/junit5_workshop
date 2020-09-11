package com.nfjs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListTest {
    private final List<String> strings = Arrays.asList("this", "is",
            "a", "list", "of", "strings");
    private List<String> otherStrings;
    private final List<String> strings1 = new ArrayList<>();

    @BeforeAll
    static void first() {
        System.out.println("Before any tests");
    }

    @BeforeEach
    void init() {
        otherStrings = strings;
        strings1.add("this");
        strings1.add("is");
        strings1.add("a");
        strings1.add("list");
        strings1.add("of");
        strings1.add("strings");
    }

    @Test @DisplayName("Check the number of strings in the list")
    void testSize() {
        assertEquals(6, strings.size());
    }

    @Test
    void testReferenceEquality() {
        assertAll(
                () -> assertEquals(strings, strings1),
                () -> assertSame(otherStrings, strings),
                () -> assertNotSame(strings, strings1)
        );
    }

    @Test
    // @Test(expected=ArrayIndexOutOfBoundsException)
    void testThrowsExceptionBeyondEnd() {
        Exception ex = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> assertNotNull(strings.get(20)));
        assertTrue(ex.getMessage().contains("20"));
    }
}
