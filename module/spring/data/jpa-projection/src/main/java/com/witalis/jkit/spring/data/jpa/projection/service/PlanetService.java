package com.witalis.jkit.spring.data.jpa.projection.service;

import com.witalis.jkit.spring.data.jpa.projection.model.PlanetType;
import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetClassView;

import java.util.List;

public interface PlanetService {

    // criteria query projection
    List<PlanetClassView> getAllCriteriaViewByType(PlanetType type);
}
