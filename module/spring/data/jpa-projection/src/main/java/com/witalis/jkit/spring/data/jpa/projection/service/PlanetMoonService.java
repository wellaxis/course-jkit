package com.witalis.jkit.spring.data.jpa.projection.service;

import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetMoonClassView;

import java.util.List;

public interface PlanetMoonService {

    // criteria query projection
    List<PlanetMoonClassView> getAllCriteriaViewByPlanetId(Long planetId);
}
