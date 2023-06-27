package com.oreilly.functionalinterfaces;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Demonstrate the use of a ThrowableSupplier in assertions
// Two assertion methods that take a ThrowableSupplier:
//   - assertDoesNotThrow
//   - assertTimeout (and, of course, assertTimeoutPreemptively)
public class ThrowableSuppliersTest {

    private static final String FILE_EXISTS = "src/test/resources/book_data.csv";
    private static final String FILE_DOES_NOT_EXIST = "src/test/resources/does_not_exist.csv";

    // private method to read lines from a file,
    //   declares it throws an IOException
    private static List<String> readLinesFromFile(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }

    // Show that reading the file works
    @Test
    public void testReadFile_FileExists() {
        List<String> lines = assertDoesNotThrow(
                () -> readLinesFromFile(FILE_EXISTS));
        assertEquals(7, lines.size());
    }

    @Test
    public void testReadFile_FileDoesNotExist() {
        // Takes an Executable and returns void,
        //    but since we checked one that exists...
        assertThrows(NoSuchFileException.class,
                () -> readLinesFromFile(FILE_DOES_NOT_EXIST));
    }

    // Show that we can read the data within a timeout period
    @Test
    public void testReadFile_WithinTimeout() {
        List<String> lines = assertTimeout(Duration.ofMillis(100),
                () -> readLinesFromFile(FILE_EXISTS));
        assertEquals(7, lines.size());
    }
}
