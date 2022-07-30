package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.lazy;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class HolderLambda {

    private Supplier<Heavy> heavy = () -> createAndCacheHeavy();

    public HolderLambda() {
        log.info("  Holder [lambda]: created");
    }

    public Heavy getHeavy() {
        return heavy.get();
    }

    private synchronized Heavy createAndCacheHeavy() {

        class HeavyFactory implements Supplier<Heavy> {
            private final Heavy heavyInstance = new Heavy();

            public Heavy get() {
                return heavyInstance;
            }
        }

        if (!(heavy instanceof HeavyFactory)) {
            heavy = new HeavyFactory();
        }
        return heavy.get();
    }
}
