package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Desc: Generic exceptional class
 * User: Wellaxis
 * Date: 21.01.2022
 */
public class Exceptional <T extends Exception> {

    /**
     * It throws exception via generic type.
     * <p/>
     * @throws T the generic type of exception
     */
    public void apply() throws T {
        throw new IllegalArgumentException("param/nonexistent");
    }
}