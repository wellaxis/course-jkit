package com.witalis.jkit.spring.data.jpa.unidirectional.repository;

import com.witalis.jkit.spring.data.jpa.unidirectional.model.Planet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("planetRepository")
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
