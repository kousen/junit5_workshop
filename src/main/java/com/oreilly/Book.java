package com.oreilly;

import java.time.LocalDate;

public record Book(String isbn, String title, String author, LocalDate publicationDate) {
}
