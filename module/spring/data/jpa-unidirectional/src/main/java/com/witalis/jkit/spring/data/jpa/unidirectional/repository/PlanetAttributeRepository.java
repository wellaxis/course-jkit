package com.witalis.jkit.spring.data.jpa.unidirectional.repository;

import com.witalis.jkit.spring.data.jpa.unidirectional.model.PlanetAttribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("attributeRepository")
public interface PlanetAttributeRepository extends JpaRepository<PlanetAttribute, Long> {
}
