package com.oreilly.functionalinterfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("Convert2MethodRef")
public class LazyErrorMessageTest {

    // method to create an error message
    // could add a delay to simulate complex process...
    private String createErrorMessage() {
        System.out.println("Creating error message...");
        return "Error message";
    }

    // eager evaluation: error message created even when test passes
    @Test
    public void eagerTest() {
        assertTrue(true, createErrorMessage());
    }

    // lazy evaluation: error message created only when test fails
    @Test
    public void lazyTest() {
        assertTrue(true, () -> createErrorMessage());
        assertTrue(true, this::createErrorMessage);
    }
}
