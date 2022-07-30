package com.witalis.jkit.usage.core.invoke.section.modifiers.content.finals;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: Final example
 * User: Wellaxis
 * Date: 4/12/2022
 */
public final class FinalExample {
    final String message = "Final message";
    final List<String> messages = new ArrayList<>();

    public String message(final String name) {
        // name = "Changes";
        return name;
    }
}
