package com.witalis.jkit.spring.data.jpa.projection.model.view.clazz;

import java.util.Objects;

public class PlanetMoonClassView {
    private String name;
    private String note;

    public PlanetMoonClassView(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetMoonClassView that = (PlanetMoonClassView) o;
        return name.equals(that.name) && note.equals(that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, note);
    }
}
