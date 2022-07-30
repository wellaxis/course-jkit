package com.witalis.jkit.usage.core.invoke.section.abstraction.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Final Class Show
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
// final class to prevent inheritance
public final class FinalShow extends UsualShow {

    public FinalShow() {
        note = "GHI";
    }
}
