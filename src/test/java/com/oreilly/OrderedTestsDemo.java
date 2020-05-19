package com.oreilly;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// From User manual: https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-execution-order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTestsDemo {

    @Test
    @Order(1)
    void nullValues() {
        // perform assertions against null values
        System.out.println("Ran nullValues test");
    }

    @Test
    @Order(3)
    void validValues() {
        // perform assertions against valid values
        System.out.println("Ran validValues test");
    }

    @Test
    @Order(2)
    void emptyValues() {
        // perform assertions against empty values
        System.out.println("Ran emptyValues test");
    }


}