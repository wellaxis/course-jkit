package com.witalis.jkit.spring.data.jpa.nplusone.service;

import com.witalis.jkit.spring.data.jpa.nplusone.model.Planet;

import java.util.List;

public interface PlanetService {

    List<Planet> findAllUseCriteriaApiBy();
}
