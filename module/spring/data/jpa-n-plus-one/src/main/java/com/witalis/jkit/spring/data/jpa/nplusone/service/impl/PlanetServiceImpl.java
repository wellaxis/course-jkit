package com.witalis.jkit.spring.data.jpa.nplusone.service.impl;

import com.witalis.jkit.spring.data.jpa.nplusone.model.Planet;
import com.witalis.jkit.spring.data.jpa.nplusone.service.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Service("planetService")
public class PlanetServiceImpl implements PlanetService {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    @Autowired
    public PlanetServiceImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<Planet> findAllUseCriteriaApiBy() {
        // criteria
        CriteriaQuery<Planet> query = criteriaBuilder.createQuery(Planet.class);
        Root<Planet> planet = query.from(Planet.class);
        // fetching
        planet.fetch("moons", JoinType.LEFT);
        query.select(planet).distinct(true);
        // result
        TypedQuery<Planet> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
