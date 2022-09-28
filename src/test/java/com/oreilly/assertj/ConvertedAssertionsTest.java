package com.oreilly.assertj;

import com.oreilly.Book;
import org.apache.commons.validator.routines.ISBNValidator;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored", "Convert2MethodRef"})
@ExtendWith(SoftAssertionsExtension.class)
public class ConvertedAssertionsTest {
    @Test
    void standardAssertions() {
        assertThat(2).isEqualTo(2);
        assertThat(2 + 2)
                .as("The optional assertion message is now the last parameter.")
                .isEqualTo(4);
        assertThat('a' < 'b')
                .as(() -> "Assertion messages can be lazily evaluated -- "
                        + "to avoid constructing complex messages unnecessarily.")
                .isTrue();
    }

    @Test
    void testWithoutSupplier() {
        assertThat("this is a string").as(getErrorMessage())
                .isEqualTo("this is a string");       // error message method called even if no error
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

    @Test // Uses AssertJ SoftAssertionsExtension to inject arg and execute at end
    void assertAllBook(SoftAssertions softy) {
        Book book = findByIsbn("149197317X");
        softy.assertThat(ISBNValidator.getInstance().isValidISBN10(book.getIsbn())).isTrue();
        softy.assertThat(book.getTitle()).isEqualTo("Modern Java Recipes");
        softy.assertThat(book.getAuthor()).isEqualTo("Ken Kousen");
        softy.assertThat(book.getPublicationDate()).isBefore(LocalDate.now());
    }

    @Test
    void assertAllBookWithDependents() {
        Book book = findByIsbn("149197317X");
        assertAll("MJR",
                // ISBN and author name tests always run
                // Null check on title always runs
                () -> ISBNValidator.getInstance().isValidISBN10(book.getIsbn()),
                () -> {
                    assertThat(book.getTitle()).isNotNull();

                    // The rest of the block skipped if null title
                    String[] name = book.getTitle().split(" ");
                    assertThat(name.length).isEqualTo(3);

                    // Skipped if title has other than three words
                    assertAll("title words",
                            () -> assertThat(name[0]).startsWith("M"),
                            () -> assertThat(name[1]).startsWith("J"),
                            () -> assertThat(name[2]).startsWith("R"));
                },
                () -> assertThat(book.getAuthor()).isEqualTo("Ken Kousen"));
    }

    private void throwException() {
        String[] strings = "".split(" ");
        if (strings.length != 2)
            throw new IllegalArgumentException("Parsing problem");
    }

    // In JUnit 4, this would be @Test(expected=IllegalArgumentException.class)
    @Test
    void exceptionTesting() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> throwException())
                .withMessage("Parsing problem");
    }

    // Junit 4: @Test(expected = IndexOutOfBoundsException.class)
    @Test
    void exceptionWithoutMethodReference() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> strings.get(-1))
                .withMessageContaining("-1");
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
