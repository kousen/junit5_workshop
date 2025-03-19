package com.oreilly.assertj;

import com.oreilly.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class CollectionAssertionsTest {

    private final List<String> starTrekCharacters = 
            List.of("Picard", "Data", "Worf", "La Forge", "Riker", "Crusher");
    
    private final List<Book> books = List.of(
            new Book("9780321356680", "Effective Java", "Joshua Bloch", 
                    LocalDate.of(2008, Month.MAY, 8)),
            new Book("9781617292545", "Modern Java Recipes", "Ken Kousen", 
                    LocalDate.of(2017, Month.AUGUST, 26)),
            new Book("9780134685991", "Effective Java", "Joshua Bloch", 
                    LocalDate.of(2018, Month.JANUARY, 6))
    );

    @Test
    void listAssertions() {
        assertThat(starTrekCharacters)
                .hasSize(6)
                .contains("Data", "Worf")
                .containsExactly("Picard", "Data", "Worf", "La Forge", "Riker", "Crusher")
                .containsExactlyInAnyOrder("Data", "Worf", "La Forge", "Riker", "Crusher", "Picard");
    }

    @Test
    void listElementAssertions() {
        assertThat(starTrekCharacters)
                .allSatisfy(name -> assertThat(name).isNotEmpty())
                .anySatisfy(name -> assertThat(name).contains("a"))
                .filteredOn(name -> name.contains("a")).hasSize(3)
                .containsExactly("Picard", "Data", "La Forge");
    }

    @Test
    void bookFilteringAndMapping() {
        // Filtering
        assertThat(books)
                .filteredOn(book -> book.author().equals("Joshua Bloch"))
                .hasSize(2)
                .extracting(Book::title)
                .containsExactly("Effective Java", "Effective Java");

        // Filtering with multiple conditions
        assertThat(books)
                .filteredOn(book -> book.author().equals("Joshua Bloch")
                    && book.publicationDate().isAfter(LocalDate.of(2017, 1, 1)))
                .hasSize(1)
                .extracting(Book::isbn)
                .containsExactly("9780134685991");

        // Property-based filtering
        assertThat(books)
                .filteredOn("author", "Ken Kousen")
                .hasSize(1)
                .extracting(Book::title)
                .containsExactly("Modern Java Recipes");
    }

    @Test
    void complexExtractionAndComparison() {
        // Multiple property extraction and comparison
        assertThat(books)
                .extracting(Book::title, Book::author)
                .contains(
                    Assertions.tuple("Effective Java", "Joshua Bloch"),
                    Assertions.tuple("Modern Java Recipes", "Ken Kousen")
                );

        // Comparing by author and title
        Book effectiveJava2nd = books.get(0);
        Book effectiveJava3rd = books.get(2);

        assertThat(effectiveJava3rd)
                .usingRecursiveComparison()
                .ignoringFields("isbn", "publicationDate")
                .isEqualTo(effectiveJava2nd);
                
        // Sort by publication date
        assertThat(books)
                .isSortedAccordingTo(Comparator.comparing(Book::publicationDate));
    }

    @Test
    void mapAssertions() {
        Map<String, Book> booksByIsbn = books.stream()
                .collect(toMap(Book::isbn, book -> book));

        assertThat(booksByIsbn)
                .hasSize(3)
                .containsKeys("9780321356680", "9781617292545", "9780134685991")
                .containsValues(books.get(0), books.get(1), books.get(2))
                .contains(entry("9781617292545", books.get(1)))
                .doesNotContainKey("1234567890")
                .extractingByKey("9780134685991")
                .extracting(Book::title)
                .isEqualTo("Effective Java");
    }
}