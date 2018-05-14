package com.oreilly;

import org.apache.commons.validator.routines.ISBNValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemo {
    @Test
    void assertAllBook() {
        Book book = new Book("149197317X", "Modern Java Recipes", "Ken Kousen", LocalDate.parse("2017-08-26"));
        assertAll("MJR",
                  () -> ISBNValidator.getInstance().isValidISBN10(book.getIsbn()),
                  () -> assertEquals("Modern Java Recipes", book.getTitle()),
                  () -> assertEquals("Ken Kousen", book.getAuthor()));
    }

    @Test
    void assertAllBookWithDependents() {
        Book book = new Book("149197317X", "Modern Java Recipes", "Ken Kousen", LocalDate.parse("2017-08-26"));
        assertAll("MJR",
                  () -> ISBNValidator.getInstance().isValidISBN10(book.getIsbn()),
                  () -> assertNotNull(book.getTitle()),
                  () -> {
                      String[] name = book.getTitle().split(" ");
                      assertEquals(3, name.length);
                      assertAll("title words",
                                () -> assertTrue(name[0].startsWith("M")),
                                () -> assertTrue(name[1].startsWith("J")),
                                () -> assertTrue(name[2].startsWith("R")));
                  });
    }

    @Test
    void exceptionTesting() {
        Exception ex = assertThrows(Exception.class,
                                    () -> {
                                        String[] strings = "".split(" ");
                                        if (strings.length != 2)
                                            throw new Exception("Parsing problem");
                                    });
        assertEquals("Parsing problem", ex.getMessage());
    }

    @Test
    void timeoutOkay() {
        assertTimeout(Duration.ofMillis(100), () -> System.out.println("Hello"));
    }

    @Test
    @Disabled("Disable until demo")
    void timeoutExceeded() {
        assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(200));
    }

    @Test
    @Disabled("Disable until demo")
    void timeoutExceededWithPreemption() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> Thread.sleep(200));
    }


}
