package com.oreilly;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTests {
    private final Stack<String> stack = new Stack<>();

    @Test @DisplayName("pop() on empty stack throws exception in regular Java")
    void checkingForTheException() {
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test @Disabled("enable for demo only")
    void callPopOnEmptyStackToSeeException() {
        stack.pop();
    }

    @Test @DisplayName("pop() on empty stack skipped by precondition failure")
    void callPopOnEmptyStack_protected() {
        int size = stack.size();

        // Pre-condition violation: Only continue if stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        // We wanted to test the pop method, but only if the precondition is true
        String value = stack.pop();

        // Post-condition: Should be one less item than before
        assertAll(
                () -> assertEquals(size - 1, stack.size()),
                () -> assertEquals("element", value)
        );
    }

    @Test @DisplayName("pop() on non-empty stack succeeds")
    void popOnNotEmptyStack() {
        stack.push("element");

        int size = stack.size();

        // Pre-condition satisfied: Only continue if stack is not empty
        assumeTrue(size > 0, "Stack must not be empty");

        String value = stack.pop();

        // Post-condition: Should be one less (one fewer?) item than before
        assertAll(
                () -> assertEquals(size - 1, stack.size()),
                () -> assertEquals("element", value)
        );
    }

    @Test @DisplayName("assumingThat both checks and executes")
    void assumingThatWithPop() {
        assumingThat(!stack.isEmpty(), stack::pop);

        // assumingThat does NOT skip the rest of the method
        System.out.println("This line is still printed");
        assertAll(
                () -> assertEquals(0, stack.size()),
                () -> assertThrows(EmptyStackException.class, stack::pop),
                () -> System.out.println("This line is also printed")
        );
    }
}
