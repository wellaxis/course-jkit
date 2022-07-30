package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.strategy;

/**
 * Desc: Sort Invoker class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class SortInvoker {
    public static final int threshold = 10;
    private int[] numbers;

    public SortInvoker(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] sort() {
        SortStrategy strategy;
        if (numbers.length > threshold) {
            strategy = new QuickSortStrategy();
        } else {
            strategy = new BubbleSortStrategy();
        }
        return new Sorter(strategy).sort(numbers);
    }
}
