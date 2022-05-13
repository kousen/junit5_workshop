package com.oreilly;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConditionalTests {
    @Test
    @EnabledOnOs(OS.MAC)
    void onlyOnMac() {
        assertTrue(true);
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.SOLARIS, OS.WINDOWS})
    void onlyOnLinuxSolarisWindows() {
        assertNotNull("abc");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        assertTrue(true);
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
    void okayOnJava8and11() {
        assertTrue(true);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_17)
    void okayForJREFrom8to17() {
    }

    @Test
    //@EnabledForJreRange(max = JRE.JAVA_17)
    @EnabledForJreRange(min = JRE.JAVA_9)
    void jre9andAbove() {
    }

    @Test
    @EnabledIfSystemProperty(named = "ciserver", matches = "true")
    void onCiServer() {
        // ...
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
        // ...
    }

    @Test
    void showSystemProperties() {
        System.getProperties().forEach((prop, value) -> System.out.println(prop + " = " + value));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void onlyOnStagingServer() {
        // ...
    }

    @Test
    @EnabledIf("customCondition")  // new in JUnit 5.7
    void enabled() {
        // ...
    }

    @Test
    @DisabledIf("customCondition")
    void disabled() {
        // ...
    }

    // method takes no arguments and returns a boolean
    boolean customCondition() {
        return true;
    }
}
