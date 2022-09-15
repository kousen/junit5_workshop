package com.oreilly;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
@Tag("model")
class TaggingDemoTest {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

}