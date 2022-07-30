package com.witalis.jkit.usage.core.invoke.section.statements.content;

import lombok.extern.slf4j.Slf4j;
import java.util.Locale;

/**
 * Desc: Return statement
 * User: Wellaxis
 * Date: 4/26/2022
 */
@Slf4j
public final class ReturnStatement {

    public String formatting(String input) {
        String result = input.toUpperCase(Locale.ROOT);
        log.info("* Return [method]: value - {}", result);

        return result;
    }

    public void information(String input) {
        if (input == null) return;

        log.info("* Return [method]: void - {}", input);
    }
}
