package com.witalis.jkit.spring.data.jpa.projection.model.view.clazz;

import com.witalis.jkit.spring.data.jpa.projection.model.PlanetMoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlanetClassView {
    private String name;
    private String note;
    // collections cannot be used in dto projections
    private List<PlanetMoon> moons = new ArrayList<>();

    public PlanetClassView(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public List<PlanetMoon> getMoons() {
        return moons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetClassView that = (PlanetClassView) o;
        return name.equals(that.name) && note.equals(that.note) && moons.equals(that.moons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, note, moons);
    }
}
