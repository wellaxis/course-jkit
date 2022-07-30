package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Several generic types
 */
public class SuperGen<P, S, V> {
    private P p;
    private S s;
    private V v;

    public SuperGen(final P p, final S s, final V v) {
        super();
        this.p = p;
        this.s = s;
        this.v = v;
    }

    @Override
    public String toString() {
        return getClass() + "\t" + "Info: P=" + p + ", S=" + s + ", V=" + v;
    }
}