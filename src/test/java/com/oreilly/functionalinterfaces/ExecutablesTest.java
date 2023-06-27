package com.oreilly.functionalinterfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Assertions that take an Executable:
//   - assertAll
//   - assertTimeout
//   - assertTimeoutPreemptively
//   - assertThrows
//   - assertThrowsExactly
public class ExecutablesTest {

    @Test
    public void testExecutable() {
        assertAll(
                () -> assertTimeout(java.time.Duration.ofMillis(100),
                        () -> Thread.sleep(50)),
                () -> assertTimeoutPreemptively(
                        java.time.Duration.ofMillis(100),
                        () -> Thread.sleep(50)),
                () -> assertThrows(Exception.class,
                        () -> { throw new NullPointerException(); }),
                () -> assertThrowsExactly(NullPointerException.class,
                        () -> { throw new NullPointerException(); }
        ));
    }
}
