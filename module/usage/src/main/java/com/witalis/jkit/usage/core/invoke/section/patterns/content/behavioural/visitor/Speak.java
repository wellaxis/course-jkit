package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Speak class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Speak implements AnimalOperation {

    @Override
    public void visitMonkey(Monkey monkey) {
        log.info("Monkey: " + monkey.shout());
    }

    @Override
    public void visitLion(Lion lion) {
        log.info("Lion: " + lion.shout());
    }

    @Override
    public void visitDolphin(Dolphin dolphin) {
        log.info("Dolphin: " + dolphin.shout());
    }
}
