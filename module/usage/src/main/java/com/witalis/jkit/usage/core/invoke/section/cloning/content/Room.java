package com.witalis.jkit.usage.core.invoke.section.cloning.content;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Desc: Room class
 * User: Wellaxis
 * Date: 3/23/2022
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private double square;
    private char label;
    private String name;
    private Set<Knob> knobs;
}
