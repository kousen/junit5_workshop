package com.oreilly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// From https://junit.org/junit5/docs/current/user-guide/#writing-tests-display-names
@DisplayName("A special test case")
public class DisplayNameTests {
    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }
}
