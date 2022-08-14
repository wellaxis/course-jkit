package com.witalis.jkit.spring.data.jpa.nplusone.service;

import com.witalis.jkit.spring.data.jpa.nplusone.model.PlanetMoon;

import java.util.List;

public interface PlanetMoonService {

    List<PlanetMoon> findAllUseCriteriaApiBy();
}
