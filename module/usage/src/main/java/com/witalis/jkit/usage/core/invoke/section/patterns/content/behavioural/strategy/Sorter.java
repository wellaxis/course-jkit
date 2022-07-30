package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.strategy;

/**
 * Desc: Sorter class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Sorter {
    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public int[] sort(int[] numbers) {
        return strategy.sort(numbers);
    }
}
