package com.witalis.jkit.usage.core.invoke.section.core.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Desc: Base class
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basic {
    private String name;
    private byte type;
    private short index;
    private char symbol;
    private int age;
    private long location;
    private float taxes;
    private double salary;
    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basic basic = (Basic) o;
        return getType() == basic.getType()
            && getIndex() == basic.getIndex()
            && getSymbol() == basic.getSymbol()
            && getAge() == basic.getAge()
            && getLocation() == basic.getLocation()
            && Float.compare(basic.getTaxes(), getTaxes()) == 0
            && Double.compare(basic.getSalary(), getSalary()) == 0
            && isActive() == basic.isActive()
            && getName().equals(basic.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getName(),
            getType(),
            getIndex(),
            getSymbol(),
            getAge(),
            getLocation(),
            getTaxes(),
            getSalary(),
            isActive()
        );
    }

    @Override
    public String toString() {
        return "Basic [" +
            "name='" + name + '\'' +
            ", type=" + type +
            ", index=" + index +
            ", symbol=" + symbol +
            ", age=" + age +
            ", location=" + location +
            ", taxes=" + taxes +
            ", salary=" + salary +
            ", active=" + active +
            ']';
    }
}
