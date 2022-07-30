package com.witalis.jkit.usage.core.invoke.section.operators;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.operators.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Operators
 * User: Wellaxis
 * Date: 2022/04/24
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class OperatorInvoker extends Invoker {

    public OperatorInvoker() {
        setTitle("Operators chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // arithmetic
        log.info("## Arithmetic Operators");
        invokeArithmetic();
        // tab
        log.info("");
        // relational
        log.info("## Relational Operators");
        invokeRelational();
        // tab
        log.info("");
        // logical
        log.info("## Logical Operators");
        invokeLogical();
        // tab
        log.info("");
        // assignment
        log.info("## Assignment Operators");
        invokeAssignment();
        // tab
        log.info("");
        // unary
        log.info("## Unary Operators");
        invokeUnary();
        // tab
        log.info("");
        // conditional
        log.info("## Conditional Operators");
        invokeConditional();
        // tab
        log.info("");
        // bitwise
        log.info("## Bitwise Operators");
        invokeBitwise();
    }

    /**
     * Basic postulates of operators.
     */
    private void invokeBasis() {
        // In Java, an expression has two parts: operand and operator.

        // Operands - the variables or constants that operators act upon.
        // Operator - a symbol or a keyword that tells the compiler to perform a specific mathematical or logical operations.
        // Expression - a combination of variables, constants, and operators.

        // types
        {
            log.info("There are three types of operators in Java based on the number of operands used to perform an operation.");
            // unary operator
            log.info("* Unary operator: the operator that acts on a single operand is called unary operator. A unary operator uses a single variable.");
            // binary operator
            log.info("* Binary operator: the operator that acts on two operands is called binary operator. A binary operator uses two variables.");
            // ternary operator
            log.info("* Ternary operator: the operator that acts on three operands is called ternary operator. A ternary operator uses three variables.");
        }

        log.info("");

        // categories
        {
            log.info("Java operator has been divided into two categories: Symbolic and Named.");
            // symbolic operator
            log.info("If a symbol like +, -, *, etc is used as an operator, it is called symbolic operator.");

            log.info("* Arithmetic operators:   +, -, *, /, etc.");
            log.info("* Relational operators:   <, >, <=, >=, ==, !=");
            log.info("* Logical operators:      &&, ||, !");
            log.info("* Assignment operators:   =");
            log.info("* In/decrement operators: ++, ––");
            log.info("* Conditional operators:  ?:");
            log.info("* Bitwise operators:      &, !, ^, ~, <<, >>, >>>");
            log.info("* Shift operators:        <<, >>, >>>");

            // named operator
            log.info("If a keyword is used as an operator, it is called named operator.");

            log.info("* Instanceof operator");
        }
    }

    /**
     * Arithmetic operators.
     */
    private void invokeArithmetic() {
        // Arithmetic operators are used to performing fundamental arithmetic operations such as:
        // addition, subtraction, multiplication, and division on numeric data types.

        // The numeric data types can be byte, short, int, long, float, and double.
        // Java provides five arithmetic operators: +, -, *, /, %

        // integer
        {
            log.info("Integer arithmetic operators:");

            int a = 30;
            int b = 11;

            log.info("- Values: a = {}, b = {}", a, b);

            log.info("* Addition: a + b = {}", a + b);
            log.info("* Subtraction: a - b = {}", a - b);
            log.info("* Multiplication: a * b = {}", a * b);
            log.info("* Division: a / b = {}", a / b);
            log.info("* Modulo division: a % b = {}", a % b);
        }

        // real
        {
            log.info("Real arithmetic operators:");

            double a = 30.5D;
            float b = 11.1F;

            log.info("- Values: a = {}, b = {}", a, b);

            log.info("* Addition: a + b = {}", a + b);
            log.info("* Subtraction: a - b = {}", a - b);
            log.info("* Multiplication: a * b = {}", a * b);
            log.info("* Division: a / b = {}", a / b);
            log.info("* Modulo division: a % b = {}", a % b);
        }

        // mixed
        {
            log.info("Mixed arithmetic operators:");

            double a = 30.3D;
            long b = 11L;

            log.info("- Values: a = {}, b = {}", a, b);

            log.info("* Addition: a + b = {}", a + b);
            log.info("* Subtraction: a - b = {}", a - b);
            log.info("* Multiplication: a * b = {}", a * b);
            log.info("* Division: a / b = {}", a / b);
            log.info("* Modulo division: a % b = {}", a % b);
        }
    }

    /**
     * Relational operators.
     */
    private void invokeRelational() {
        // Relational operators - are used to perform the comparison between two numeric values or two quantities.
        // These operators determine the relationship between them by comparing operands.

        // 1. Relational operators in Java are the most frequently used operators.
        // 2. These operators are mainly used to determine equality and order.
        // 3. The six relational operators are < , > , <= , >= , == , and != .
        // 4. Equality in Java is represented with two equal signs, not one.
        // 5. In Java, integers, floating-point numbers, characters, and booleans can be compared using the equality (==), and inequality (!=).

        log.info("The result of all relational operators is always of a boolean type: true of false.");
        log.info("There are six types of relational operators.");

        // numeric
        {
            int a = 10;
            float b = 30.5F;

            log.info("- Values: a = {}, b = {}", a, b);

            log.info(">= : a is greater than equal to b: {}", a >= b);
            log.info("> : a is greater than b: {}", a > b);
            log.info("<= : a is less than equal to b: {}", a <= b);
            log.info("< : a is less than b: {}", a < b);
            log.info("== : a is equal to b: {}", a ==b);
            log.info("!= : a is not equal to b: {}", a !=b);
        }
    }

    /**
     * Logical operators.
     */
    private void invokeLogical() {
        // Logical operators - are used to form compound conditions by combining two or more conditions or relations.
        // These operators are also called Boolean operators because they return a boolean value.

        log.info("There are three types of logical operators: AND '&&', OR '||', NOT '!'");

        // logical and
        {
            log.info("AND: If both conditions are true, the operator returns true.");

            int a = 10;
            float b = 20F;

            log.info("- Values: a = {}, b = {}", a, b);

            log.info("* && : a < b && 2a == b: {}", a < b && 2 * a == b);
        }

        // logical or
        {
            log.info("OR: If either one of the conditions is true, the operator returns true.");

            int a = 10;
            float b = 20F;

            log.info("- Values: a = {}, b = {}", a, b);

            log.info("* || : a < b || a >= b: {}", a < b || a >= b);
        }

        // logical not
        {
            log.info("NOT: If the condition is correct, the logical NOT operator returns false.");

            int a = 10;
            float b = 20F;

            log.info("- Values: a = {}, b = {}", a, b);

            log.info("* ! : !(a >= b): {}", !(a >= b));
        }

        // short-circuiting
        {
            // not all logical parts are calculated (from left to right)

            log.info("Short-circuiting: && and II - are left-associative");

            Checker checker = new Checker();

            int a = 10;
            int b = 20;

            // true in ||
            log.info("* True in ||: {}", checker.check(a) || checker.check(b));

            // false in &&
            log.info("* False in &&: {}", checker.check(-a) && checker.check(-b));
        }
    }

    /**
     * Assignment operators.
     */
    private void invokeAssignment() {
        // Assignment operator - is used to store a value into a particular variable.

        log.info("There are three categories of assignment operations: simple, compound, expression.");

        // simple assignment
        {
            log.info("- Simple assignment: v = expression;");

            int a = 10;
            int b = a;

            log.info("* Assign value into a variable: {}", a);
            log.info("* Assign value of variable into another variable: {}", b);
        }

        // compound assignment
        {
            log.info("- Compound assignment: v operator = expression;");

            int a = 10;
            a += 20;

            log.info("* Assign compound value into a variable: {}", a);
        }

        // expression assignment
        {
            log.info("- Expression assignment: v = expressions...;");

            int a = 10;
            int b = 20;
            int c = (2 * a + 100 / b) % 4;

            log.info("* Assign expression value into a variable: {}", c);
        }
    }

    /**
     * Unary operators.
     */
    private void invokeUnary() {
        // Unary operator - acts on a single operand.

        log.info("There are three types of unary operators: minus, increment, decrement.");

        // minus
        {
            log.info("- Unary minus: is used to negate a given value.");

            int a = 10;
            a = -a;

            log.info("* Minus operation: {}", a);
        }

        // increment
        {
            log.info("- Unary increment: is used to increase the value of a variable by one.");

            // pre incrementation (Prefix)
            {
                int a = 10;

                log.info("* Pre incrementation: {} |> {}", ++a, a);
            }

            // post incrementation (Postfix)
            {
                int a = 10;

                log.info("* Post incrementation: {} |> {}", a++, a);
            }
        }

        // decrement
        {
            log.info("- Unary decrement: is used to decrease the value of a variable by one.");

            // pre decrementation (Prefix)
            {
                int a = 10;

                log.info("* Pre decrementation: {} |> {}", --a, a);
            }

            // post decrementation (Postfix)
            {
                int a = 10;

                log.info("* Post decrementation: {} |> {}", a--, a);
            }
        }
    }

    /**
     * Conditional operators.
     */
    private void invokeConditional() {
        // Conditional operator - provides a one line approach for creating a simple conditional statement.
        // It is often used as a shorthand method for if-else statement.
        // It makes the code much more simple, shorter and readable.

        log.info("The conditional operator (?:) is also known as ternary operator in Java,");
        log.info("because it takes three operands and perform a conditional test.");

        int a = 10;
        int b = 20;

        int max = (a > b) ? a : b;
        log.info("* Ternary operation: max of {} & {} is {}", a, b, max);
    }

    /**
     * Bitwise operators.
     */
    private void invokeBitwise() {
        // Bitwise operator - acts on individual bits (0 or 1) of the operands.
        // It acts only integer data types such as byte, short, int, and long.
        // Bitwise operators in java cannot be applied to float and double data types.

        log.info("There are seven types of bitwise operators: &, |, ^, ~, <<, >>, >>>");

        int a = 51;
        int b = 98;

        log.info("- Binary: a = {} as = {}", a, Integer.toBinaryString(a));
        log.info("- Binary: b = {} as = {}", b, Integer.toBinaryString(b));

        // bitwise
        {
            // bitwise AND
            int c = a & b;
            log.info("* Bitwise AND: {} |> {}", c, Integer.toBinaryString(c));

            // bitwise OR
            int d = a | b;
            log.info("* Bitwise OR: {} |> {}", d, Integer.toBinaryString(d));

            // bitwise XOR
            int e = a ^ b;
            log.info("* Bitwise XOR: {} |> {}", e, Integer.toBinaryString(e));

            // bitwise Negate
            int f = ~a;
            log.info("* Bitwise NOT: {} |> {}", f, Integer.toBinaryString(f));
        }

        // shift
        {
            // Shifting a value to the left, n bits, is equivalent to multiplying the number by 2^n.
            // Shifting a value to the right, n bits, is equivalent to dividing the number by 2^n.

            // bitwise Left Shift
            int g = a << 1;
            log.info("* Bitwise Left Shift: {} |> {}", g, Integer.toBinaryString(g));

            // bitwise Right Shift
            int h = a >> 1;
            log.info("* Bitwise Right Shift: {} |> {}", h, Integer.toBinaryString(h));

            // bitwise Unsigned Shift
            int i = a >>> 1;
            log.info("* Bitwise Unsigned Shift: {} |> {}", i, Integer.toBinaryString(i));
        }
    }
}
