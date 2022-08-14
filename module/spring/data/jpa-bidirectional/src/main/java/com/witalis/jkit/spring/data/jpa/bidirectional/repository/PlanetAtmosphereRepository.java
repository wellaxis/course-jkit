package com.witalis.jkit.spring.data.jpa.bidirectional.repository;

import com.witalis.jkit.spring.data.jpa.bidirectional.model.PlanetAtmosphere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("atmosphereRepository")
public interface PlanetAtmosphereRepository extends JpaRepository<PlanetAtmosphere, Long> {

    @Query("SELECT a FROM Atmosphere a JOIN FETCH a.planets p WHERE p.id = :planetId")
    List<PlanetAtmosphere> findAllByPlanetId(long planetId);
}
