package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Bubble Sort Strategy class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class BubbleSortStrategy implements SortStrategy {

    @Override
    public int[] sort(int[] numbers) {
        log.info("-> Sort [Bubble]: in action");
        bubbleSort(numbers, numbers.length);
        return numbers;
    }

    public void bubbleSort(int[] arr, int len) {
        for (int i = 0; i < len - 1; ++i) {
            for (int j = 0; j < len - i - 1; ++j) {
                if (arr[j + 1] < arr[j]) {
                    int swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }
        }
    }
}
