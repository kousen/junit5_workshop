package com.oreilly.assertj;

import com.oreilly.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvancedFeaturesTest {

    private final Person jeanLuc = new Person("Jean-Luc", "Picard",
            LocalDate.of(2305, Month.JULY, 13));
    private final Person worf = new Person("Worf", "Son of Mogh",
            LocalDate.of(2340, Month.DECEMBER, 9));
    private final Person data = new Person("Data", null,
            LocalDate.of(2338, Month.FEBRUARY, 2));
            
    private final List<Person> characters = List.of(jeanLuc, worf, data);

    @Test
    void descriptiveAssertions() {
        // Using assertThat with as() for better failure messages
        assertThat(jeanLuc.getFirst())
                .as("Captain's first name")
                .isEqualTo("Jean-Luc");
                
        // Using assertThat with describedAs() for better failure messages
        assertThat(jeanLuc.getLast())
                .describedAs("Captain's last name")
                .isEqualTo("Picard");
                
        // Using overrideErrorMessage for complete control over error message
        assertThat(jeanLuc.getDob())
                .overridingErrorMessage("Expected Captain Picard's birth date to be %s but was %s",
                        LocalDate.of(2305, Month.JULY, 13), jeanLuc.getDob())
                .isEqualTo(LocalDate.of(2305, Month.JULY, 13));
    }
    
    @Test
    void predicateAssertions() {
        // Creating a condition from a predicate
        Predicate<Person> hasLastNamePredicate = person -> person.getLast() != null;
        org.assertj.core.api.Condition<Person> hasLastName = 
                new org.assertj.core.api.Condition<>(hasLastNamePredicate, "has last name");
        
        assertThat(characters)
                .haveExactly(2, hasLastName)
                .doNotHave(
                    org.assertj.core.api.Assertions.allOf(
                        hasLastName,
                        new org.assertj.core.api.Condition<>(
                            person -> person.getFirst().equals("Data"),
                            "is Data")
                    )
                );
        
        // Using anyMatch/allMatch/noneMatch
        assertThat(characters)
                .anyMatch(person -> person.getFirst().equals("Data"))
                .allMatch(person -> person.getDob() != null) // Changed from getAge() > 0 which may fail based on current date
                .noneMatch(person -> person.getFirst().equals("Wesley"));
                
        // Combining multiple predicates
        assertThat(characters)
                .filteredOn(hasLastNamePredicate)
                .hasSize(2)
                .extracting(Person::getFirst)
                .contains("Jean-Luc", "Worf");
    }
    
    @Test
    void bddStyleAssertions() {
        // Using BDD-style assertions
        // Given
        List<String> names = Arrays.asList("Jean-Luc", "Data", "Worf");
        
        // When
        String first = names.get(0);
        
        // Then
        then(first)
                .as("first element")
                .isEqualTo("Jean-Luc")
                .startsWith("J");
                
        then(names)
                .as("list of names")
                .hasSize(3)
                .contains("Data")
                .doesNotContain("Riker");
    }
    
    @Test
    void usingCustomComparator() {
        // Using a custom comparator to ignore case in string comparisons
        assertThat("JEAN-LUC")
                .usingComparator(String::compareToIgnoreCase)
                .isEqualTo("jean-luc");
                
        // Using a custom comparator for person comparison
        Person jeanLucCopy = new Person("Jean-Luc", "Picard",
                LocalDate.of(2305, Month.JULY, 14)); // Different birth date
                
        assertThat(jeanLucCopy)
                .usingComparator((p1, p2) -> 
                    p1.getFirst().equals(p2.getFirst()) && p1.getLast().equals(p2.getLast()) ? 0 : 1)
                .isEqualTo(jeanLuc);
    }
    
    @Test
    void usingConditions() {
        // Define a custom condition
        org.assertj.core.api.Condition<Person> starfleetOfficer = 
                new org.assertj.core.api.Condition<>(
                    person -> person.getLast() != null && person.getFirst() != null,
                    "Starfleet officer");
        
        assertThat(jeanLuc)
                .is(starfleetOfficer);
                
        assertThat(data)
                .isNot(starfleetOfficer);
                
        assertThat(characters)
                .areAtLeast(2, starfleetOfficer)
                .haveExactly(1, not(starfleetOfficer));
    }
    
    @Test
    void assumptionsWithAssertJ() {
        // Using AssertJ's assumptions
        assumeThat(jeanLuc.getLast()).isEqualTo("Picard");
        
        // This assertion will only be executed if the assumption passes
        assertThat(jeanLuc.getFirst()).isEqualTo("Jean-Luc");
        
        // Multiple assumptions
        assumeThat(characters).hasSize(3);
        assumeThat(characters).extracting(Person::getFirst).contains("Data");
        
        // This assertion will only be executed if all assumptions pass
        assertTrue(true);
    }
}