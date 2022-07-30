package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.memento;

import lombok.Data;
import lombok.ToString;

/**
 * Desc: Text Window State - memento
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@ToString
public class TextWindowState {
    private String text;

    public TextWindowState(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
