package com.oreilly.assertj;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class ExceptionAssertionsTest {
    
    @Test
    void simpleExceptionAssertion() {
        assertThatThrownBy(() -> {
            throw new IllegalArgumentException("Invalid argument");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid argument");
    }
    
    @Test
    void exceptionAssertionWithCause() {
        Throwable throwable = new IllegalStateException("Outer exception",
                new IOException("Inner exception"));
        
        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Outer exception")
                .hasCause(new IOException("Inner exception"))
                .cause()
                    .isInstanceOf(IOException.class)
                    .hasMessage("Inner exception");
    }
    
    @Test
    void specificExceptionTypeAssertion() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    throw new IllegalArgumentException("wrong value");
                })
                .withMessage("wrong value")
                .withMessageContaining("wrong")
                .withNoCause();
    }
    
    @Test
    void commonExceptionTypeShortcuts() {
        // AssertJ provides shortcuts for common exceptions
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    throw new IllegalArgumentException("Invalid input");
                })
                .withMessage("Invalid input");
                
        assertThatNullPointerException()
                .isThrownBy(() -> {
                    throw new NullPointerException("Value cannot be null");
                })
                .withMessage("Value cannot be null");
                
        assertThatIllegalStateException()
                .isThrownBy(() -> {
                    throw new IllegalStateException("Invalid state");
                });
                
        assertThatIndexOutOfBoundsException()
                .isThrownBy(() -> {
                    throw new IndexOutOfBoundsException("Index 5 out of bounds");
                });
                
        assertThatIOException()
                .isThrownBy(() -> {
                    throw new IOException("File not found");
                });
    }
    
    @Test
    void assertingNoExceptionIsThrown() {
        // Using assertThatNoException
        assertThatNoException().isThrownBy(() -> {
            // Code that should not throw
            String value = "test";
            value.length();
        });
        
        // Using assertThatCode
        assertThatCode(() -> {
            // Code that should not throw
            int sum = 1 + 1;
        }).doesNotThrowAnyException();
    }
}