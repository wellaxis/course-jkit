package com.witalis.jkit.spring.data.jpa.nplusone;

import com.witalis.jkit.spring.data.jpa.nplusone.model.Planet;
import com.witalis.jkit.spring.data.jpa.nplusone.model.PlanetMoon;
import com.witalis.jkit.spring.data.jpa.nplusone.repository.PlanetMoonRepository;
import com.witalis.jkit.spring.data.jpa.nplusone.repository.PlanetRepository;
import com.witalis.jkit.spring.data.jpa.nplusone.service.PlanetMoonService;
import com.witalis.jkit.spring.data.jpa.nplusone.service.PlanetService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@ComponentScan
class JkitDataJpaNPlusOneTests {
    private final PlanetRepository planetRepository;
    private final PlanetMoonRepository planetMoonRepository;
    private final PlanetService planetService;
    private final PlanetMoonService planetMoonService;

    @Autowired
    public JkitDataJpaNPlusOneTests(
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
        log.info("Application tests [jpa/n-plus-one]: In Action...");
    }

    @Nested
    @Tag("n-plus-one-problem")
    @DisplayName("Test: N+1 problem")
    class NPlus1ProblemTest {

        @Nested
        @Tag("n-plus-one-problem")
        @Tag("parent-side")
        @DisplayName("Test: parent N+1 problem")
        class ParentEntityTest {

            @Test
            @DisplayName("Test: problem simulation")
            void problemParent() {
                List<Planet> planets = planetRepository.findAllBy();
                // from PLANET p
                // from PLANET_MOON m where m.PLANET_ID=?
                assertNotNull(planets);
                traversePlanets(planets);
            }

            @Test
            @DisplayName("Test: solution #1 - join fetch")
            void solutionJoinFetch() {
                List<Planet> planets = planetRepository.findAllUseOfJoinFetchBy();
                // from PLANET p inner join PLANET_MOON m on p.ID=m.PLANET_ID
                assertNotNull(planets);
                traversePlanets(planets);
            }

            @Test
            @DisplayName("Test: solution #2 - entity graph")
            void solutionEntityGraph() {
                List<Planet> planets = planetRepository.findAllUseEntityGraphBy();
                // from PLANET p left outer join PLANET_MOON m on p.ID=m.PLANET_ID
                assertNotNull(planets);
                traversePlanets(planets);
            }

            @Test
            @DisplayName("Test: solution #3 - criteria api")
            void solutionCriteriaApi() {
                List<Planet> planets = planetService.findAllUseCriteriaApiBy();
                // from PLANET p left outer join PLANET_MOON m on p.ID=m.PLANET_ID
                assertNotNull(planets);
                traversePlanets(planets);
            }

            /** Traverse via planets, to check lazy initialization */
            private static void traversePlanets(List<Planet> planets) {
                var moons = planets.stream()
                    .filter(Objects::nonNull)
                    .flatMap(p -> p.getMoons().stream())
                    .peek(PlanetMoon::getName)
                    .toList();
            }
        }

        @Nested
        @Tag("n-plus-one-problem")
        @Tag("child-side")
        @DisplayName("Test: child N+1 problem")
        class ChildEntityTest {

            @Test
            @DisplayName("Test: problem simulation")
            void problemChild() {
                List<PlanetMoon> moons = planetMoonRepository.findAllBy();
                // from PLANET_MOON m
                // from PLANET p where p.ID=?
                assertNotNull(moons);
                traverseMoons(moons);
            }

            @Test
            @DisplayName("Test: solution #1: join fetch")
            void solutionJoinFetch() {
                List<PlanetMoon> moons = planetMoonRepository.findAllUseOfJoinFetchBy();
                // from PLANET_MOON m left outer join PLANET p on m.PLANET_ID=p.ID
                assertNotNull(moons);
                traverseMoons(moons);
            }

            @Test
            @DisplayName("Test: solution #2 - entity graph")
            void solutionEntityGraph() {
                List<PlanetMoon> moons = planetMoonRepository.findAllUseEntityGraphBy();
                // from PLANET_MOON m left outer join PLANET p on m.PLANET_ID=p.ID
                assertNotNull(moons);
                traverseMoons(moons);
            }

            @Test
            @DisplayName("Test: solution #3 - criteria api")
            void solutionCriteriaApi() {
                List<PlanetMoon> moons = planetMoonService.findAllUseCriteriaApiBy();
                // from PLANET_MOON m left outer join PLANET p on m.PLANET_ID=p.ID
                assertNotNull(moons);
                traverseMoons(moons);
            }

            /** Traverse via moons, to check lazy initialization */
            private static void traverseMoons(List<PlanetMoon> moons) {
                var planets = moons.stream()
                    .filter(Objects::nonNull)
                    .map(PlanetMoon::getPlanet)
                    .peek(Planet::getName)
                    .toList();
            }
        }
    }
}
