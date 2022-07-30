package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.memento;

import com.witalis.jkit.usage.core.invoke.section.patterns.PatternInvoker;

import lombok.Data;
import lombok.ToString;

/**
 * Desc: Text Window - originator
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@ToString
public class TextWindow {
    private final PatternInvoker patternInvoker;
    private StringBuilder currentText;

    public TextWindow(PatternInvoker patternInvoker) {
        this.patternInvoker = patternInvoker;
        this.currentText = new StringBuilder();
    }

    public void addText(String text) {
        currentText.append(text);
    }

    public TextWindowState save() {
        return new TextWindowState(currentText.toString());
    }

    public void restore(TextWindowState save) {
        currentText = new StringBuilder(save.getText());
    }
}
