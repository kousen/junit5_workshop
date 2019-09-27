package com.oreilly;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionsDemo {
    private Stack<String> stack = new Stack<>();

    @Test
    void onlyRunIfStackIsNotEmpty() {
        int size = stack.size();

        // Pre-condition: Stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        stack.pop();
        // Post-condition: Should be one less item than before
        assertEquals(size - 1, stack.size());
    }
}
