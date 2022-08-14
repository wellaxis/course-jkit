package com.witalis.jkit.spring.data.jpa.pagination.service.impl;

import com.witalis.jkit.spring.data.jpa.pagination.model.Planet;
import com.witalis.jkit.spring.data.jpa.pagination.service.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("planetService")
public class PlanetServiceImpl implements PlanetService {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public PlanetServiceImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<Planet> findAllCriteriaBy(int offset, int limit) {
        CriteriaQuery<Planet> query = criteriaBuilder.createQuery(Planet.class);
        Root<Planet> planet = query.from(Planet.class);
        query.select(planet);
        TypedQuery<Planet> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(offset * limit).setMaxResults(limit);
        return typedQuery.getResultList();
    }

    @Override
    public List<Planet> sortAllCriteriaBy(int offset, int limit) {
        CriteriaQuery<Planet> query = criteriaBuilder.createQuery(Planet.class);
        Root<Planet> planet = query.from(Planet.class);
        query.orderBy(
            criteriaBuilder.asc(planet.get("type")),
            criteriaBuilder.desc(planet.get("name"))
        );
        query.select(planet);
        TypedQuery<Planet> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(offset * limit).setMaxResults(limit);
        return typedQuery.getResultList();
    }
}
