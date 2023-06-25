package com.witalis.jkit.usage.core.invoke.section.statements;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.statements.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Desc: Statements
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
@SuppressWarnings({"preview", "deprecation", "unchecked"})
public class StatementInvoker extends Invoker {

    public StatementInvoker() {
        setTitle("Statements chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // conditional
        log.info("## Conditional statements");
        invokeConditional();
        // tab
        log.info("");
        // unconditional
        log.info("## Unconditional statements");
        invokeUnconditional();
    }

    /**
     * Basic postulates of control statements.
     */
    private void invokeBasis() {
        // Control flow statements - change the flow of execution
        // and provide better control on the flow of execution in the program.

        log.info("There are three main types of flow of execution: sequential, conditional & repetition (loop).");
    }

    /**
     * Conditional statements.
     */
    private void invokeConditional() {
        log.info("Conditional statements: if-else, do-while, while loop, for loop, for-each loop, switch");

        // if-else
        log.info("-- If Else");
        invokeIfElse();
        // tab
        log.info("");
        // loops
        log.info("-- Loops");
        invokeLoops();
        // tab
        log.info("");
        // switch
        log.info("-- Switch");
        invokeSwitch();
    }

    /**
     * If..else statement.
     */
    private void invokeIfElse() {
        // if statement - the simplest decision-making statement
        // that allows to specify alternative paths of execution in a program.

        // Selection statement types:
        // * One way if statements
        // * Two-way if-else statements
        // * Nested if statements
        // * Multi-way if-else statements

        int a = ThreadLocalRandom.current().nextInt(-10, 11);

        // one way > executes a statement if and only if the specified condition is true
        if (a > 5) log.info("* If statement [one way]: {} > 5", a);

        // two-way > decides the execution path based on whether the condition is true or false
        if (a > 0) {
            log.info("* If statement [two-way]: positive value {}", a);
        } else {
            log.info("* If statement [two-way]: negative value {}", a);
        }

        // nested > when an 'if statement' is declared inside another if, or if-else statement
        if (a < 0) {
            log.info("* If statement [nested]: value is negative {}", a);
        } else {
            if ((a & 1) == 0) {
                log.info("* If statement [nested]: value is even {}", a);
            } else {
                log.info("* If statement [nested]: value is odd {}", a);
            }
        }

        // multi-way > as ladder, is used to decide among three or more actions
        if (a < 0) {
            log.info("* If statement [multi-way]: negative {}", a);
        } else if (a == 0) {
            log.info("* If statement [multi-way]: is zero {}", a);
        } else if (a < 5) {
            log.info("* If statement [multi-way]: is small {}", a);
        } else {
            log.info("* If statement [multi-way]: is big {}", a);
        }
    }

    /**
     * Loop statements.
     */
    private void invokeLoops() {
        // Loops - the processes that execute a block of statements repeatedly until a termination condition is met.
        // The block of statements can be executed any number of times: from zero to infinite number.
        // Each execution of the loop is called iteration.

        log.info("There are generally three types of loops: simple, nested, infinite.");

        // while loop
        {
            log.info("");
            // while loop is the simplest of all the looping structures.
            log.info("- The 'while loop' repeats the body of the loop as long as the loop condition holds.");

            // simple iteration
            int i = 1;
            int j = i + 10;
            while (i < 5) {
                log.info("* While loop [iteration]: i = {}, j = {}", i, j);
                j = ++i * 2;
            }

            // without body
            int x = 10, y = 20;
            log.info("* While loop [empty]: values {} & {}", x, y);

            while (++x < --y);
            log.info("* While loop [empty]: mid value is {}", x);
        }

        // do-while
        {
            log.info("");
            // do-while loop test conditional expression is at the bottom of the loop.
            log.info("- The 'do-while loop' always executes its body at least once before the test evaluates.");

            // calculate sum
            int i = 1;
            int sum = 0;
            do {
                sum = sum + i;
                i = i + 2;
            } while (sum < 40 || i < 10);
            log.info("* Do-while loop [sum]: sum is {}", sum);

            // task execution
            long time = System.currentTimeMillis();
            log.info("* Do-while loop [task]: in -> {}", time);
            do {
                try {
                    TimeUnit.MILLISECONDS.sleep(300L);
                } catch (InterruptedException e) {
                    log.error("Interrupted exception has been occurred: {}", e.getMessage());
                }
                log.info("* Do-while loop [task]: {}", System.currentTimeMillis() - time);
            } while (System.currentTimeMillis() - time <= 1000L);
            log.info("* Do-while loop [task]: out -> {}", System.currentTimeMillis());
            log.info("* Do-while loop [task]: diff -> {}s", (System.currentTimeMillis() - time) / 1000L);
        }

        // for loop
        {
            log.info("");
            // this structure consists of initialization, test condition, and iteration (increment or decrement).
            log.info("- The 'for loop' is an entry-controlled loop structure that executes a fixed number of times.");

            // short overflow
            for (int j = 0; j >= 0; j = ((short) (j + 1))) {
                if (j % 10000 != 0) continue;
                log.info("* For loop [overflow]: item {}", j);
            }

            // chars formatting
            char[] c = "An African Swallow".toCharArray();
            StringBuilder sb = new StringBuilder("-");
            for (int l = 0; l < c.length; ++l) {
                char s = c[l];
                sb.append(s).append("-");
            }
            log.info("* For loop [format]: transformation {}", sb);
        }

        // for-each loop
        {
            log.info("");
            // A for each loop (enhanced for loop) is an extended feature that was introduced with the J2SE 5.0 release.
            log.info("- The 'enhanced for loop' repeatedly executes statements for each element of the collection.");

            // array
            int[] numbers = {10, 11, 12, 13, 14, 15};
            for (int number: numbers) {
                log.info("* For-each loop [square]: {}", number * number);
            }

            // collection
            List<String> cities = new ArrayList<>(List.of("Kyiv", "Kharkiv", "Odesa", "Lviv"));
            for (String city: cities) {
                log.info("* For-each loop [city]: {}", city.toUpperCase());
            }
        }

        // nested loop
        {
            log.info("");
            // A for loop nested inside another for loop is called nested for loops.
            log.info("- The 'nested loop' consists of an outer for loop and one or more inner for loops.");

            // outer loop
            for (int i = 1; i <= 5; i++) {
                var builder = new StringBuilder();
                int counter = 1;
                // inner loop
                while (counter++ <= i) {
                    builder.append("*").append(" ");
                }
                log.info(builder.toString());
            }
            log.info("* Nested loop [pyramid]: delimiter is {}", '*');
        }

        // labelled loop
        {
            log.info("");
            // A label is a valid variable name that represents the name of the loop
            // to where the control of execution should jump.
            log.info("- The 'labelled loop' is the loop with a valid variable name to jump outside the statement.");

            log.info("* Labelled loop [sample]: BEGIN");

            int i = 0;
            log.info("|> outer do-while loop");
            outer: do {
                log.info("|> inner do-while loop");
                inner: do {
                    log.info("|> i = {}", ++i);
                    if (i == 1) {
                        log.info("|> continue nested");
                        continue;
                    }
                    if (i == 2) {
                        log.info("|> continue inner");
                        continue inner;
                    }
                    if (i == 3) {
                        log.info("|> continue outer");
                        continue outer;
                    }
                    if (i == 5) {
                        log.info("|> break nested");
                        break;
                    }
                    if (i == 6) {
                        log.info("|> break inner");
                        break inner;
                    }
                    if (i == 7) {
                        log.info("|> break outer");
                        break outer;
                    }
                } while (true);
                log.info("|> exit inner");
                break outer;
            } while (true);
            log.info("|> exit outer");

            log.info("* Labelled loop [sample]: END");
        }

        // infinite loop
        {
            log.info("");
            // An infinite loop is an instruction sequence that loops endlessly when a terminating condition isn't met.
            log.info("- The 'infinite loop', there is no test condition in the loop that tells where to stop.");

            log.info("* Infinite [while loop]: while (true) { x++; }");
            log.info("* Infinite [for loop]: for (;;) { x++; }");
        }
    }

    /**
     * Switch statements.
     */
    private void invokeSwitch() {
        // Switch statement is an alternative to if-else-if ladder statement.

        log.info("Switch statement - a multiway decision statement that executes one statement from multiple conditions.");

        // simple case
        {
            char mode = 'd';
            switch (mode) {
                case 'a': {
                    log.info("* Symbol is 'a'.");
                    break;
                }
                case 'b', 'c': {
                    log.info("* Symbol is 'b' or 'c'.");
                    break;
                }
                case 'd': {
                    log.info("* Switch [simple]: symbol is '{}'.", mode);
                    break;
                }
                default: {
                    log.info("* Symbol is any.");
                }
            }
        }

        // enums case
        {
            enum Mode {ON, OFF, ALL, ANY, NONE}
            Mode mode = Mode.ANY;

            switch (mode) {
                case ON -> log.info("* Switch [enum]: On");
                case OFF -> log.info("* Switch [enum]: Off");
                case ALL -> log.info("* Switch [enum]: All");
                case ANY -> log.info("* Switch [enum]: Any");
                case NONE -> log.info("* Switch [enum]: None");
            }
        }

        // nested
        {
            int i = 10;
            int j = 20;
            // outer
            switch (i) {
                case 10: {
                    // inner
                    switch (j) {
                        case 20: {
                            log.info("* Switch [nested]: i * j = {}", i * j);
                        }
                    }
                }
            }
        }

        // yield
        {
            int a = 10;
            int b = 20;
            String info = switch (a) {
                case 10 -> {
                    if (b >= 0 ) {
                        yield "a+b";
                    } else {
                        yield "a-b";
                    }
                }
                default -> "a-b";
            };
            log.info("* Switch [yield]: {}", info);
        }

        // return
        {
            int a = 10;
            String language = switch (a) {
                case 0 -> "English";
                case 1 -> "German";
                case 10 -> "Ukrainian";
                default -> "Polish";
            };
            log.info("* Switch [return]: {}", language);
        }

        // pattern matching - need to enable preview
        {
            final var random = ThreadLocalRandom.current();

            int type = random.nextInt(1, 10);
            Object object = switch (type) {
                case 1 -> 10;
                case 2 -> -10L;
                case 3 -> 10F;
                case 4 -> -10D;
                case 5 -> "Ok";
                case 6 -> true;
                case 7 -> Instant.now();
                case 8 -> new int[0];
                case 9 -> "";
                case 10 -> '0';
                default -> null;
            };

            // parenthesized pattern - for additional checks
            Object result = switch (object) {
                case null -> 0L;
                case Integer i when i >= 0 -> i.hashCode();
                case Long l -> l.hashCode();
                case Float f -> f.hashCode();
                case Double d when d % 2 == 0 -> d.hashCode();
                case Number n -> n.hashCode();
                case String s when s.length() > 0 -> s.hashCode();
                case Character c when Character.isDigit(c) -> c.hashCode();
                case Instant t -> t.hashCode();
                case int[] a -> Arrays.hashCode(a);
                default -> 1L;
            };
            log.info("* Switch [pattern matching]: {}", result);
        }

        // label
        {
            int a = 10;
            switcher: switch (a) {
                case 10: {
                    log.info("* Switch [label]: {}", a);
                    break switcher;
                }
            }
        }
    }

    /**
     * Unconditional statements.
     */
    private void invokeUnconditional() {
        // Java supports three types of jump statements: break, continue and return.
        // These statements transfer control of execution to another part of the program.

        log.info("Unconditional statements: break, continue, return");

        // break
        log.info("-- Break");
        invokeBreak();
        // tab
        log.info("");
        // continue
        log.info("-- Continue");
        invokeContinue();
        // tab
        log.info("");
        // return
        log.info("-- Return");
        invokeReturn();
    }

    /**
     * Break statements.
     */
    private void invokeBreak() {
        // When a break statement encounters inside a loop statement,
        // the loop immediately ends at a specified condition.

        log.info("A break statement in Java is used to break a loop or switch statement.");

        // loop
        {
            for (int i = 0; i < 1_000_000; i++) {
                if (i > 2) break;
                log.info("* Break [loop]: {}", i + 1);
            }
        }

        // switch
        {
            int i = 1;
            switch (i) {
                case 0: {
                    log.info("* Break [switch]: 0th");
                    break;
                }
                case 1: {
                    log.info("* Break [switch]: 1st");
                    break;
                }
                case 2: {
                    log.info("* Break [switch]: 2nd");
                    break;
                }
                default: throw new RuntimeException("unexpected i");
            }
        }

        // block
        {
            outer:
            {
                log.info("* Break [block]: inner");
                break outer;
            }
            log.info("* Break [block]: outer");
        }
    }

    /**
     * Continue statements.
     */
    private void invokeContinue() {
        // Continue statement is used inside a loop to repeat the next iteration of the loop.

        log.info("Continue statement stops the current iteration of loop and begins a new iteration of loop.");

        // for loop
        {
            for (int i = 0; i < 6; i++) {
                if ((i & 1) == 0) continue;
                log.info("* Continue [for loop]: {}", i);
            }
        }

        // while loop
        {
            int counter = 0;
            while (counter <= 3) {
                counter++;
                if (counter == 2) continue;
                log.info("* Continue [while loop]: {}", counter);
            }
        }

        // label
        {
            outer: for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (i == 1 && j == 1) continue outer;
                    log.info("* Continue [labelled loop]: {}x{}", i, j);
                }
            }
        }
    }

    /**
     * Return statements.
     */
    private void invokeReturn() {
        // The return statement inside a loop will cause the loop to break
        // and further statements will be ignored by the compiler.

        log.info("Return statement is used for returning a value when the execution of the block is completed.");

        // sample
        {
            ReturnStatement statement = new ReturnStatement();
            String input = "Text";

            statement.information(input);
            input = statement.formatting(input);
            statement.information(input);

            return;

            // unreachable scope
        }
    }
}
