package com.witalis.jkit.usage.core.invoke.section.generics.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Bounded wildcards
 */
@Slf4j
public class Coords<T extends DimensionalTwo> {
    T[] coords;

    public Coords (T[] о) {
        this.coords = о;
    };

    // wildcard argument
    public void showX (Coords<T> c) {
        log.info("  Coordinates Х: ");
        if (c.coords == null) return;
        for (var i = 0; i < c.coords.length; i++) {
            log.info(
                "OBJ: {}, INF: {}",
                c.coords[i].toString(),
                c.coords[i].x
            );
        }
    }

    // wildcard argument
    public void showXY (Coords<?> c) {
        log.info("  Coordinates Х Y: ");
        if (c.coords == null) return;
        log.info("  2 dimensions...");
        for (var i = 0; i < c.coords.length; i++) {
            log.info(
                "OBJ: {}, INF: {} {}",
                c.coords[i].toString(),
                c.coords[i].x,
                c.coords[i].y
            );
        }
    }

    // bounded wildcard argument - up bound - extends
    public void showXYZ (Coords<? extends DimensionalThree> c) {
        log.info("  Coordinates Х Y Z: ");
        if (c.coords == null) return;
        showXY(c);
        log.info("  3 dimensions...");
        for (var i = 0; i < c.coords.length; i++) {
            log.info(
                "OBJ: {}, INF: {} {} {}",
                c.coords[i].toString(),
                c.coords[i].x,
                c.coords[i].y,
                c.coords[i].z
            );
        }
    }

    // bounded wildcard argument - up bound - extends [included]
    public void showXYZT (Coords<? extends DimensionalFour> c) {
        log.info("  Coordinates Х Y Z T: ");
        showXYZ(c);
        if (c.coords == null) return;
        log.info("  4 dimensions...");
        for (var i = 0; i < c.coords.length; i++) {
            log.info(
                "OBJ: {}, INF: {} {} {} {}",
                c.coords[i].toString(),
                c.coords[i].x,
                c.coords[i].y,
                c.coords[i].z,
                c.coords[i].t
            );
        }
    }

    // bounded wildcard argument - down bound - super [not included]
    public void showZYX (Coords<? super DimensionalFour> c) {
        log.info("Coordinates Z Y X: ");
        if (coords == null) return;
        for (var i = 0; i < c.coords.length; i++) {
            log.info(
                "OBJ: {}, INF: {} {}",
                c.coords[i].toString(),
                c.coords[i].x,
                c.coords[i].y
                // c.coords[i].z - due to class T extends TwoD
            );
        }
    }
}