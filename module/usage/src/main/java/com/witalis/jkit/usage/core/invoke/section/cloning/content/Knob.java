package com.witalis.jkit.usage.core.invoke.section.cloning.content;

import lombok.*;

/**
 * Desc: Knob class
 * User: Wellaxis
 * Date: 3/23/2022
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Knob {
    private String shape;
    private boolean lock;
    private float angle;
}
