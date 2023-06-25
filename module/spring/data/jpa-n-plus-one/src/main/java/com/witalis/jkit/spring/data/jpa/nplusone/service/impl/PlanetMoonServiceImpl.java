package com.witalis.jkit.spring.data.jpa.nplusone.service.impl;

import com.witalis.jkit.spring.data.jpa.nplusone.model.PlanetMoon;
import com.witalis.jkit.spring.data.jpa.nplusone.service.PlanetMoonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Service("moonService")
public class PlanetMoonServiceImpl implements PlanetMoonService {
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    @Autowired
    public PlanetMoonServiceImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<PlanetMoon> findAllUseCriteriaApiBy() {
        // criteria
        CriteriaQuery<PlanetMoon> query = criteriaBuilder.createQuery(PlanetMoon.class);
        Root<PlanetMoon> moon = query.from(PlanetMoon.class);
        // fetching
        moon.fetch("planet", JoinType.LEFT);
        query.select(moon).distinct(false);
        // result
        TypedQuery<PlanetMoon> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
