package com.witalis.jkit.usage.core.invoke.section.cloning.content;

import java.util.List;
import java.util.Set;

/**
 * Desc: House factory
 * User: Wellaxis
 * Date: 4/10/2022
 */
public class Factory {

    /**
     * It initialises the house instance.
     * <p/>
     * @return the initialised house
     */
    public static House makeHouse() {
        return House.builder()
            .number(111L)
            .square(345.88D)
            .address("Liberty str.")
            .skyscraper(true)
            .door(
                Door.builder().height(3).width(2).material(Material.GLASS).locking(true).build()
            )
            .roof(
                Roof.builder().type('A').chimney(true).shape("Plane").color(Color.GREEN).build()
            )
            .rooms(
                Set.of(
                    Room.builder().square(33.3D).label('s').name("Standard").knobs(Set.of(
                        Knob.builder().shape("A1").lock(true).angle(30F).build(),
                        Knob.builder().shape("B1").lock(false).angle(25F).build()
                    )).build(),
                    Room.builder().square(66.6D).label('x').name("Extra").knobs(Set.of(
                        Knob.builder().shape("A2").lock(false).angle(10F).build(),
                        Knob.builder().shape("B2").lock(true).angle(15F).build()
                    )).build(),
                    Room.builder().square(99.9D).label('v').name("VIP").knobs(Set.of(
                        Knob.builder().shape("V2").lock(true).angle(15F).build(),
                        Knob.builder().shape("V2").lock(true).angle(20F).build()
                    )).build()
                )
            )
            .windows(
                List.of(
                    Window.builder().section((short) 5).shape("Round").material(Material.GLASS).knobs(List.of(
                        Knob.builder().shape("A1").lock(true).angle(30F).build(),
                        Knob.builder().shape("B1").lock(false).angle(25F).build()
                    )).build(),
                    Window.builder().section((short) 7).shape("Square").material(Material.IRON).knobs(List.of(
                        Knob.builder().shape("A2").lock(false).angle(10F).build(),
                        Knob.builder().shape("B2").lock(true).angle(15F).build()
                    )).build(),
                    Window.builder().section((short) 9).shape("Design").material(Material.WOOD).knobs(List.of(
                        Knob.builder().shape("V2").lock(true).angle(15F).build(),
                        Knob.builder().shape("V2").lock(true).angle(20F).build()
                    )).build()
                )
            )
            .build();
    }
}
