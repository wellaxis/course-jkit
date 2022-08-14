package com.witalis.jkit.spring.data.jpa.projection.repository;

import com.witalis.jkit.spring.data.jpa.projection.model.PlanetMoon;
import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetMoonClassView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.close.PlanetMoonCloseView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.open.PlanetMoonOpenView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("moonRepository")
public interface PlanetMoonRepository extends JpaRepository<PlanetMoon, Long> {

    // base entity loading
    List<PlanetMoon> getAllByPlanetId(Long planetId);

    // interface-based close projection
    List<PlanetMoonCloseView> getAllCloseViewByPlanetId(Long planetId);

    // interface-based open projection
    List<PlanetMoonOpenView> getAllOpenViewByPlanetId(Long planetId);

    // class-based dto projection
    List<PlanetMoonClassView> getAllClassViewByPlanetId(Long planetId);

    // dynamic projection
    <T> List<T> getAllDynamicViewByPlanetId(Long planetId, Class<T> classType);

    // native query projection
    @Query(name = "Moon.getDetails", nativeQuery = true)
    List<Object> getAllNativeViewByPlanetId(@Param("planetId") Long planetId);

    // jpql query projection
    @Query("select m from Moon m where m.planet.id = :planetId")
    List<PlanetMoonCloseView> getAllQueryViewByPlanetId(Long planetId);
}
