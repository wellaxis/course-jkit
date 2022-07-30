package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Quick Sort Strategy class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class QuickSortStrategy implements SortStrategy {

    @Override
    public int[] sort(int[] numbers) {
        log.info("-> Sort [Quick]: in action");
        quickSort(numbers, 0, numbers.length - 1);
        return numbers;
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
