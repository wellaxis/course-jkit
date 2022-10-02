package com.witalis.jkit.shell;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.Shell;
import org.springframework.shell.result.DefaultResultHandler;

import javax.validation.ConstraintViolation;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Tag("shell")
@DisplayName("Test: cli")
@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class JkitShellCliTests {
    private final Shell shell;
    private final DefaultResultHandler handler;

    @Autowired
    public JkitShellCliTests(
        final Shell shell,
        final DefaultResultHandler handler
    ) {
        this.shell = shell;
        this.handler = handler;
    }

    @BeforeAll
    public static void initialization() {
        log.info("Application tests [shell/cli]: In Action...");
    }

    @Nested
    @Tag("cli")
    @Tag("greeting")
    @DisplayName("Test: greeting")
    class GreetingTest {

        @Test
        @DisplayName("Test: success")
        void greetingSuccess() {
            final String username = "User";

            var result = shell.evaluate(() -> "greeting " + username);

            handler.handleResult(result);

            assertNotNull(result);
            assertEquals("Hello, " + username, result);
        }

        @Test
        @DisplayName("Test: failure")
        void greetingFailure() {
            final String username = "Me";

            var result = shell.evaluate(() -> "greeting " + username);

            handler.handleResult(result);

            assertNotNull(result);
            assertTrue(result instanceof ParameterValidationException);
            assertEquals(1, ((ParameterValidationException) result).getConstraintViolations().size());

            ConstraintViolation<?> constraint = ((ParameterValidationException) result).getConstraintViolations().iterator().next();
            assertEquals("size must be between 3 and 40", constraint.getMessage());
        }
    }

    @Nested
    @Tag("cli")
    @Tag("numeric")
    @DisplayName("Test: numeric")
    class NumbericTest {

        @Test
        @DisplayName("Test: sum")
        void sumOfElements() {
            final String numbers = "1.2 3.4 5.6";

            var result = shell.evaluate(() -> "sum --numbers " + numbers);

            handler.handleResult(result);

            assertNotNull(result);
            assertEquals(10.2D, result);
        }

        @Test
        @DisplayName("Test: max")
        void maxElement() {
            final String numbers = "1.2 7.8 5.6 9.0 3.4";

            var result = shell.evaluate(() -> "max --numbers " + numbers);

            handler.handleResult(result);

            assertNotNull(result);
            assertEquals(9.0D, result);
        }
    }

    @Nested
    @Tag("cli")
    @Tag("scenario")
    @DisplayName("Test: scenario")
    class ScenarioTest {

        @Test
        @DisplayName("Test: activate action")
        void actionEnable() {
            final String action = "action";

            shell.evaluate(() -> "activate");
            var result = shell.evaluate(() -> action);

            handler.handleResult(result);

            assertNotNull(result);
            assertEquals("Download...", result);
        }

        @Test
        @DisplayName("Test: deactivate action")
        void actionDisable() {
            final String message = "Command 'action' exists but is not currently available because the access to scenario is switched off. Please, enable scenario!";
            final String action = "action";

            shell.evaluate(() -> "deactivate");
            var result = shell.evaluate(() -> action);

            handler.handleResult(result);

            assertNotNull(result);
            assertTrue(result instanceof CommandNotCurrentlyAvailable);
            assertEquals(message, ((CommandNotCurrentlyAvailable) result).getMessage());
        }
    }
}
