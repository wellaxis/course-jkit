package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.state;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: UpperCase class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class UpperCase implements WritingState {

    @Override
    public void write(String text) {
        log.info(text.toUpperCase());
    }
}
