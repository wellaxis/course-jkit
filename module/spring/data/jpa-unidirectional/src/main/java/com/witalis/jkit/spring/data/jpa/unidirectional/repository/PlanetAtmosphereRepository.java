package com.witalis.jkit.spring.data.jpa.unidirectional.repository;

import com.witalis.jkit.spring.data.jpa.unidirectional.model.PlanetAtmosphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("atmosphereRepository")
public interface PlanetAtmosphereRepository extends JpaRepository<PlanetAtmosphere, Long> {
}
