package com.witalis.jkit.spring.data.jpa.projection.service.impl;

import com.witalis.jkit.spring.data.jpa.projection.model.Planet;
import com.witalis.jkit.spring.data.jpa.projection.model.PlanetType;
import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetClassView;
import com.witalis.jkit.spring.data.jpa.projection.service.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
    public List<PlanetClassView> getAllCriteriaViewByType(PlanetType type) {
        CriteriaQuery<PlanetClassView> query = criteriaBuilder.createQuery(PlanetClassView.class);
        Root<Planet> root = query.from(Planet.class);
        query.multiselect(root.get("name"), root.get("note"));
        query.where(criteriaBuilder.equal(root.get("type"), type));
        return entityManager.createQuery(query).getResultList();
    }
}
