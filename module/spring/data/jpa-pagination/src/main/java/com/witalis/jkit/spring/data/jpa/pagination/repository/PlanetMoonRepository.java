package com.witalis.jkit.spring.data.jpa.pagination.repository;

import com.witalis.jkit.spring.data.jpa.pagination.model.PlanetMoon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("moonRepository")
public interface PlanetMoonRepository extends JpaRepository<PlanetMoon, Long> {

    List<PlanetMoon> findAllPageableBy(Pageable pageable);

    Page<PlanetMoon> findAllByRadiusGreaterThanEqual(BigDecimal threshold, Pageable pageable);

    Slice<PlanetMoon> findAllByDistanceLessThanEqual(BigDecimal threshold, Pageable pageable);

    @Query("select m from Moon m")
    Page<PlanetMoon> findAllQueryBy(Pageable pageable);

    @Query(value = "select m.* from moon m", nativeQuery = true)
    Page<PlanetMoon> findAllNativeBy(Pageable pageable);

    List<PlanetMoon> findAllSortableBy(Sort sort);

    @Query("select m from Moon m order by m.name asc")
    Page<PlanetMoon> sortAllQueryBy(Pageable pageable);

    @Query(value = "select m.* from moon m order by m.distance asc, m.name desc", nativeQuery = true)
    Page<PlanetMoon> sortAllNativeBy(Pageable pageable);
}
