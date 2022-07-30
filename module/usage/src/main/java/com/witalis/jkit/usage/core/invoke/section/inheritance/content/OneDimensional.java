package com.witalis.jkit.usage.core.invoke.section.inheritance.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: One dimensional
 * User: Wellaxis
 * Date: 4/9/2022
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class OneDimensional {
    private int x;

    public OneDimensional(final int x) {
        this();
        this.x = x;
    }

    public void reflect() {
        log.info("Measure[point]: {}", calculatePoint());
    }

    public int calculatePoint() {
        return Math.multiplyExact(1, getX());
    }

    @Override
    public String toString() {
        return "Dimensional(One) [" + "x=" + getX() + ']';
    }
}
