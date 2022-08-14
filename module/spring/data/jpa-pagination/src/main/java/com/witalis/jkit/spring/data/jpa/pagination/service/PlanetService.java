package com.witalis.jkit.spring.data.jpa.pagination.service;

import com.witalis.jkit.spring.data.jpa.pagination.model.Planet;

import java.util.List;

public interface PlanetService {

    List<Planet> findAllCriteriaBy(int offset, int limit);

    List<Planet> sortAllCriteriaBy(int offset, int limit);
}
