package com.oreilly.jqwik;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import static org.assertj.core.api.Assertions.assertThat;

class StringPropertiesTest {

    @Property
    void concatenationLength(@ForAll String first, @ForAll String second) {
        String concatenated = first + second;
        assertThat(concatenated.length()).isEqualTo(first.length() + second.length());
    }
    
    @Property
    void stringConcatenation(@ForAll String first, @ForAll String second) {
        String concatenated = first + second;
        assertThat(concatenated).startsWith(first).endsWith(second);
    }

    @Property
    void stringReversal(@ForAll String original) {
        String reversed = new StringBuilder(original).reverse().toString();
        String doubleReversed = new StringBuilder(reversed).reverse().toString();
        
        assertThat(doubleReversed).isEqualTo(original);
    }
    
    @Property
    void substringProperties(@ForAll String source, 
                             @ForAll @IntRange(min = 0, max = 10) int begin, 
                             @ForAll @IntRange(min = 0, max = 10) int length) {
        Assume.that(begin < source.length() && begin + length <= source.length());
        
        String substring = source.substring(begin, begin + length);
        
        assertThat(substring.length()).isEqualTo(length);
        assertThat(source).contains(substring);
    }
    
    @Property
    void toUpperAndLowerCase(@ForAll("alpha") String text) {
        String upper = text.toUpperCase();
        String lower = text.toLowerCase();
        
        assertThat(upper.toLowerCase()).isEqualTo(lower);
        assertThat(lower.toUpperCase()).isEqualTo(upper);
    }
    
    @Provide
    Arbitrary<String> alpha() {
        return Arbitraries.strings()
                .withCharRange('a', 'z')
                .ofMinLength(1)
                .ofMaxLength(20);
    }
}