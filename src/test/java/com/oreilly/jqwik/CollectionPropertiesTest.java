package com.oreilly.jqwik;

import com.oreilly.Person;
import net.jqwik.api.*;
import net.jqwik.api.Tuple.Tuple2;
import net.jqwik.api.arbitraries.ListArbitrary;
import net.jqwik.api.constraints.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionPropertiesTest {

    @Property
    void listsCanBeConcatenated(@ForAll List<@AlphaChars String> list1, 
                                @ForAll List<@AlphaChars String> list2) {
        List<String> concatenated = new ArrayList<>(list1);
        concatenated.addAll(list2);
        
        assertThat(concatenated.size()).isEqualTo(list1.size() + list2.size());
        
        for (String element : list1) {
            assertThat(concatenated).contains(element);
        }
        
        for (String element : list2) {
            assertThat(concatenated).contains(element);
        }
    }
    
    @Property
    void reversedListsContainSameElements(@ForAll("nonEmptyAlphaLists") List<String> original) {
        List<String> reversed = new ArrayList<>(original);
        java.util.Collections.reverse(reversed);
        
        assertThat(reversed).containsExactlyInAnyOrderElementsOf(original);
        
        if (original.size() > 1) {
            // First and last elements are swapped in reversed list
            assertThat(reversed.get(0)).isEqualTo(original.get(original.size() - 1));
            assertThat(reversed.get(reversed.size() - 1)).isEqualTo(original.get(0));
        }
    }
    
    @Provide
    ListArbitrary<String> nonEmptyAlphaLists() {
        return Arbitraries.strings().alpha().list().ofMinSize(1);
    }
    
    @Property
    void distinctElementsInCombinedList(@ForAll List<Integer> list) {
        List<Integer> distinctList = list.stream().distinct().toList();
        
        // Distinct elements count should be less than or equal to original size
        assertThat(distinctList.size()).isLessThanOrEqualTo(list.size());
        
        // All distinct elements must exist in original list
        for (Integer element : distinctList) {
            assertThat(list).contains(element);
        }
    }
    
    @Property
    void sortedListsKeepElements(@ForAll List<Integer> original) {
        List<Integer> sorted = new ArrayList<>(original);
        java.util.Collections.sort(sorted);
        
        assertThat(sorted).containsExactlyInAnyOrderElementsOf(original);
        
        // Check if sorted list is actually sorted
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertThat(sorted.get(i)).isLessThanOrEqualTo(sorted.get(i + 1));
        }
    }
    
    @Property
    void personCreation(@ForAll("firstNames") String firstName,
                        @ForAll("lastNames") String lastName,
                        @ForAll("dates") LocalDate birthDate) {
        
        Person person = new Person(firstName, lastName, birthDate);
        
        assertThat(person.getFirst()).isEqualTo(firstName);
        assertThat(person.getLast()).isEqualTo(lastName);
        assertThat(person.getDob()).isEqualTo(birthDate);
        assertThat(person.getName()).isEqualTo(firstName + " " + lastName);
    }
    
    @Provide
    Arbitrary<String> firstNames() {
        return Arbitraries.of("Jean-Luc", "James", "Benjamin", "Kathryn", "Jonathan", "Beverly", "William");
    }
    
    @Provide
    Arbitrary<String> lastNames() {
        return Arbitraries.of("Picard", "Kirk", "Sisko", "Janeway", "Archer", "Crusher", "Riker");
    }
    
    @Provide
    Arbitrary<LocalDate> dates() {
        return Arbitraries.integers().between(2300, 2400)
                .map(year -> LocalDate.of(
                        year,
                        Arbitraries.integers().between(1, 12).sample(),
                        Arbitraries.integers().between(1, 28).sample()
                ));
    }
}