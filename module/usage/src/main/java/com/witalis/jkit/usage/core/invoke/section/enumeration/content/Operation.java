package com.witalis.jkit.usage.core.invoke.section.enumeration.content;

/**
 * Desc: Enumeration inherited
 * User: Wellaxis
 * Date: 21.12.2021
 */
// enum with behaviour
public enum Operation implements Operatable {
    // constant-specific class body
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
