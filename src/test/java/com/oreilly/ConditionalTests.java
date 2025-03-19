package com.oreilly;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertTrue;

// from: https://junit.org/junit5/docs/current/user-guide/#writing-tests-conditional-execution
public class ConditionalTests {
    @Test
    @EnabledOnOs(OS.MAC)
    void onlyOnMac() {
        assertTrue(true);
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.SOLARIS, OS.WINDOWS})
    void onlyOnLinuxSolarisWindows() {
        // ...
    }

    @TestOnMac
    void testOnMac() {
        assertTrue(true);
    }

    // Demo of custom annotation; better to put in its own file
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(OS.MAC)
    @interface TestOnMac {
    }

    @Test
    @EnabledOnOs(architectures = "aarch64")
    void onAarch64() {
        // ...
    }

    @Test
    @DisabledOnOs(architectures = "x86_64")
    void notOnX86_64() {
        // ...
    }

    @Test
    @EnabledOnOs(value = OS.MAC, architectures = "aarch64")
    void onNewMacs() {
        // ...
    }

    @Test
    @DisabledOnOs(value = OS.MAC, architectures = "aarch64")
    void notOnNewMacs() {
        // ...
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        assertTrue(true);
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11, JRE.JAVA_17, JRE.JAVA_21})
    void okayOnJava8and11and17and21() {
        assertTrue(true);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_21)
    void okayForJREFrom8to21() {
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
        System.getProperties()
                .forEach((prop, value) -> System.out.println(prop + " = " + value));
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

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    @EnabledIfSystemProperty(named = "user.country", matches = ".*US.*")
    void runs_only_on_64bit_architectures_in_US() {
        System.out.println("This test runs only on 64-bit architectures in the US");
    }

    @Test
    @EnabledIf("hasAtLeastFourProcessors")
    void only_runs_if_four_processors_available() {
        System.out.println("This test runs only if there are at least 4 processors");
    }

    private boolean hasAtLeastFourProcessors() {
        return Runtime.getRuntime().availableProcessors() >= 4;
    }

}
