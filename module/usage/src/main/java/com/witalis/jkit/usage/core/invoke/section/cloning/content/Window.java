package com.witalis.jkit.usage.core.invoke.section.cloning.content;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: Window class
 * User: Wellaxis
 * Date: 3/23/2022
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Window implements Cloneable {
    private short section;
    private String shape;
    private Material material;
    private List<Knob> knobs;
}
