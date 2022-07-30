package com.witalis.jkit.usage.core.invoke.section.modifiers.content.abstracts;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Concrete example
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
public class ConcreteExample extends AbstractExample {

    public ConcreteExample(int code) {
        super(code);
    }

    @Override
    public void processNote() {
        log.info("Overridden [method]: {}", this.getCode());
    }
}
