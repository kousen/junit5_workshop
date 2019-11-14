package com.oreilly;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ConditionalTests {
    @Test
    @EnabledOnOs(OS.MAC)
    void onlyOnMac() {
        assertTrue(true);
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.SOLARIS})
    void onlyOnLinuxOrSolaris() {
        assertNotNull("abc");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        assertTrue(true);
    }

    @Test
    @EnabledOnJre({JRE.JAVA_10, JRE.JAVA_11})
    void okayOnJava10and11() {
        assertTrue(true);
    }

    @Test
    @EnabledIfSystemProperty(named = "ciserver", matches = "true")
    void onCiServer() {
        // ...
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void onlyOnStagingServer() {
        // ...
    }
}
