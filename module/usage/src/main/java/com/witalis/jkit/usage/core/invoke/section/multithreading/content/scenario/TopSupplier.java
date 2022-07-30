package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Supplier
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
public final class TopSupplier<T extends TopProduct> {
    public static final int MIN_PRICE = 100;
    public static final int MAX_PRICE = 1000;

    private int batchSize;

    public TopSupplier(int batchSize) {
        this.batchSize = batchSize;
    }

    @SuppressWarnings("unchecked")
    public T supply(int index) {
        var product = new TopProduct(
            UUID.randomUUID(),
            "Index-" + index,
            ThreadLocalRandom.current().nextInt(MIN_PRICE, MAX_PRICE)
        );
        return (T) product;
    }
}
