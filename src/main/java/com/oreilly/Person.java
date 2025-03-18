package com.oreilly;

import java.time.LocalDate;
import java.time.Period;

public record Person(String first, String last, LocalDate dob) {

    public String getName() {
        return String.format("%s %s", first(), last());
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

}
