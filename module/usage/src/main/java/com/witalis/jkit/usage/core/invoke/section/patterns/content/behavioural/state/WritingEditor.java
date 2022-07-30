package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.state;

import lombok.Data;
import lombok.ToString;

/**
 * Desc: Writing Editor class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@ToString
public class WritingEditor {
    private WritingState state;

    public WritingEditor(WritingState state) {
        this.state = state;
    }

    public void type(String text) {
        state.write(text);
    }
}
