package com.witalis.jkit.usage.core.invoke.section.cloning.content;

import lombok.*;

/**
 * Desc: Roof class
 * User: Wellaxis
 * Date: 3/23/2022
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Roof implements Cloneable {
    private char type;
    private boolean chimney;
    private String shape;
    private Color color;
}
