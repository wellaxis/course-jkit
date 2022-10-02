package com.witalis.jkit.shell.service;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Service;

@Service
public class ShellPromptProvider implements PromptProvider {
    public static final String PROMPT = "jkit~";

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(
            PROMPT,
            AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW)
        );
    }
}
