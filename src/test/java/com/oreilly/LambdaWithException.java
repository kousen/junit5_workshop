package com.oreilly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;

public class LambdaWithException {

    @Test
    void testRunnableWithoutException() {
        assertNotNull(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Runnable");
            }
        });
    }

    @Test
    void testRunnableWithException() {
        assertNotNull(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception("checked exception");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test @Disabled
    void testRunnableWithLambda() {
        assertThrows(Exception.class, () -> System.out.println("Inside lambda"));
    }

    @Test
    void testRunnableWithLambdaWithException() {
        assertThrows(Exception.class, () -> {
            throw new Exception();
        });
    }
}
