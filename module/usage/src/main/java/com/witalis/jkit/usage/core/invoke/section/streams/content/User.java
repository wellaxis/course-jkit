package com.witalis.jkit.usage.core.invoke.section.streams.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Desc: User class
 * User: Wellaxis
 * Date: 4/24/2022
 */
@Data
@ToString
@AllArgsConstructor
public
class User {
    private String name;
    private double salary;

    public double getTaxes(int tax) {
        return getSalary() * tax / 100;
    }
}
