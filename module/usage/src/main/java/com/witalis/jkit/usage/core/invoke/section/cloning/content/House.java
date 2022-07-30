package com.witalis.jkit.usage.core.invoke.section.cloning.content;

import lombok.*;
import java.util.*;

/**
 * Desc: House class
 * User: Wellaxis
 * Date: 3/23/2022
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class House implements Cloneable {
    private long number;
    private double square;
    private String address;
    private boolean skyscraper;
    private Door door;
    private Roof roof;
    private List<Window> windows;
    private Set<Room> rooms;

    public House(House target, boolean deep) {
        super();

        if (Objects.nonNull(target)) {
            // basic
            this.setNumber(target.getNumber());
            this.setSquare(target.getSquare());
            this.setAddress(target.getAddress());
            this.setSkyscraper(target.isSkyscraper());
            // deep
                // door
                if (Objects.nonNull(target.getDoor())) {            if (deep) {

                    this.setDoor(
                        Door.builder()
                            .height(target.getDoor().getHeight())
                            .width(target.getDoor().getWidth())
                            .material(target.getDoor().getMaterial())
                            .locking(target.getDoor().isLocking())
                            .build()
                    );
                }
                // roof
                if (Objects.nonNull(target.getRoof())) {
                    this.setRoof(
                        Roof.builder()
                            .type(target.getRoof().getType())
                            .chimney(target.getRoof().isChimney())
                            .shape(target.getRoof().getShape())
                            .color(target.getRoof().getColor())
                            .build()
                    );
                }
                // windows
                if (!target.getWindows().isEmpty()) {
                    List<Window> cloneWindows = new ArrayList<>();
                    for (Window window : target.getWindows()) {
                        Window cloneWindow = Window.builder()
                            .section(window.getSection())
                            .shape(window.getShape())
                            .material(window.getMaterial())
                            .build();
                        if (!window.getKnobs().isEmpty()) {
                            List<Knob> cloneKnobs = new ArrayList<>();
                            for (Knob knob : window.getKnobs()) {
                                cloneKnobs.add(
                                    Knob.builder()
                                        .shape(knob.getShape())
                                        .lock(knob.isLock())
                                        .angle(knob.getAngle())
                                        .build()
                                );
                            }
                            cloneWindow.setKnobs(cloneKnobs);
                        }
                        cloneWindows.add(cloneWindow);
                    }
                    this.setWindows(cloneWindows);
                }
                // rooms
                if (!target.getRooms().isEmpty()) {
                    Set<Room> cloneRooms = new HashSet<>();
                    for (Room room : target.getRooms()) {
                        Room cloneRoom = Room.builder()
                            .name(room.getName())
                            .label(room.getLabel())
                            .square(room.getSquare())
                            .build();
                        if (!room.getKnobs().isEmpty()) {
                            Set<Knob> cloneKnobs = new HashSet<>();
                            for (Knob knob : room.getKnobs()) {
                                cloneKnobs.add(
                                    Knob.builder()
                                        .shape(knob.getShape())
                                        .lock(knob.isLock())
                                        .angle(knob.getAngle())
                                        .build()
                                );
                            }
                            cloneRoom.setKnobs(cloneKnobs);
                        }
                        cloneRooms.add(cloneRoom);
                    }
                    this.setRooms(cloneRooms);
                }
            }
        }
    }

    // Native cloning
    @Override
    public House clone() throws CloneNotSupportedException{
        return (House) super.clone();
    }

    // Via constructor
    public House cloning(boolean deep) {
        return new House(this, deep);
    }
}
