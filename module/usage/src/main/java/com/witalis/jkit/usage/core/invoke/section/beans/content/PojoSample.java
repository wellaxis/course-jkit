package com.witalis.jkit.usage.core.invoke.section.beans.content;

/**
 * Desc: Java POJO
 * User: Wellaxis
 * Date: 4/10/2022
 */
public class PojoSample {
    private int identifier;
    private String name;

    public PojoSample(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public int retrieveIdentifier() {
        return identifier;
    }

    public String retrieveName() {
        return name;
    }

    @Override
    public String toString() {
        return "PojoSample [" + "id=" + identifier + ", name='" + name + "']";
    }
}
