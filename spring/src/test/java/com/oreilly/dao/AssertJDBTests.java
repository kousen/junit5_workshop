package com.oreilly.dao;

import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.AssertDbConnectionFactory;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.db.api.Assertions.assertThat;

@SpringBootTest
public class AssertJDBTests {
    private Table table;

    @BeforeEach
    void setUp() {
        AssertDbConnection connection = AssertDbConnectionFactory.of(
                        "jdbc:h2:mem:testdb", "sa", "sa")
                .create();
        table = connection.table("officers")
                .build();
    }

    @Test
    void checkLastNameColumnValues() {
        assertThat(table).column("last_name")
                .value()
                .isEqualTo("Kirk")
                .value()
                .isEqualTo("Picard")
                .value()
                .isEqualTo("Sisko")
                .value()
                .isEqualTo("Janeway")
                .value()
                .isEqualTo("Archer");
    }

    @Test
    void checkLastNames() {
        assertThat(table).column("last_name")
                .hasValues("Kirk", "Picard", "Sisko", "Janeway", "Archer");
    }

    @Test
    void checkFirstTwoRows() {
        assertThat(table).row(0)
                .value()
                .isEqualTo(1)
                .value()
                .isEqualTo("CAPTAIN")
                .value()
                .isEqualTo("James")
                .value()
                .isEqualTo("Kirk");
        assertThat(table).row(1)
                .value()
                .isEqualTo(2)
                .value()
                .isEqualTo("CAPTAIN")
                .value()
                .isEqualTo("Jean-Luc")
                .value()
                .isEqualTo("Picard");
    }

    @Test
    void tableSizeAssertions() {
        assertThat(table).hasNumberOfColumns(4);
        assertThat(table).hasNumberOfRows(5);
    }
}
