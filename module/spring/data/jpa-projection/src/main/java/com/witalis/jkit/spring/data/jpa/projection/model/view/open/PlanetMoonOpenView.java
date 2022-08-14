package com.witalis.jkit.spring.data.jpa.projection.model.view.open;

import org.springframework.beans.factory.annotation.Value;

public interface PlanetMoonOpenView {

    @Value("#{'MOON> ' + target.name + ' ' + target.note}")
    String getDescription();
}
