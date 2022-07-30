package com.witalis.jkit.usage.core.invoke.section.lambda.content.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@FunctionalInterface
public interface HandlingCheckedConsumer<T, E extends Exception> {
    void accept(T target) throws E;

    @Slf4j
    final class LogHolder {}

    static <T> Consumer<T> checkedHandling(
        HandlingCheckedConsumer<T, Exception> handlingConsumer
    ) {
        return obj -> {
            try {
                handlingConsumer.accept(obj);
            } catch (Exception e) {
                var message = e.getMessage();
                LogHolder.log.error("{} occurred: {}", e.getClass().getSimpleName(), message);
                throw new RuntimeException(e);
            }
        };
    }
}
