package com.oreilly;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class OrderedTestDemo {

    @Test
    @Order(30)
    void nullValues() {
        // perform assertions against null values
    }

    @Test
    @Order(32)
    void emptyValues() {
        // perform assertions against empty values
    }

    @Test
    @Order(12)
    void validValues() {
        // perform assertions against valid values
    }

}