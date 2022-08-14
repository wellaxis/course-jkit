package com.witalis.jkit.spring.data.jpa.unidirectional.repository;

import com.witalis.jkit.spring.data.jpa.unidirectional.model.PlanetMoon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("moonRepository")
public interface PlanetMoonRepository extends JpaRepository<PlanetMoon, Long> {
}
