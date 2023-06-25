package com.witalis.jkit.shell.utils;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
public class ShellPromptProvider implements PromptProvider {
    public static final String PROMPT = "jkit |>";

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(
            PROMPT,
            AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)
        );
    }
}
