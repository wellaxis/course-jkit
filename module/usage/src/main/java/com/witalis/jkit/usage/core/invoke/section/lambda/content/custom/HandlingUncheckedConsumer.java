package com.witalis.jkit.usage.core.invoke.section.lambda.content.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@FunctionalInterface
public interface HandlingUncheckedConsumer<T, E extends Exception> {
    void accept(T target);

    @Slf4j
    final class LogHolder {}

    static <T, E extends Exception> Consumer<T> uncheckedHandling(
        HandlingUncheckedConsumer<T, Exception> handlingConsumer,
        Class<E> exceptionClass
    ) {
        return obj -> {
            try {
                handlingConsumer.accept(obj);
            } catch (Exception e) {
                try {
                    E exception = exceptionClass.cast(e);
                    var message = exception.getMessage();
                    LogHolder.log.error("{} occurred: {}", e.getClass().getSimpleName(), message);
                } catch (ClassCastException cce) {
                    throw e;
                }
            }
        };
    }
}
