package com.oreilly.functionalinterfaces;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("Convert2MethodRef")
public class LazyErrorMessageTest {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private String createErrorMessage() {
        System.out.println("Creating error message...");
        return "Error message";
    }

    private String getLogMessage() {
        log.info("Creating log message...");
        return "Log message";
    }

    // eager evaluation
    @Test
    public void eagerTest() {
        log.fine(getLogMessage());
        assertTrue(true, createErrorMessage());
    }

    // lazy evaluation
    @Test
    public void lazyTest() {
        log.fine(() -> getLogMessage());
        assertTrue(true, () -> createErrorMessage());
        assertTrue(true, this::createErrorMessage);
    }
}
