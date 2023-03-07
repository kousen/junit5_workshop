package com.oreilly.assertj;

import com.oreilly.Person;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleAssertionsTest {
    @Test
    void a_few_simple_assertions() {
        assertThat("The Lord of the Rings")
                .isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith(" Rings");
    }

    @Test
    void personAssertions() {
        Person jeanLuc = new Person("Jean-Luc", "Picard",
                LocalDate.of(2305, Month.JULY, 13));

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(jeanLuc.getLast()).isEqualToIgnoringCase("picard");
            softAssertions.assertThat(jeanLuc.getLast())
                    .as("check %s's last name", jeanLuc.getFirst())
                    .isEqualTo("Picard");
            softAssertions.assertThat(jeanLuc.getDob()).isAfter(LocalDate.now());
            softAssertions.assertThat(jeanLuc.getDob()).isAfterOrEqualTo("2305-07-13");
        });
    }

}
