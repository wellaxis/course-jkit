package com.witalis.jkit.spring.data.jpa.projection.service.impl;

import com.witalis.jkit.spring.data.jpa.projection.model.PlanetMoon;
import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetMoonClassView;
import com.witalis.jkit.spring.data.jpa.projection.service.PlanetMoonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
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
    public List<PlanetMoonClassView> getAllCriteriaViewByPlanetId(Long planetId) {
        CriteriaQuery<PlanetMoonClassView> query = criteriaBuilder.createQuery(PlanetMoonClassView.class);
        Root<PlanetMoon> root = query.from(PlanetMoon.class);
        query.multiselect(root.get("name"), root.get("note"));
        query.where(criteriaBuilder.equal(root.get("planet").get("id"), planetId));
        return entityManager.createQuery(query).getResultList();
    }
}
