package com.witalis.jkit.usage.core.invoke.section.references.content;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SuppressWarnings("removal")
public class ReferenceObject {
    public static final String NULL_INFO = "Reference: [null]";
    private int value;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("Finalization: " + this);
    }

    @Override
    public String toString() {
        return "Reference: [" + value + ']';
    }

    public static String toNullString() {
        return NULL_INFO;
    }
}
