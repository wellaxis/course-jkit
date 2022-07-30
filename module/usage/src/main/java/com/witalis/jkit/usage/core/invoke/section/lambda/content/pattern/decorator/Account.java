package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@SuppressWarnings("unchecked")
public class Account {
    private Function<Balance, Balance> operation;

    public Account() {
        setOperations();
    }

    // side-by-side operations
    public Balance capture(final Balance originalBalance) {
        log.info("\t> balance [original]: {}", originalBalance.getAmount());
        final Balance operationalBalance = operation.apply(originalBalance);
        log.info("\t> balance [operational]: {}", operationalBalance.getAmount());
        return operationalBalance;
    }

    // array of chained operations
    public void setOperations(final Function<Balance, Balance>... operations) {
        operation = Stream.of(operations)
            .reduce(Function::compose)
            .orElse(balance -> balance);
    }
}
