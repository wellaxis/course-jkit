package com.witalis.jkit.spring.data.jpa.projection.model.view.close;

import java.util.List;

public interface PlanetCloseView {
    String getName();
    String getNote();
    List<PlanetMoonCloseView> getMoons();
}
