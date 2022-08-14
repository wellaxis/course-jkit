package com.witalis.jkit.spring.data.jpa.bidirectional.repository;

import com.witalis.jkit.spring.data.jpa.bidirectional.model.PlanetMoon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("moonRepository")
public interface PlanetMoonRepository extends JpaRepository<PlanetMoon, Long> {

    @Query("SELECT pm FROM Moon pm JOIN FETCH pm.planet WHERE pm.planet.id = :planetId")
    List<PlanetMoon> findAllByPlanetId(long planetId);

    List<PlanetMoon> getAllByPlanetId(long planetId);
}
