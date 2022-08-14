package com.witalis.jkit.spring.data.jpa.projection.model.view.open;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface PlanetOpenView {

    @Value("#{'PLANET> ' + target.name + ' ' + target.note}")
    String getDescription();

    List<PlanetMoonOpenView> getMoons();
}
