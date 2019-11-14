package com.oreilly;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionsDemo {
    private Stack<String> stack = new Stack<>();

    @Test
    void stackIsEmpty() {
        int size = stack.size();

        // Pre-condition violation: Only continue if stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        stack.pop();

        // Post-condition: Should be one less item than before
        assertEquals(size - 1, stack.size());
    }

    @Test
    void stackIsNotEmpty() {
        stack.push("element");

        int size = stack.size();

        // Pre-condition satisfied: Only continue if stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        stack.pop();

        // Post-condition: Should be one less item than before
        assertEquals(size - 1, stack.size());
    }
}
