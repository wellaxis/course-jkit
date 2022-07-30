package com.witalis.jkit.usage.core.invoke.section.enumeration.content;

/**
 * Desc: Enumeration inherited
 * User: Wellaxis
 * Date: 21.12.2021
 */
// inheritance of enumerations - emulation based on interfaces
public enum ExtendedOperation implements Operatable {
    ЕХР("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
