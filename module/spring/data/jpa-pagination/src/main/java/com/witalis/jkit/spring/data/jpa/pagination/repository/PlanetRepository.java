package com.witalis.jkit.spring.data.jpa.pagination.repository;

import com.witalis.jkit.spring.data.jpa.pagination.model.Planet;
import com.witalis.jkit.spring.data.jpa.pagination.model.PlanetType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("planetRepository")
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    List<Planet> findAllPageableBy(Pageable pageable);

    Page<Planet> findAllByTypeEquals(PlanetType type, Pageable pageable);

    Slice<Planet> findByNameIgnoreCaseContaining(String mask, Pageable pageable);

    @Query("select p from Planet p")
    Page<Planet> findAllQueryBy(Pageable pageable);

    @Query(value = "select p.* from planet p", nativeQuery = true)
    Page<Planet> findAllNativeBy(Pageable pageable);

    List<Planet> findAllSortableBy(Sort sort);

    @Query("select p from Planet p order by p.name asc")
    Page<Planet> sortAllQueryBy(Pageable pageable);

    @Query(value = "select p.* from planet p order by p.type asc, p.name desc", nativeQuery = true)
    Page<Planet> sortAllNativeBy(Pageable pageable);
}
