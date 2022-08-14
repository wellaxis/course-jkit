package com.witalis.jkit.spring.data.jpa.nplusone.repository;

import com.witalis.jkit.spring.data.jpa.nplusone.model.PlanetMoon;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("moonRepository")
public interface PlanetMoonRepository extends JpaRepository<PlanetMoon, Long> {

    List<PlanetMoon> findAllBy();

    @Query("SELECT pm FROM PlanetMoon pm LEFT JOIN FETCH pm.planet")
    List<PlanetMoon> findAllUseOfJoinFetchBy();

    @EntityGraph(
        value = "moon-planet",
        type = EntityGraph.EntityGraphType.FETCH
    )
    List<PlanetMoon> findAllUseEntityGraphBy();
}
