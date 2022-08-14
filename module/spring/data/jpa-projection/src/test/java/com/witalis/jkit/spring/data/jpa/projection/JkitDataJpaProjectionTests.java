package com.witalis.jkit.spring.data.jpa.projection;

import com.witalis.jkit.spring.data.jpa.projection.model.Planet;
import com.witalis.jkit.spring.data.jpa.projection.model.PlanetMoon;
import com.witalis.jkit.spring.data.jpa.projection.model.PlanetType;
import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetClassView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.clazz.PlanetMoonClassView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.close.PlanetCloseView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.close.PlanetMoonCloseView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.open.PlanetMoonOpenView;
import com.witalis.jkit.spring.data.jpa.projection.model.view.open.PlanetOpenView;
import com.witalis.jkit.spring.data.jpa.projection.repository.PlanetMoonRepository;
import com.witalis.jkit.spring.data.jpa.projection.repository.PlanetRepository;
import com.witalis.jkit.spring.data.jpa.projection.service.PlanetMoonService;
import com.witalis.jkit.spring.data.jpa.projection.service.PlanetService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Tag("projection")
@DisplayName("Test: projection")
@DataJpaTest
@ComponentScan
class JkitDataJpaProjectionTests {
    private final PlanetRepository planetRepository;
    private final PlanetMoonRepository planetMoonRepository;
    private final PlanetService planetService;
    private final PlanetMoonService planetMoonService;

    @Autowired
    public JkitDataJpaProjectionTests(
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
        log.info("Application tests [jpa/projection]: In Action...");
    }

    @Nested
    @Tag("projection")
    @DisplayName("Test: Projection")
    class ProjectionTest {

        @Nested
        @Tag("entity")
        @Tag("projection")
        @DisplayName("Test: entity")
        class EntityTest {

            @Test
            @DisplayName("Test: entity query - parent")
            void entityParent() {
                // given
                var type = PlanetType.GIANT;

                List<Planet> planets = planetRepository.getAllByType(type);
                assertNotNull(planets);
                assertEquals(4, planets.size());
                Planet planet = planets.get(0);
                assertNotNull(planet);

                log.info(
                    "Entity [Planet]: {}",
                    planet
                );

                List<PlanetMoon> moons = planet.getMoons();
                assertNotNull(moons);
                assertEquals(8, moons.size());
                PlanetMoon moon = moons.get(0);
                assertNotNull(moon);

                log.info(
                    "Entity [Moon]: {}",
                    moon
                );
            }

            @Test
            @DisplayName("Test: entity query - child")
            void entityChild() {
                // given
                var id = 1_005L;

                List<PlanetMoon> moons = planetMoonRepository.getAllByPlanetId(id);
                assertNotNull(moons);
                assertEquals(8, moons.size());
                PlanetMoon moon = moons.get(0);
                assertNotNull(moon);

                log.info(
                    "Entity [Moon]: {}",
                    moon
                );
            }
        }

        @Nested
        @Tag("close-view")
        @Tag("projection")
        @DisplayName("Test: close view")
        class CloseViewTest {

            @Test
            @DisplayName("Test: close view query - parent")
            void closeViewParent() {
                // given
                var type = PlanetType.GIANT;

                List<PlanetCloseView> planets = planetRepository.getAllCloseViewByType(type);
                assertNotNull(planets);
                assertEquals(4, planets.size());
                PlanetCloseView planet = planets.get(0);
                assertNotNull(planet);

                log.info(
                    "Close View [Planet]: name={}, note={}",
                    planet.getName(),
                    planet.getNote()
                );

                List<PlanetMoonCloseView> moons = planet.getMoons();
                assertNotNull(moons);
                assertEquals(8, moons.size());
                PlanetMoonCloseView moon = moons.get(0);
                assertNotNull(moon);

                log.info(
                    "Close View [Moon]: name={}, note={}",
                    moon.getName(),
                    moon.getNote()
                );
            }

            @Test
            @DisplayName("Test: close view query - child")
            void closeViewChild() {
                // given
                var id = 1_005L;

                List<PlanetMoonCloseView> moons = planetMoonRepository.getAllCloseViewByPlanetId(id);
                assertNotNull(moons);
                assertEquals(8, moons.size());
                PlanetMoonCloseView moon = moons.get(0);
                assertNotNull(moon);

                log.info(
                    "Close View [Moon]: name={}, note={}",
                    moon.getName(),
                    moon.getNote()
                );
            }
        }

        @Nested
        @Tag("open-view")
        @Tag("projection")
        @DisplayName("Test: open view")
        class OpenViewTest {

            @Test
            @DisplayName("Test: open view query - parent")
            void openViewParent() {
                // given
                var type = PlanetType.GIANT;

                List<PlanetOpenView> planets = planetRepository.getAllOpenViewByType(type);
                assertNotNull(planets);
                assertEquals(4, planets.size());
                PlanetOpenView planet = planets.get(0);
                assertNotNull(planet);

                log.info(
                    "Close View [Planet]: description={}",
                    planet.getDescription()
                );

                List<PlanetMoonOpenView> moons = planet.getMoons();
                assertNotNull(moons);
                assertEquals(8, moons.size());
                PlanetMoonOpenView moon = moons.get(0);
                assertNotNull(moon);

                log.info(
                    "Close View [Moon]: description={}",
                    moon.getDescription()
                );
            }

            @Test
            @DisplayName("Test: open view query - child")
            void openViewChild() {
                // given
                var id = 1_005L;

                List<PlanetMoonOpenView> moons = planetMoonRepository.getAllOpenViewByPlanetId(id);
                assertNotNull(moons);
                assertEquals(8, moons.size());
                PlanetMoonOpenView moon = moons.get(0);
                assertNotNull(moon);

                log.info(
                    "Close View [Moon]: description={}",
                    moon.getDescription()
                );
            }
        }

        @Nested
        @Tag("class-view")
        @Tag("projection")
        @DisplayName("Test: class view")
        class ClassViewTest {

            @Test
            @DisplayName("Test: class view query - parent")
            void classViewParent() {
                // given
                var type = PlanetType.GIANT;

                List<PlanetClassView> planets = planetRepository.getAllClassViewByType(type);
                assertNotNull(planets);
                assertEquals(4, planets.size());
                PlanetClassView planet = planets.get(0);
                assertNotNull(planet);

                log.info(
                    "Class View [Planet]: name={}, note={}",
                    planet.getName(),
                    planet.getNote()
                );

                List<PlanetMoon> moons = planet.getMoons();
                assertNotNull(moons);
                assertEquals(0, moons.size());

                log.info(
                    "Collections cannot be used in class-based DTO projections"
                );
            }

            @Test
            @DisplayName("Test: class view query - child")
            void classViewChild() {
                // given
                var id = 1_005L;

                List<PlanetMoonClassView> moons = planetMoonRepository.getAllClassViewByPlanetId(id);
                assertNotNull(moons);
                assertEquals(8, moons.size());
                PlanetMoonClassView moon = moons.get(0);
                assertNotNull(moon);

                log.info(
                    "Class View [Moon]: name={}, note={}",
                    moon.getName(),
                    moon.getNote()
                );
            }
        }

        @Nested
        @Tag("dynamic-view")
        @Tag("projection")
        @DisplayName("Test: dynamic view")
        class DynamicViewTest {

            @Test
            @DisplayName("Test: dynamic view query - parent")
            void dynamicViewParent() {
                // given
                var type = PlanetType.GIANT;

                List<Planet> entityPlanets = planetRepository.getAllDynamicViewByType(type, Planet.class);
                List<PlanetCloseView> closePlanets = planetRepository.getAllDynamicViewByType(type, PlanetCloseView.class);
                List<PlanetOpenView> openPlanets = planetRepository.getAllDynamicViewByType(type, PlanetOpenView.class);
                List<PlanetClassView> classPlanets = planetRepository.getAllDynamicViewByType(type, PlanetClassView.class);

                assertNotNull(entityPlanets);
                assertNotNull(closePlanets);
                assertNotNull(openPlanets);
                assertNotNull(classPlanets);

                assertEquals(4, entityPlanets.size());
                assertEquals(4, closePlanets.size());
                assertEquals(4, openPlanets.size());
                assertEquals(4, classPlanets.size());

                Planet entityPlanet = entityPlanets.get(0);
                PlanetCloseView closePlanet = closePlanets.get(0);
                PlanetOpenView openPlanet = openPlanets.get(0);
                PlanetClassView classPlanet = classPlanets.get(0);

                assertNotNull(entityPlanet);
                assertNotNull(closePlanet);
                assertNotNull(openPlanet);
                assertNotNull(classPlanet);

                log.info("Entity [Planet]: name={}, note={}", entityPlanet.getName(), entityPlanet.getNote());
                log.info("Close View [Planet]: name={}, note={}", closePlanet.getName(), closePlanet.getNote());
                log.info("Open View [Planet]: description={}", openPlanet.getDescription());
                log.info("Class View [Planet]: name={}, note={}", classPlanet.getName(), classPlanet.getNote());
            }

            @Test
            @DisplayName("Test: dynamic view query - child")
            void dynamicViewChild() {
                // given
                var id = 1_005L;

                List<PlanetMoon> entityMoons = planetMoonRepository.getAllDynamicViewByPlanetId(id, PlanetMoon.class);
                List<PlanetMoonCloseView> closeMoons = planetMoonRepository.getAllDynamicViewByPlanetId(id, PlanetMoonCloseView.class);
                List<PlanetMoonOpenView> openMoons = planetMoonRepository.getAllDynamicViewByPlanetId(id, PlanetMoonOpenView.class);
                List<PlanetMoonClassView> classMoons = planetMoonRepository.getAllDynamicViewByPlanetId(id, PlanetMoonClassView.class);

                assertNotNull(entityMoons);
                assertNotNull(closeMoons);
                assertNotNull(openMoons);
                assertNotNull(classMoons);

                assertEquals(8, entityMoons.size());
                assertEquals(8, closeMoons.size());
                assertEquals(8, openMoons.size());
                assertEquals(8, classMoons.size());

                PlanetMoon entityMoon = entityMoons.get(0);
                PlanetMoonCloseView closeMoon = closeMoons.get(0);
                PlanetMoonOpenView openMoon = openMoons.get(0);
                PlanetMoonClassView classMoon = classMoons.get(0);

                assertNotNull(entityMoon);
                assertNotNull(closeMoon);
                assertNotNull(openMoon);
                assertNotNull(classMoon);

                log.info("Entity [Moon]: name={}, note={}", entityMoon.getName(), entityMoon.getNote());
                log.info("Close View [Moon]: name={}, note={}", closeMoon.getName(), closeMoon.getNote());
                log.info("Open View [Moon]: description={}", openMoon.getDescription());
                log.info("Class View [Moon]: name={}, note={}", classMoon.getName(), classMoon.getNote());
            }
        }

        @Nested
        @Tag("native-view")
        @Tag("projection")
        @DisplayName("Test: native view")
        class NativeViewTest {

            @Test
            @DisplayName("Test: native view query - parent")
            void nativeViewParent() {
                // given
                var type = PlanetType.GIANT;
                // when
                List<Object> nativePlanets = planetRepository.getAllNativeViewByType(type.name());
                // then
                assertNotNull(nativePlanets);
                assertEquals(4, nativePlanets.size());

                Object nativePlanet = nativePlanets.get(0);

                assertNotNull(nativePlanet);
                assertTrue(nativePlanet instanceof Object[]);

                Object[] planetInfo = (Object[]) nativePlanet;

                log.info(
                    "Native [Planet]: name={}, note={}",
                    planetInfo[0],
                    planetInfo[1]
                );
            }

            @Test
            @DisplayName("Test: native view query - child")
            void nativeViewChild() {
                // given
                var id = 1_005L;
                // when
                List<Object> nativeMoons = planetMoonRepository.getAllNativeViewByPlanetId(id);
                // then
                assertNotNull(nativeMoons);
                assertEquals(8, nativeMoons.size());

                Object nativeMoon = nativeMoons.get(0);

                assertNotNull(nativeMoon);
                assertTrue(nativeMoon instanceof Object[]);

                Object[] moonInfo = (Object[]) nativeMoon;

                log.info(
                    "Native [Moon]: name={}, note={}",
                    moonInfo[0],
                    moonInfo[1]
                );
            }
        }

        @Nested
        @Tag("jpql-view")
        @Tag("projection")
        @DisplayName("Test: jpql view")
        class QueryViewTest {

            @Test
            @DisplayName("Test: jpql view query - parent")
            void jpqlViewParent() {
                // given
                var type = PlanetType.GIANT;
                // when
                List<PlanetCloseView> queryPlanets = planetRepository.getAllQueryViewByType(type);
                // then
                assertNotNull(queryPlanets);
                assertEquals(4, queryPlanets.size());

                PlanetCloseView queryPlanet = queryPlanets.get(0);

                assertNotNull(queryPlanet);

                log.info(
                    "Query [Planet]: name={}, note={}",
                    queryPlanet.getName(),
                    queryPlanet.getNote()
                );
            }

            @Test
            @DisplayName("Test: jpql view query - child")
            void jpqlViewChild() {
                // given
                var id = 1_005L;
                // when
                List<PlanetMoonCloseView> queryMoons = planetMoonRepository.getAllQueryViewByPlanetId(id);
                // then
                assertNotNull(queryMoons);
                assertEquals(8, queryMoons.size());

                // single
                PlanetMoonCloseView queryMoon = queryMoons.get(0);

                assertNotNull(queryMoon);

                log.info(
                    "Query [Moon]: name={}, note={}",
                    queryMoon.getName(),
                    queryMoon.getNote()
                );
            }
        }

        @Nested
        @Tag("criteria-view")
        @Tag("projection")
        @DisplayName("Test: criteria view")
        class CriteriaViewTest {

            @Test
            @DisplayName("Test: criteria view query - parent")
            void criteriaViewParent() {
                // given
                var type = PlanetType.GIANT;
                // when
                List<PlanetClassView> criteriaPlanets = planetService.getAllCriteriaViewByType(type);
                // then
                assertNotNull(criteriaPlanets);
                assertEquals(4, criteriaPlanets.size());

                PlanetClassView criteriaPlanet = criteriaPlanets.get(0);

                assertNotNull(criteriaPlanet);

                log.info(
                    "Criteria [Planet]: name={}, note={}",
                    criteriaPlanet.getName(),
                    criteriaPlanet.getNote()
                );
            }

            @Test
            @DisplayName("Test: criteria view query - child")
            void criteriaViewChild() {
                // given
                var id = 1_005L;
                // when
                List<PlanetMoonClassView> criteriaMoons = planetMoonService.getAllCriteriaViewByPlanetId(id);
                // then
                assertNotNull(criteriaMoons);
                assertEquals(8, criteriaMoons.size());

                // single
                PlanetMoonClassView criteriaMoon = criteriaMoons.get(0);

                assertNotNull(criteriaMoon);

                log.info(
                    "Criteria [Moon]: name={}, note={}",
                    criteriaMoon.getName(),
                    criteriaMoon.getNote()
                );
            }
        }
    }
}
