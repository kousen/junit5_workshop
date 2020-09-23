package com.oreilly;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsDemo {
    private final Stack<String> stack = new Stack<>();

    @Test
    void popOnEmptyStack() {
        int size = stack.size();

        // Pre-condition violation: Only continue if stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        // We wanted to test the pop method, but only if the precondition is true
        stack.pop();

        // Post-condition: Should be one less item than before
        assertEquals(size - 1, stack.size());
    }

    @Test
    void popOnNotEmptyStack() {
        stack.push("element");

        int size = stack.size();

        // Pre-condition satisfied: Only continue if stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        stack.pop();

        // Post-condition: Should be one less item than before
        assertEquals(size - 1, stack.size());
    }

    @Test
    void assumingThatWithPop() {
        assumingThat(stack.size() > 0, () -> stack.pop());
    }
}
