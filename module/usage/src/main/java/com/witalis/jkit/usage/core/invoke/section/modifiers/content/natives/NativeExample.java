package com.witalis.jkit.usage.core.invoke.section.modifiers.content.natives;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Native example
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
public class NativeExample {
    public static final String LIBRARY_NAME = "Mod3.dll";

    public NativeExample() {
        super();
        // add it to static definition
        if (false) {
            System.loadLibrary(LIBRARY_NAME);
        }
        log.info("Native [library]: {}", LIBRARY_NAME);
    }

    // native - do not define body for the method
    public native void invoke();
}
