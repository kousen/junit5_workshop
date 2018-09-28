package com.oreilly;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Person {
    private String first;
    private String last;
    private LocalDate dob;

    public Person(String first, String last, LocalDate dob) {
        this.first = first;
        this.last = last;
        this.dob = dob;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getName() {
        return String.format("%s %s", getFirst(), getLast());
    }

    public LocalDate getDob() {
        return dob;
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(first, person.first) &&
                Objects.equals(last, person.last) &&
                Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, last, dob);
    }
}
