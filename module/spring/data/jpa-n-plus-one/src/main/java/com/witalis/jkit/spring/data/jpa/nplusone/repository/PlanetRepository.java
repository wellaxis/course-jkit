package com.witalis.jkit.spring.data.jpa.nplusone.repository;

import com.witalis.jkit.spring.data.jpa.nplusone.model.Planet;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("planetRepository")
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    List<Planet> findAllBy();

    @Query("SELECT DISTINCT p FROM Planet p JOIN FETCH p.moons")
    List<Planet> findAllUseOfJoinFetchBy();

    @EntityGraph(
        value = "planet-moon",
        type = EntityGraph.EntityGraphType.FETCH
    )
    List<Planet> findAllUseEntityGraphBy();
}
