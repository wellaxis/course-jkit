package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Desc: Consumer
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
public class TopConsumer<T extends TopProduct> implements Runnable {
    private String id;
    private final TopStock<T> stock;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public TopConsumer(String id, TopStock<T> stock) {
        this.id = id;
        this.stock = stock;
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            try {
                Thread.sleep(10);
                T product = consume();
                log.info("[{}] Consumed <- {}", getId(), product);
            } catch (InterruptedException e) {
                if (!running.get()) {
                    log.info(
                        "\t{}> Consumer interruption, stop handle.",
                        Thread.currentThread().getName()
                    );
                } else {
                    log.error("Consumer errors: {}", e.getMessage());
                }
            }
        }
    }

    private T consume() throws InterruptedException {
        while (stock.getProducts().isEmpty()) {
            synchronized (stock) {
                log.info(
                    "\t{}> The stock is empty, wait please...",
                    Thread.currentThread().getName()
                );
                stock.wait();
            }
        }

        synchronized (stock) {
            // remove from stock
            T product = stock.getProducts().remove(0);
            // notify producer
            stock.notifyAll();
            return product;
        }
    }

    public void complete() {
        running.set(false);
    }
}
