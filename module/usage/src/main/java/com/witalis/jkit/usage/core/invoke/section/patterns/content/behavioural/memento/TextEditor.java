package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.memento;

import lombok.Data;
import lombok.ToString;

/**
 * Desc: Text Editor - caretaker
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@ToString
public class TextEditor {
    private TextWindow textWindow;
    private TextWindowState savedTextWindow;

    public TextEditor(TextWindow textWindow) {
        this.textWindow = textWindow;
    }

    public void write(String text) {
        textWindow.addText(text);
    }

    public void hitSave() {
        savedTextWindow = textWindow.save();
    }

    public void hitUndo() {
        textWindow.restore(savedTextWindow);
    }
}
