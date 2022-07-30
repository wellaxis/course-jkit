package com.witalis.jkit.usage.core.invoke.section.blocks.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Block holder
 * User: Wellaxis
 * Date: 7/10/2022
 */
@Slf4j
public class BlockHolder {
    private static int threshold;
    private int counter;

    static {
        log.info("The 1st SIB");
        threshold = 10;
    }

    {
        log.info("The 1st IIB.");
        counter = 20;
        check();
    }

    static {
        log.info("The 2nd SIB");
        threshold = 100;
    }

    {
        log.info("The 2nd IIB.");
        counter = 200;
        check();
    }

    static {
        log.info("The 3rd SIB");
        threshold = 1000;
    }

    {
        log.info("The 3rd IIB.");
        counter = 2000;
        check();
    }

    public BlockHolder() {
        log.info("Default constructor.");
    }

    public BlockHolder(int counter) {
        this();
        this.counter = counter;
        log.info("Parameterized constructor.");
        {
            log.info("Local block.");
            check();
        }
    }

    public boolean check() {
        return counter > threshold;
    }

    public static void print(BlockHolder holder) {
        log.info("Holder: [counter = {}, threshold = {}]", holder.counter, threshold);
    }
}
