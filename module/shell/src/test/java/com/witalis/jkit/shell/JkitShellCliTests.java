package com.witalis.jkit.shell;

import com.witalis.jkit.shell.utils.ShellPromptProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static com.witalis.jkit.shell.command.CourseCommand.MESSAGE;
import static com.witalis.jkit.shell.utils.Constants.GROUP;
import static org.awaitility.Awaitility.await;

@Slf4j
@Tag("shell")
@DisplayName("Test: cli")
@ShellTest(terminalWidth = 120, terminalHeight = 40)
@ComponentScan(basePackageClasses = JkitShellApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JkitShellCliTests {
    private final ShellTestClient client;

    @Autowired
    public JkitShellCliTests(
        final ShellTestClient client
    ) {
        this.client = client;
    }

    @BeforeAll
    public static void initialization() {
        log.info("Application tests [shell/cli]: In Action...");
    }

    @Nested
    @Tag("cli")
    @Tag("session")
    @DisplayName("Test: session")
    class SessionTest {

        @Test
        @Tag("nonInteractive")
        @DisplayName("Test: non interactive session")
        void nonInteractionSession() {
            ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative()
                .run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .isNotNull()
            );
        }

        @Test
        @Tag("interactive")
        @DisplayName("Test: interactive session")
        void interactionSession() {
            ShellTestClient.InteractiveShellSession session = client
                .interactive()
                .run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .isNotNull()
                    .containsText(ShellPromptProvider.PROMPT)
            );
        }
    }

    @Nested
    @Tag("cli")
    @Tag("help")
    @DisplayName("Test: help")
    class HelpTest {

        @Test
        @Tag("nonInteractive")
        @Tag("operation")
        @DisplayName("Test: non interactive help operation")
        void nonInteractionHelpOperation() {
            var session = client.nonInterative("help").run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("AVAILABLE COMMANDS")
                    .containsText(GROUP)
            );
        }

        @Test
        @Tag("nonInteractive")
        @Tag("metadata")
        @DisplayName("Test: non interactive help metadata")
        void nonInteractionHelpMetadata() {
            var session = client.nonInterative("help", "help").run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("NAME")
                    .containsText("SYNOPSIS")
                    .containsText("OPTIONS")
            );
        }

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: interactive help operation")
        void interactionHelpOperation() {
            var session = client.interactive().run();

            session.write(session.writeSequence().text("help").carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("AVAILABLE COMMANDS")
                    .containsText(GROUP)
            );
        }

        @Test
        @Tag("interactive")
        @Tag("metadata")
        @DisplayName("Test: interactive help metadata")
        void interactionHelpMetadata() {
            var session = client.interactive().run();

            session.write(session.writeSequence().text("help help").carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("NAME")
                    .containsText("SYNOPSIS")
                    .containsText("OPTIONS")
            );
        }
    }

    @Nested
    @Tag("cli")
    @Tag("course")
    @DisplayName("Test: course")
    class CourseTest {

        @Test
        @Tag("nonInteractive")
        @Tag("operation")
        @DisplayName("Test: non interactive course operation")
        void nonInteractionCourseOperation() {
            var session = client.nonInterative("course").run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText(MESSAGE);
                }
            );
        }

        @Test
        @Tag("nonInteractive")
        @Tag("metadata")
        @DisplayName("Test: non interactive course metadata")
        void nonInteractionCourseMetadata() {
            var session = client.nonInterative("help", "course").run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("NAME")
                    .containsText("SYNOPSIS")
                    .containsText("OPTIONS")
            );
        }

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: interactive course operation")
        void interactionCourseOperation() {
            var session = client.interactive().run();

            session.write(session.writeSequence().text("course").carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText(MESSAGE);
                }
            );
        }

        @Test
        @Tag("interactive")
        @Tag("metadata")
        @DisplayName("Test: interactive course metadata")
        void interactionCourseMetadata() {
            var session = client.interactive().run();

            session.write(session.writeSequence().text("help course").carriageReturn().build());

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("NAME")
                    .containsText("SYNOPSIS")
                    .containsText("OPTIONS")
            );
        }
    }

    @Nested
    @Tag("cli")
    @Tag("greeting")
    @DisplayName("Test: greeting")
    class GreetingTest {

        @Test
        @Tag("nonInteractive")
        @Tag("operation")
        @DisplayName("Test: non interactive greeting failure")
        void nonInteractionGreetingFailure() {
            final String username = "John Doe";

            var session = client.nonInterative("greeting").run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("Hello, User");
                }
            );
        }

        @Test
        @Tag("nonInteractive")
        @Tag("operation")
        @DisplayName("Test: non interactive greeting success")
        void nonInteractionGreetingSuccess() {
            final String username = "John Doe";

            var session = client.nonInterative("greeting", "--user", username).run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("Hello, " + username);

                }
            );
        }

        @Test
        @Tag("nonInteractive")
        @Tag("metadata")
        @DisplayName("Test: non interactive greeting metadata")
        void nonInteractionGreetingMetadata() {
            var session = client.nonInterative("help", "greeting").run();

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("NAME")
                    .containsText("SYNOPSIS")
                    .containsText("OPTIONS")
            );
        }

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: interactive greeting failure")
        void interactionGreetingFailure() {
            final String username = "John Doe";

            var session = client.interactive().run();

            session.write(session.writeSequence().text("greeting").carriageReturn().build());

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("Hello, User");
                }
            );
        }

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: interactive greeting success")
        void interactionGreetingSuccess() {
            final String username = "John Doe";

            var session = client.interactive().run();

            session.write(session.writeSequence().text("greeting --user '" + username + "'").carriageReturn().build());

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("Hello, " + username);

                }
            );
        }

        @Test
        @Tag("interactive")
        @Tag("metadata")
        @DisplayName("Test: interactive greeting metadata")
        void interactionGreetingMetadata() {
            var session = client.interactive().run();

            session.write(session.writeSequence().text("help greeting").carriageReturn().build());

            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> ShellAssertions.assertThat(session.screen())
                    .containsText("NAME")
                    .containsText("SYNOPSIS")
                    .containsText("OPTIONS")
            );
        }
    }

    @Nested
    @Tag("cli")
    @Tag("numeric")
    @DisplayName("Test: numeric")
    class NumericTest {

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: interactive add operation")
        void interactionAddOperation() {
            final String numbers = "1.2 3.4 5.6";

            var session = client.interactive().run();

            session.write(session.writeSequence().text("add --n " + numbers).carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("10.2");
                }
            );
        }

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: interactive sum operation")
        void interactionSumOperation() {
            final String numbers = "1.2 3.4 5.6 7.8 9.0";

            var session = client.interactive().run();

            session.write(session.writeSequence().text("sum --n " + numbers).carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("27.0");
                }
            );
        }

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: interactive max operation")
        void interactionMaxOperation() {
            final String numbers = "1.2 7.8 5.6 9.0 3.4";

            var session = client.interactive().run();

            session.write(session.writeSequence().text("max --n " + numbers).carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("9.0");
                }
            );
        }
    }

    @Nested
    @Tag("cli")
    @Tag("scenario")
    @DisplayName("Test: scenario")
    class ScenarioTest {

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: activate action")
        void actionEnable() {
            var session = client.interactive().run();

            session.write(session.writeSequence().text("activate").carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .isNotNull();
                }
            );

            session.write(session.writeSequence().text("action").carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .containsText("Download data...");
                }
            );
        }

        @Test
        @Tag("interactive")
        @Tag("operation")
        @DisplayName("Test: deactivate action")
        void actionDisable() {
            var session = client.interactive().run();

            session.write(session.writeSequence().text("deactivate").carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .isNotNull();
                }
            );

            session.write(session.writeSequence().text("action").carriageReturn().build());
            await().atMost(1, TimeUnit.SECONDS).untilAsserted(
                () -> {
                    log.debug(session.screen().lines().toString());
                    ShellAssertions.assertThat(session.screen())
                        .isNotNull();
                }
            );
        }
    }
}
