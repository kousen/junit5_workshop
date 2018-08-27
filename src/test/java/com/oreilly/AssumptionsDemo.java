package com.oreilly;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionsDemo {
    private Stack<String> stack = new Stack<>();

    @Test
    void onlyRunIfStackIsNotEmpty() {
        int size = stack.size();

        // Make sure stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        stack.pop();
        assertEquals(size - 1, stack.size());
    }
}
