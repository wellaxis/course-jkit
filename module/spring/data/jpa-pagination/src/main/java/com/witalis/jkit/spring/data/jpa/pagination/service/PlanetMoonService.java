package com.witalis.jkit.spring.data.jpa.pagination.service;

import com.witalis.jkit.spring.data.jpa.pagination.model.PlanetMoon;

import java.util.List;

public interface PlanetMoonService {

    List<PlanetMoon> findAllCriteriaBy(int offset, int limit);

    List<PlanetMoon> sortAllCriteriaBy(int offset, int limit);
}
