package com.witalis.jkit.usage.core.invoke.section.types.context;

import lombok.*;

/**
 * Desc: Data holder
 * User: Wellaxis
 * Date: 4/11/2022
 */
@Data
@Builder(setterPrefix = "of")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DataHolder {
    // primitives
    private char char$;
    private byte byte$;
    private short short$;
    private int int$;
    private long long$;
    private float float$;
    private double double$;
    private boolean boolean$;
}
