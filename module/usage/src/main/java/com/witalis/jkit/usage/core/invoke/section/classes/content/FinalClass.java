package com.witalis.jkit.usage.core.invoke.section.classes.content;

/**
 * Desc: Final class
 * User: Wellaxis
 * Date: 4/11/2022
 */
public final class FinalClass {
    private int id;

    public FinalClass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FinalClass [id=" + id + ']';
    }
}
