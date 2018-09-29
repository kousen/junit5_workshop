package com.oreilly;

import org.apache.commons.validator.routines.ISBNValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemo {
    @Test
    void standardAssertions() {
        assertEquals(2, 2);
        assertEquals(4, 2 + 2, "The optional assertion message is now the last parameter.");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void testWithoutSupplier() {
        assertEquals("this is a string",  // expected
                     getCompleteString(), // test method
                     getErrorMessage());  // error message method called even if no error
    }

    @Test
    void testWithSupplier() {
        assertEquals("this is a string",  // expected
                     getCompleteString(), // test method
                     () -> "This should never happen");  // error message supplier
    }

    @Test
    void testWithSupplierMethod() {
        assertEquals("this is a string",  // expected
                     getCompleteString(), // test method
                     () -> getErrorMessage());  // error message supplier NOT CALLED if no error
    }

    private String getCompleteString() {
        System.out.println("Inside the getCompleteString method");
        return "this" + " " + "is" +
                " " + "a" + " " + "string";
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "This should never happen";
    }

    @Test
    void assertAllBook() {
        Book book = new Book("149197317X", "Modern Java Recipes", "Ken Kousen", LocalDate.parse("2017-08-26"));
        assertAll("MJR",
                  () -> assertTrue(ISBNValidator.getInstance().isValidISBN10(book.getIsbn())),
                  () -> assertEquals("Modern Java Recipes", book.getTitle()),
                  () -> assertEquals("Ken Kousen", book.getAuthor()));
    }

    @Test
    void assertAllBookWithDependents() {
        Book book = new Book("149197317X", "Modern Java Recipes", "Ken Kousen", LocalDate.parse("2017-08-26"));
        assertAll("MJR",
                  // ISBN and author name tests always run
                  // Null check on title always runs
                  () -> ISBNValidator.getInstance().isValidISBN10(book.getIsbn()),
                  () -> {
                      assertNotNull(book.getTitle());

                      // The rest of the block skipped if null title
                      String[] name = book.getTitle().split(" ");
                      assertEquals(3, name.length);
                      assertAll("title words",
                                () -> assertTrue(name[0].startsWith("M")),
                                () -> assertTrue(name[1].startsWith("J")),
                                () -> assertTrue(name[2].startsWith("R")));
                  },
                  () -> assertEquals("Ken Kousen", book.getAuthor()));
    }

    private void throwException() {
        String[] strings = "".split(" ");
        if (strings.length != 2)
            throw new IllegalArgumentException("Parsing problem");
    }

    @Test
        // In JUnit 4, this would be @Test(expected=IllegalArgumentException.class)
    void exceptionTesting() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                                    this::throwException);
        assertEquals("Parsing problem", ex.getMessage());
    }

    @Test
    void exceptionWithoutMethodReference() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        ArrayIndexOutOfBoundsException ex = assertThrows(ArrayIndexOutOfBoundsException.class,
                                                         () -> strings.get(99));
        assertThat(ex.getMessage(), containsString("99"));
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
