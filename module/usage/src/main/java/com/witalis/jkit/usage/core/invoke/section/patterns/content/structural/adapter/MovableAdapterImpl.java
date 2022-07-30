package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Desc: Movable adapter class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MovableAdapterImpl implements MovableAdapter {
    private Movable movable;

    @Override
    public double getSpeed() {
        return convertMPHtoKMH(movable.getSpeed());
    }

    private double convertMPHtoKMH(double mph) {
        return mph * 1.60934;
    }
}
