package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc:
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
public class TopProducer<T extends TopProduct> implements Runnable {
    private final String id;
    private final TopStock<T> stock;

    public TopProducer(String id, TopStock<T> stock) {
        this.id = id;
        this.stock = stock;
    }

    @Override
    public void run() {
        try {
            var supplier = stock.getSupplier();

            for (int index = 1; index <= supplier.getBatchSize(); index++) {
                var product = supplier.supply(index);
                produce(product);
                log.info("[{}] Produced -> {}", getId(), product);
            }
        } catch (InterruptedException e) {
            log.error("Producer errors: {}", e.getMessage());
        }
    }

    private void produce(T product) throws InterruptedException {
        while (stock.getProducts().size() == stock.getMaxSize()) {
            synchronized (stock) {
                log.info(
                    "\t{}> The stock is full, wait please...",
                    Thread.currentThread().getName()
                );
                stock.wait();
            }
        }

        synchronized (stock) {
            // store in stock
            stock.getProducts().add(product);
            // notify consumer
            stock.notifyAll();
        }
    }
}
