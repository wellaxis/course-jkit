package com.witalis.jkit.spring.data.jpa.projection.repository;

import com.witalis.jkit.spring.data.jpa.projection.model.Planet;
import com.witalis.jkit.spring.data.jpa.projection.model.PlanetType;
import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetClassView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.close.PlanetCloseView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.open.PlanetOpenView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("planetRepository")
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    // base entity loading
    List<Planet> getAllByType(PlanetType type);

    // interface-based close projection
    List<PlanetCloseView> getAllCloseViewByType(PlanetType type);

    // interface-based open projection
    List<PlanetOpenView> getAllOpenViewByType(PlanetType type);

    // class-based dto projection
    List<PlanetClassView> getAllClassViewByType(PlanetType type);

    // dynamic projection
    <T> List<T> getAllDynamicViewByType(PlanetType type, Class<T> classType);

    // native query projection
    @Query(name = "Planet.getDetails", nativeQuery = true)
    List<Object> getAllNativeViewByType(@Param("planetType") String planetType);

    // jpql query projection
    @Query("select distinct p from Planet p join fetch p.moons where p.type = :planetType")
    List<PlanetCloseView> getAllQueryViewByType(PlanetType planetType);
}
