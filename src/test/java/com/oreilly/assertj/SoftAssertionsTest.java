package com.oreilly.assertj;

import com.oreilly.Book;
import com.oreilly.Person;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class SoftAssertionsTest {

    private final Person jeanLuc = new Person("Jean-Luc", "Picard",
            LocalDate.of(2305, Month.JULY, 13));
            
    private final Book effectiveJava = new Book("9780134685991", 
            "Effective Java", "Joshua Bloch", 
            LocalDate.of(2018, Month.JANUARY, 6));

    @Test
    void standardAssertionsStopAtFirstFailure() {
        // If any of these assertions fail, the test stops immediately
        assertThat(jeanLuc.getFirst()).isEqualTo("Jean-Luc");
        assertThat(jeanLuc.getLast()).isEqualTo("Picard");
        assertThat(jeanLuc.getDob()).isEqualTo(LocalDate.of(2305, Month.JULY, 13));
    }
    
    @Test
    void softlyAssertAll() {
        // Using assertSoftly to collect all failures
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(jeanLuc.getFirst()).isEqualTo("Jean-Luc");
            softly.assertThat(jeanLuc.getLast()).isEqualTo("Picard");
            softly.assertThat(jeanLuc.getDob()).isEqualTo(LocalDate.of(2305, Month.JULY, 13));
            
            // Even if this fails, the test will continue to check all assertions
            softly.assertThat(effectiveJava.title()).startsWith("Effective");
            softly.assertThat(effectiveJava.author()).isEqualTo("Joshua Bloch");
        });
    }
    
    @Test
    void softAssertionsInstance() {
        // Using a SoftAssertions instance
        SoftAssertions softly = new SoftAssertions();
        
        softly.assertThat(jeanLuc.getFirst()).isEqualTo("Jean-Luc");
        softly.assertThat(jeanLuc.getLast()).isEqualTo("Picard");
        softly.assertThat(jeanLuc.getDob()).isEqualTo(LocalDate.of(2305, Month.JULY, 13));
        
        // Report all collected errors
        softly.assertAll();
    }
    
    @Test
    void autoCloseableSoftAssertions() {
        // Using try-with-resources for automatic assertAll() call
        try (var softly = new org.assertj.core.api.AutoCloseableSoftAssertions()) {
            softly.assertThat(jeanLuc.getFirst()).isEqualTo("Jean-Luc");
            softly.assertThat(jeanLuc.getLast()).isEqualTo("Picard");
            softly.assertThat(jeanLuc.getDob()).isEqualTo(LocalDate.of(2305, Month.JULY, 13));
            
            // No need to call assertAll() - it will happen automatically when the try block exits
        }
    }
    
    // Example of using SoftAssertionsExtension
    @ExtendWith(SoftAssertionsExtension.class)
    static class SoftAssertionExtensionTest {
        
        private final Person jeanLuc = new Person("Jean-Luc", "Picard",
                LocalDate.of(2305, Month.JULY, 13));
        
        // The extension will inject a SoftAssertions instance
        @Test
        void softAssertionInjection(SoftAssertions softly) {
            softly.assertThat(jeanLuc.getFirst()).as("first name").isEqualTo("Jean-Luc");
            softly.assertThat(jeanLuc.getLast()).as("last name").isEqualTo("Picard");
            softly.assertThat(jeanLuc.getDob()).as("date of birth")
                    .isEqualTo(LocalDate.of(2305, Month.JULY, 13));
                    
            // No need to call assertAll(), the extension does that automatically
        }
    }
}