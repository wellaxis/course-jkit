package com.witalis.jkit.spring.data.jpa.bidirectional.repository;

import com.witalis.jkit.spring.data.jpa.bidirectional.model.PlanetAttribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("attributeRepository")
public interface PlanetAttributeRepository extends JpaRepository<PlanetAttribute, Long> {
}
