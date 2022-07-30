package com.witalis.jkit.usage.core.invoke.section.inheritance.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Two dimensional
 * User: Wellaxis
 * Date: 4/9/2022
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TwoDimensional extends OneDimensional {
    private int y;

    public TwoDimensional(final int x, final int y) {
        super(x);
        this.y = y;
    }

    @Override
    public void reflect() {
        log.info("Measure[square]: {}", calculateSquare());
    }

    public int calculateSquare() {
        return Math.multiplyExact(calculatePoint(), getY());
    }

    @Override
    public String toString() {
        return "Dimensional(Two) [" + "x=" + getX() + "y=" + getY() + ']';
    }
}
