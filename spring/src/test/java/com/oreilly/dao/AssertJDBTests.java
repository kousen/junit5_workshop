package com.oreilly.dao;

import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;

@SpringBootTest
public class AssertJDBTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void checkLastNameColumnValues() {
        Table table = new Table(dataSource, "officers");
        assertThat(table).column("last_name")
                .value().isEqualTo("Kirk")
                .value().isEqualTo("Picard")
                .value().isEqualTo("Sisko")
                .value().isEqualTo("Janeway")
                .value().isEqualTo("Archer");
    }

    @Test
    void checkLastNames() {
        Table table = new Table(dataSource, "officers");
        assertThat(table).column("last_name")
                .hasValues("Kirk", "Picard", "Sisko", "Janeway", "Archer");
    }

    @Test
    void checkFirstTwoRows() {
        Table table = new Table(dataSource, "officers");
        assertThat(table).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("CAPTAIN")
                .value().isEqualTo("James")
                .value().isEqualTo("Kirk");
        assertThat(table).row(1)
                .value().isEqualTo(2)
                .value().isEqualTo("CAPTAIN")
                .value().isEqualTo("Jean-Luc")
                .value().isEqualTo("Picard");
    }

    @Test
    void tableSizeAssertions() {
        Table table = new Table(dataSource, "officers");
        assertThat(table).hasNumberOfColumns(4);
        assertThat(table).hasNumberOfRows(5);
    }
}
