package com.witalis.jkit.spring.data.jpa.pagination;

import com.witalis.jkit.spring.data.jpa.pagination.model.Planet;
import com.witalis.jkit.spring.data.jpa.pagination.model.PlanetMoon;
import com.witalis.jkit.spring.data.jpa.pagination.model.PlanetType;
import com.witalis.jkit.spring.data.jpa.pagination.repository.PlanetMoonRepository;
import com.witalis.jkit.spring.data.jpa.pagination.repository.PlanetRepository;
import com.witalis.jkit.spring.data.jpa.pagination.service.PlanetMoonService;
import com.witalis.jkit.spring.data.jpa.pagination.service.PlanetService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Tag("pagination")
@DisplayName("Test: pagination")
@DataJpaTest
@ComponentScan
class JkitDataJpaPaginationTests {
    private final PlanetRepository planetRepository;
    private final PlanetMoonRepository planetMoonRepository;
    private final PlanetService planetService;
    private final PlanetMoonService planetMoonService;

    @Autowired
    public JkitDataJpaPaginationTests(
        final PlanetRepository planetRepository,
        final PlanetMoonRepository planetMoonRepository,
        final PlanetService planetService,
        final PlanetMoonService planetMoonService
    ) {
        this.planetRepository = planetRepository;
        this.planetMoonRepository = planetMoonRepository;
        this.planetService = planetService;
        this.planetMoonService = planetMoonService;
    }

    @BeforeAll
    public static void initialization() {
        log.info("Application tests [jpa/pagination]: In Action...");
    }

    @Nested
    @Tag("pagination")
    @DisplayName("Test: Pagination")
    class PaginationTest {

        @Nested
        @Tag("paging")
        @Tag("pagination")
        @DisplayName("Test: page")
        class PagingTest {

            @Nested
            @Tag("parent")
            @Tag("paging")
            @Tag("pagination")
            @DisplayName("Test: parent")
            class ParentTest {

                @Test
                @DisplayName("Test: list all")
                void listAll() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    List<Planet> planets = planetRepository.findAllPageableBy(pageable);

                    assertNotNull(planets);
                    assertEquals(pageable.getPageSize(), planets.size());

                    log.info(
                        "List [Planet]: elements={}, size={}",
                        count,
                        planets.size()
                    );
                }

                @Test
                @DisplayName("Test: page all")
                void pageAll() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.findAll(pageable);

                    assertNotNull(planets);
                    assertEquals(count, planets.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= planets.getTotalPages());

                    log.info(
                        "Page [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: page filter")
                void pageFilter() {
                    // given
                    var type = PlanetType.TERRESTRIAL;
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.findAllByTypeEquals(type, pageable);

                    assertNotNull(planets);
                    assertTrue(count >= planets.getTotalElements());

                    log.info(
                        "Page Filter [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );

                    while (planets.hasNext()) {
                        Pageable nextPageable = planets.nextPageable();
                        planets = planetRepository.findAllByTypeEquals(type, nextPageable);

                        assertNotNull(planets);

                        log.info(
                            "Page Next [Planet]: pages={}, elements={}, number={}, items={}",
                            planets.getTotalPages(),
                            planets.getTotalElements(),
                            planets.getNumber(),
                            planets.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: slice filter")
                void sliceFilter() {
                    // given
                    var mask = "ur";
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    Slice<Planet> planets = planetRepository.findByNameIgnoreCaseContaining(mask, pageable);

                    assertNotNull(planets);
                    assertTrue(count >= planets.getSize());

                    log.info(
                        "Slice Filter [Planet]: size={}, number={}, items={}",
                        planets.getSize(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );

                    while (planets.hasNext()) {
                        Pageable nextPageable = planets.nextPageable();
                        planets = planetRepository.findByNameIgnoreCaseContaining(mask, nextPageable);

                        assertNotNull(planets);

                        log.info(
                            "Slice Next [Planet]: size={}, number={}, items={}",
                            planets.getSize(),
                            planets.getNumber(),
                            planets.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: page query")
                void pageQuery() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.findAllQueryBy(pageable);

                    assertNotNull(planets);
                    assertEquals(count, planets.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= planets.getTotalPages());

                    log.info(
                        "Page Query [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: page native")
                void pageNative() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.findAllNativeBy(pageable);

                    assertNotNull(planets);
                    assertEquals(count, planets.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= planets.getTotalPages());

                    log.info(
                        "Page Native [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: list criteria")
                void listCriteria() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    List<Planet> planets = planetService.findAllCriteriaBy(
                        pageable.getPageNumber(),
                        pageable.getPageSize()
                    );

                    assertNotNull(planets);
                    assertTrue(count >= planets.size());

                    log.info(
                        "List Criteria [Planet]: size={}",
                        planets.size()
                    );

                    planets = planetService.findAllCriteriaBy(
                        pageable.getPageNumber() + 1,
                        pageable.getPageSize()
                    );

                    assertNotNull(planets);
                    assertTrue(count >= planets.size());

                    log.info(
                        "List Next [Planet]: size={}",
                        planets.size()
                    );
                }
            }

            @Nested
            @Tag("child")
            @Tag("paging")
            @Tag("pagination")
            @DisplayName("Test: child")
            class ChildTest {

                @Test
                @DisplayName("Test: list all")
                void listAll() {
                    // given
                    var pageable = PageRequest.of(0, 5);

                    long count = planetMoonRepository.count();
                    List<PlanetMoon> moons = planetMoonRepository.findAllPageableBy(pageable);

                    assertNotNull(moons);
                    assertEquals(pageable.getPageSize(), moons.size());

                    log.info(
                        "List [Moon]: elements={}, size={}",
                        count,
                        moons.size()
                    );
                }

                @Test
                @DisplayName("Test: page all")
                void pageAll() {
                    // given
                    var pageable = PageRequest.of(0, 5);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.findAll(pageable);

                    assertNotNull(moons);
                    assertEquals(count, moons.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= moons.getTotalPages());

                    log.info(
                        "Page [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: page filter")
                void pageFilter() {
                    // given
                    var threshold = new BigDecimal(1_000);
                    var pageable = PageRequest.of(0, 5);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.findAllByRadiusGreaterThanEqual(threshold, pageable);

                    assertNotNull(moons);
                    assertTrue(count >= moons.getTotalElements());

                    log.info(
                        "Page Filter [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );

                    while (moons.hasNext()) {
                        Pageable nextPageable = moons.nextPageable();
                        moons = planetMoonRepository.findAllByRadiusGreaterThanEqual(threshold, nextPageable);

                        assertNotNull(moons);

                        log.info(
                            "Page Next [Moon]: pages={}, elements={}, number={}, items={}",
                            moons.getTotalPages(),
                            moons.getTotalElements(),
                            moons.getNumber(),
                            moons.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: slice filter")
                void sliceFilter() {
                    // given
                    var threshold = new BigDecimal(100_000);
                    var pageable = PageRequest.of(0, 5);

                    long count = planetMoonRepository.count();
                    Slice<PlanetMoon> moons = planetMoonRepository.findAllByDistanceLessThanEqual(threshold, pageable);

                    assertNotNull(moons);
                    assertTrue(count >= moons.getSize());

                    log.info(
                        "Slice Filter [Moon]: size={}, number={}, items={}",
                        moons.getSize(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );

                    while (moons.hasNext()) {
                        Pageable nextPageable = moons.nextPageable();
                        moons = planetMoonRepository.findAllByDistanceLessThanEqual(threshold, nextPageable);

                        assertNotNull(moons);

                        log.info(
                            "Slice Next [Moon]: size={}, number={}, items={}",
                            moons.getSize(),
                            moons.getNumber(),
                            moons.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: page query")
                void pageQuery() {
                    // given
                    var pageable = PageRequest.of(0, 5);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.findAllQueryBy(pageable);

                    assertNotNull(moons);
                    assertEquals(count, moons.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= moons.getTotalPages());

                    log.info(
                        "Page Query [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: page native")
                void pageNative() {
                    // given
                    var pageable = PageRequest.of(0, 5);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.findAllNativeBy(pageable);

                    assertNotNull(moons);
                    assertEquals(count, moons.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= moons.getTotalPages());

                    log.info(
                        "Page Native [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: list criteria")
                void listCriteria() {
                    // given
                    var pageable = PageRequest.of(0, 5);

                    long count = planetMoonRepository.count();
                    List<PlanetMoon> moons = planetMoonService.findAllCriteriaBy(
                        pageable.getPageNumber(),
                        pageable.getPageSize()
                    );

                    assertNotNull(moons);
                    assertTrue(count >= moons.size());

                    log.info(
                        "List Criteria [Moon]: size={}",
                        moons.size()
                    );

                    moons = planetMoonService.findAllCriteriaBy(
                        pageable.getPageNumber() + 1,
                        pageable.getPageSize()
                    );

                    assertNotNull(moons);
                    assertTrue(count >= moons.size());

                    log.info(
                        "List Next [Moon]: size={}",
                        moons.size()
                    );
                }
            }
        }

        @Nested
        @Tag("sorting")
        @Tag("pagination")
        @DisplayName("Test: sort")
        class SortingTest {

            @Nested
            @Tag("parent")
            @Tag("sorting")
            @Tag("pagination")
            @DisplayName("Test: parent")
            class ParentTest {

                @Test
                @DisplayName("Test: sorted all")
                void sortedAll() {
                    // given
                    var sort = Sort.by("id").descending();

                    long count = planetRepository.count();
                    List<Planet> planets = planetRepository.findAllSortableBy(sort);

                    assertNotNull(planets);
                    assertEquals(count, planets.size());

                    log.info(
                        "List [Planet]: elements={}, size={}",
                        count,
                        planets.size()
                    );
                }

                @Test
                @DisplayName("Test: sorted list all")
                void sortedListAll() {
                    // given
                    var sort = Sort.by(Sort.Order.asc("name"));
                    var pageable = PageRequest.of(0, 4, sort);

                    long count = planetRepository.count();
                    List<Planet> planets = planetRepository.findAllPageableBy(pageable);

                    assertNotNull(planets);
                    assertEquals(pageable.getPageSize(), planets.size());

                    log.info(
                        "List [Planet]: elements={}, size={}",
                        count,
                        planets.size()
                    );
                }

                @Test
                @DisplayName("Test: sorted page all")
                void sortedPageAll() {
                    // given
                    var sort = Sort.by("type").ascending().and(Sort.by("name").descending());
                    var pageable = PageRequest.of(0, 3, sort);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.findAll(pageable);

                    assertNotNull(planets);
                    assertEquals(count, planets.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= planets.getTotalPages());

                    log.info(
                        "Page [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: sorted page filter")
                void sortedPageFilter() {
                    // given
                    var sort = Sort.unsorted();
                    var type = PlanetType.TERRESTRIAL;
                    var pageable = PageRequest.of(0, 3, sort);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.findAllByTypeEquals(type, pageable);

                    assertNotNull(planets);
                    assertTrue(count >= planets.getTotalElements());

                    log.info(
                        "Page Filter [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );

                    while (planets.hasNext()) {
                        Pageable nextPageable = planets.nextPageable();
                        planets = planetRepository.findAllByTypeEquals(type, nextPageable);

                        assertNotNull(planets);

                        log.info(
                            "Page Next [Planet]: pages={}, elements={}, number={}, items={}",
                            planets.getTotalPages(),
                            planets.getTotalElements(),
                            planets.getNumber(),
                            planets.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: sorted slice filter")
                void sortedSliceFilter() {
                    // given
                    var mask = "ur";
                    var pageable = PageRequest.of(0, 3, Sort.Direction.ASC, "type", "name");

                    long count = planetRepository.count();
                    Slice<Planet> planets = planetRepository.findByNameIgnoreCaseContaining(mask, pageable);

                    assertNotNull(planets);
                    assertTrue(count >= planets.getSize());

                    log.info(
                        "Slice Filter [Planet]: size={}, number={}, items={}",
                        planets.getSize(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );

                    while (planets.hasNext()) {
                        Pageable nextPageable = planets.nextPageable();
                        planets = planetRepository.findByNameIgnoreCaseContaining(mask, nextPageable);

                        assertNotNull(planets);

                        log.info(
                            "Slice Next [Planet]: size={}, number={}, items={}",
                            planets.getSize(),
                            planets.getNumber(),
                            planets.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: sorted page query")
                void sortedPageQuery() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.sortAllQueryBy(pageable);

                    assertNotNull(planets);
                    assertEquals(count, planets.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= planets.getTotalPages());

                    log.info(
                        "Page Query [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: sorted page native")
                void sortedPageNative() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    Page<Planet> planets = planetRepository.sortAllNativeBy(pageable);

                    assertNotNull(planets);
                    assertEquals(count, planets.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= planets.getTotalPages());

                    log.info(
                        "Page Native [Planet]: pages={}, elements={}, number={}, items={}",
                        planets.getTotalPages(),
                        planets.getTotalElements(),
                        planets.getNumber(),
                        planets.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: sorted list criteria")
                void sortedListCriteria() {
                    // given
                    var pageable = PageRequest.of(0, 3);

                    long count = planetRepository.count();
                    List<Planet> planets = planetService.sortAllCriteriaBy(
                        pageable.getPageNumber(),
                        pageable.getPageSize()
                    );

                    assertNotNull(planets);
                    assertTrue(count >= planets.size());

                    log.info(
                        "List Criteria [Planet]: size={}",
                        planets.size()
                    );

                    planets = planetService.sortAllCriteriaBy(
                        pageable.getPageNumber() + 1,
                        pageable.getPageSize()
                    );

                    assertNotNull(planets);
                    assertTrue(count >= planets.size());

                    log.info(
                        "List Next [Planet]: size={}",
                        planets.size()
                    );
                }
            }

            @Nested
            @Tag("child")
            @Tag("sorting")
            @Tag("pagination")
            @DisplayName("Test: child")
            class ChildTest {

                @Test
                @DisplayName("Test: sorted all")
                void sortedAll() {
                    // given
                    var sort = Sort.by("id").descending();

                    long count = planetMoonRepository.count();
                    List<PlanetMoon> moons = planetMoonRepository.findAllSortableBy(sort);

                    assertNotNull(moons);
                    assertEquals(count, moons.size());

                    log.info(
                        "List [Moon]: elements={}, size={}",
                        count,
                        moons.size()
                    );
                }

                @Test
                @DisplayName("Test: sorted list all")
                void sortedListAll() {
                    // given
                    var sort = Sort.by(Sort.Order.asc("name"));
                    var pageable = PageRequest.of(0, 10, sort);

                    long count = planetMoonRepository.count();
                    List<PlanetMoon> moons = planetMoonRepository.findAllPageableBy(pageable);

                    assertNotNull(moons);
                    assertEquals(pageable.getPageSize(), moons.size());

                    log.info(
                        "List [Moon]: elements={}, size={}",
                        count,
                        moons.size()
                    );
                }

                @Test
                @DisplayName("Test: sorted page all")
                void sortedPageAll() {
                    // given
                    var sort = Sort.by("radius").descending().and(Sort.by("distance").ascending());
                    var pageable = PageRequest.of(0, 10, sort);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.findAll(pageable);

                    assertNotNull(moons);
                    assertEquals(count, moons.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= moons.getTotalPages());

                    log.info(
                        "Page [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: sorted page filter")
                void sortedPageFilter() {
                    // given
                    var sort = Sort.unsorted();
                    var threshold = new BigDecimal(1_000);
                    var pageable = PageRequest.of(0, 10, sort);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.findAllByRadiusGreaterThanEqual(threshold, pageable);

                    assertNotNull(moons);
                    assertTrue(count >= moons.getTotalElements());

                    log.info(
                        "Page Filter [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );

                    while (moons.hasNext()) {
                        Pageable nextPageable = moons.nextPageable();
                        moons = planetMoonRepository.findAllByRadiusGreaterThanEqual(threshold, nextPageable);

                        assertNotNull(moons);

                        log.info(
                            "Page Next [Moon]: pages={}, elements={}, number={}, items={}",
                            moons.getTotalPages(),
                            moons.getTotalElements(),
                            moons.getNumber(),
                            moons.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: sorted slice filter")
                void sortedSliceFilter() {
                    // given
                    var threshold = new BigDecimal(100_000);
                    var pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "radius", "name");

                    long count = planetMoonRepository.count();
                    Slice<PlanetMoon> moons = planetMoonRepository.findAllByDistanceLessThanEqual(threshold, pageable);

                    assertNotNull(moons);
                    assertTrue(count >= moons.getSize());

                    log.info(
                        "Slice Filter [Moon]: size={}, number={}, items={}",
                        moons.getSize(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );

                    while (moons.hasNext()) {
                        Pageable nextPageable = moons.nextPageable();
                        moons = planetMoonRepository.findAllByDistanceLessThanEqual(threshold, nextPageable);

                        assertNotNull(moons);

                        log.info(
                            "Slice Next [Moon]: size={}, number={}, items={}",
                            moons.getSize(),
                            moons.getNumber(),
                            moons.getNumberOfElements()
                        );
                    }
                }

                @Test
                @DisplayName("Test: sorted page query")
                void sortedPageQuery() {
                    // given
                    var pageable = PageRequest.of(0, 10);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.sortAllQueryBy(pageable);

                    assertNotNull(moons);
                    assertEquals(count, moons.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= moons.getTotalPages());

                    log.info(
                        "Page Query [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: sorted page native")
                void sortedPageNative() {
                    // given
                    var pageable = PageRequest.of(0, 10);

                    long count = planetMoonRepository.count();
                    Page<PlanetMoon> moons = planetMoonRepository.sortAllNativeBy(pageable);

                    assertNotNull(moons);
                    assertEquals(count, moons.getTotalElements());
                    assertTrue(count / pageable.getPageSize() <= moons.getTotalPages());

                    log.info(
                        "Page Native [Moon]: pages={}, elements={}, number={}, items={}",
                        moons.getTotalPages(),
                        moons.getTotalElements(),
                        moons.getNumber(),
                        moons.getNumberOfElements()
                    );
                }

                @Test
                @DisplayName("Test: sorted list criteria")
                void sortedListCriteria() {
                    // given
                    var pageable = PageRequest.of(0, 10);

                    long count = planetMoonRepository.count();
                    List<PlanetMoon> moons = planetMoonService.sortAllCriteriaBy(
                        pageable.getPageNumber(),
                        pageable.getPageSize()
                    );

                    assertNotNull(moons);
                    assertTrue(count >= moons.size());

                    log.info(
                        "List Criteria [Moon]: size={}",
                        moons.size()
                    );

                    moons = planetMoonService.sortAllCriteriaBy(
                        pageable.getPageNumber() + 1,
                        pageable.getPageSize()
                    );

                    assertNotNull(moons);
                    assertTrue(count >= moons.size());

                    log.info(
                        "List Next [Moon]: size={}",
                        moons.size()
                    );
                }
            }
        }
    }
}
