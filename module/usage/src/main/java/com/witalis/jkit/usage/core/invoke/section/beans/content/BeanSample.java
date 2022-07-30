package com.witalis.jkit.usage.core.invoke.section.beans.content;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Desc: Java Bean
 * User: Wellaxis
 * Date: 4/10/2022
 */
public class BeanSample implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int identifier;
    private String name;

    public BeanSample() {
    }

    public BeanSample(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanSample that = (BeanSample) o;
        return getIdentifier() == that.getIdentifier() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getName());
    }

    @Override
    public String toString() {
        return "BeanSample [" + "id=" + identifier + ", name='" + name + "']";
    }
}
