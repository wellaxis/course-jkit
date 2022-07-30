package com.witalis.jkit.usage.core.invoke.section.inheritance.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Three dimensional
 * User: Wellaxis
 * Date: 4/9/2022
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ThreeDimensional extends TwoDimensional {
    private int z;

    public ThreeDimensional(final int x, final int y, final int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public void reflect() {
        log.info("Measure[volume]: {}", calculateVolume());
    }

    public int calculateVolume() {
        return Math.multiplyExact(calculateSquare(), getZ());
    }

    @Override
    public String toString() {
        return "Dimensional(Three) [" + "x=" + getX() + "y=" + getY() + "z=" + getZ() + ']';
    }
}
