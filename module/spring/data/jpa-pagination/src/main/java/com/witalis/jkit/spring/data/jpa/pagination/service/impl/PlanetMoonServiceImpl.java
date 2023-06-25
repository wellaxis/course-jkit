package com.witalis.jkit.spring.data.jpa.pagination.service.impl;

import com.witalis.jkit.spring.data.jpa.pagination.model.PlanetMoon;
import com.witalis.jkit.spring.data.jpa.pagination.service.PlanetMoonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Service("moonService")
public class PlanetMoonServiceImpl implements PlanetMoonService {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public PlanetMoonServiceImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<PlanetMoon> findAllCriteriaBy(int offset, int limit) {
        CriteriaQuery<PlanetMoon> query = criteriaBuilder.createQuery(PlanetMoon.class);
        Root<PlanetMoon> moon = query.from(PlanetMoon.class);
        query.select(moon);
        TypedQuery<PlanetMoon> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(offset * limit).setMaxResults(limit);
        return typedQuery.getResultList();
    }

    @Override
    public List<PlanetMoon> sortAllCriteriaBy(int offset, int limit) {
        CriteriaQuery<PlanetMoon> query = criteriaBuilder.createQuery(PlanetMoon.class);
        Root<PlanetMoon> moon = query.from(PlanetMoon.class);
        query.select(moon);
        query.orderBy(
            criteriaBuilder.desc(moon.get("radius")),
            criteriaBuilder.asc(moon.get("distance")),
            criteriaBuilder.asc(moon.get("name"))
        );
        TypedQuery<PlanetMoon> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(offset * limit).setMaxResults(limit);
        return typedQuery.getResultList();
    }
}
