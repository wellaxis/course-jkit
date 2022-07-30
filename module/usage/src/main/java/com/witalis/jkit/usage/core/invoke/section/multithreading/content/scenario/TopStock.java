package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: Stock
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
public class TopStock<T extends TopProduct> {
    private TopSupplier<T> supplier;
    private List<T> products;
    private int maxSize;

    public TopStock(TopSupplier<T> supplier, int maxSize) {
        this.supplier = supplier;
        this.products = new ArrayList<>();
        this.maxSize = maxSize;
    }
}
