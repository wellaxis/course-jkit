package com.witalis.jkit.usage.core.invoke.section.lambda.content.scenario.emulation;

/**
 * Desc: Emulation utilities
 * User: Wellaxis
 * Date: 5/14/2022
 */
public final class EmulationUtils {

    private EmulationUtils() {
        super();
    }

    // use lambda - it should be an interface
    public static <T> T valueEmulate(T value, LambdaEmulation<T> emulation) {
        return emulation.emulate(value);
    }

    // use abstract class - it should be a class
    public static <T> T valueEmulate(T value, ClassEmulation<T> simulation) {
        return simulation.simulate(value);
    }
}
