package com.witalis.jkit.usage.core.invoke.section.abstraction.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Abstract Show
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
public abstract class AbstractShow implements InterfaceShow {
    protected String note = "ABC";

    @Override
    public abstract void show();

    public void note() {
        log.info("Note: " + note);
    }

    public void hide() {
        log.info("Hide, me.");
    }

    // final = early binding vs late binding
    public final void take() {
        log.info("Unable to override!");
    }
}
