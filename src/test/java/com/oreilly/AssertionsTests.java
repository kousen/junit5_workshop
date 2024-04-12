package com.oreilly;

import org.apache.commons.validator.routines.ISBNValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"ConstantConditions", "Convert2MethodRef", "divzero"})
public class AssertionsTests {
    @Test
    void standardAssertions() {
        assertEquals(4, 2 + 2,
                "The optional error message is now the last parameter.");
    }

    private String getString() {
        return "this is a string";
    }

    @Test
    void testWithoutSupplier() {
        assertEquals("this is a string",  // expected
                getString(),         // method to test
                getErrorMessage());  // error message method called even if no error
    }

    @Test
    void testWithSupplierMethod() {
        assertEquals("this is a string",   // expected
                getString(),               // method to test
                () -> getErrorMessage());  // error message supplier NOT CALLED if no error
    }

    @Test
    void testWithSupplierMethodReference() {
        assertEquals("this is a string",  // expected
                getString(),             // method to test
                this::getErrorMessage);  // error message supplier NOT CALLED if no error
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "This should never happen";
    }

    @SuppressWarnings("SameParameterValue")
    private Book findByIsbn(String isbn) {
        if (isbn.equals("149197317X")) return
                new Book("149197317X", "Modern Java Recipes",
                        "Ken Kousen", LocalDate.parse("2017-08-26"));
        else return null;
    }

    @Test
    void assertAllBook() {
        Book book = findByIsbn("149197317X");
        // assertAll(String header, Executable...)
        assertAll("MJR",
                () -> assertTrue(ISBNValidator.getInstance().isValidISBN10(book.isbn())),
                () -> assertEquals("Modern Java Recipes", book.title()),
                () -> assertEquals("Ken Kousen", book.author()),
                () -> assertTrue(book.publicationDate().isBefore(LocalDate.now())));
    }

    @Test
    void assertAllBookWithDependents() {
        Book book = findByIsbn("149197317X");
        assertAll("MJR",
                // ISBN and author name tests always run
                // Null check on title always runs
                () -> ISBNValidator.getInstance().isValidISBN10(book.isbn()),
                () -> {
                    assertNotNull(book.title());
                    // The rest of the block skipped if null title

                    String[] name = book.title().split(" ");
                    assertEquals(3, name.length);
                    // Skipped if title has other than three words

                    assertAll("title words",
                            () -> assertTrue(name[0].startsWith("M")),
                            () -> assertTrue(name[1].startsWith("J")),
                            () -> assertTrue(name[2].startsWith("R")));
                },
                () -> assertEquals("Ken Kousen", book.author()));
    }

    private void throwException() {
        String[] strings = "".split(" ");
        if (strings.length != 2)
            throw new IllegalArgumentException("Parsing problem");
    }

    // In JUnit 4, this would be @Test(expected=IllegalArgumentException.class)
    @Test
    void exceptionTesting() {
        Exception ex = assertThrows(Exception.class, () -> throwException());
        Exception exc = assertThrowsExactly(IllegalArgumentException.class, () -> throwException());
        assertEquals("Parsing problem", ex.getMessage());
        assertEquals("Parsing problem", exc.getMessage());
    }

    // Junit 4: @Test(expected = IndexOutOfBoundsException.class)
    @Test
    void exceptionWithoutMethodReference() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        IndexOutOfBoundsException ex =
                assertThrows(IndexOutOfBoundsException.class, () -> strings.get(-1));
        // assertThat(ex.getMessage(), containsString("-1"));
        assertTrue(ex.getMessage().contains("-1"));
    }


    // Binary numbers
    // To the left of the decimal 1's, 2's, 4's, 8's, 16's, ...
    //    So 1010 is 10 in decimal
    // To the right of the decimal 1/2, 1/4, 1/8, 1/16, ...
    //    So 0.101 is 1/2 + 1/8 = 5/8 in decimal
    //    0.1 in decimal is a repeating decimal in binary --> can't get exact precision
    // So you can't represent floating point numbers exactly in binary
    // IEEE 754 standard for floating point numbers --> mantissa, exponent, ...

    @Test @DisplayName("Arithmetic exception: / by zero")
    void arithmeticExceptionWithInts() {
        int x = 1;
        int y = 0;
        Exception exception = assertThrows(
                ArithmeticException.class,
                () -> System.out.println(x / y));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test @DisplayName("IEEE 754 spec: Floating point / by zero results in INFINITY")
    void noArithmeticExceptionWithDoubles() {
        double x = 1.0;
        double y = 0.0;
        Double value = assertDoesNotThrow(() -> x / y);// arg is a ThrowingSupplier<Double>
        System.out.println(value);
        assertDoesNotThrow(
                () -> assertEquals(Double.POSITIVE_INFINITY, x / y));
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
