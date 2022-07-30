package com.witalis.jkit.usage.core.invoke.section.cloning.content;

import lombok.*;

/**
 * Desc: Door class
 * User: Wellaxis
 * Date: 3/23/2022
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Door {
    private int height;
    private int width;
    private Material material;
    private boolean locking;
}
