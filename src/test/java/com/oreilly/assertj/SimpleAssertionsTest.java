package com.oreilly.assertj;

import com.oreilly.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleAssertionsTest {
    @Test
    void a_few_simple_assertions() {
        String title = "The Lord of the Rings";
        assertThat(title)
                .isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith(" Rings");
    }

    @Test
    void personAssertions() {
        Person jeanLuc = new Person("Jean-Luc", "Picard",
                LocalDate.of(2305, Month.JULY, 13));

        assertThat(jeanLuc)
                .extracting(Person::first, Person::last)
                .containsExactly("Jean-Luc", "Picard");

        assertThat(jeanLuc)
                .extracting(Person::first, Person::last, Person::dob)
                .containsExactly("Jean-Luc", "Picard", LocalDate.of(2305, Month.JULY, 13));
    }

}
