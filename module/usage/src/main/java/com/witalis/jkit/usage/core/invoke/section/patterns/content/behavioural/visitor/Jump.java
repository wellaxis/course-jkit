package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Jump class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Jump implements AnimalOperation {

    @Override
    public void visitMonkey(Monkey monkey) {
        log.info("Monkey: jumps on 10 meters");
    }

    @Override
    public void visitLion(Lion lion) {
        log.info("Lion: jumps on 5 meters");
    }

    @Override
    public void visitDolphin(Dolphin dolphin) {
        log.info("Dolphin: jumps on 7 meters");
    }
}
