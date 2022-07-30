package com.witalis.jkit.usage.core.invoke.section.inheritance.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Four dimensional
 * User: Wellaxis
 * Date: 4/9/2022
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FourDimensional extends ThreeDimensional {
    private int t;

    public FourDimensional(final int x, final int y, final int z, final int t) {
        super(x, y, z);
        this.t = t;
    }

    @Override
    public void reflect() {
        log.info("Measure[location]: {}", calculateLocation());
    }

    public int calculateLocation() {
        return Math.multiplyExact(calculateVolume(), getT());
    }

    @Override
    public String toString() {
        return "Dimensional(Four) [" + "x=" + getX() + "y=" + getY() + "z=" + getZ() + "t=" + getT() + ']';
    }
}
