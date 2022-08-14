package com.witalis.jkit.spring.data.jpa.bidirectional;

import com.witalis.jkit.spring.data.jpa.bidirectional.model.*;
import com.witalis.jkit.spring.data.jpa.bidirectional.repository.PlanetAtmosphereRepository;
import com.witalis.jkit.spring.data.jpa.bidirectional.repository.PlanetAttributeRepository;
import com.witalis.jkit.spring.data.jpa.bidirectional.repository.PlanetMoonRepository;
import com.witalis.jkit.spring.data.jpa.bidirectional.repository.PlanetRepository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Tag("bidirectional")
@DisplayName("Test: bidirectional")
@DataJpaTest
class JkitDataJpaBidirectionalTests {
    private final PlanetRepository planetRepository;
    private final PlanetAttributeRepository planetAttributeRepository;
    private final PlanetMoonRepository planetMoonRepository;
    private final PlanetAtmosphereRepository planetAtmosphereRepository;

    @Autowired
    public JkitDataJpaBidirectionalTests(
        final PlanetRepository planetRepository,
        final PlanetAttributeRepository planetAttributeRepository,
        final PlanetMoonRepository planetMoonRepository,
        final PlanetAtmosphereRepository planetAtmosphereRepository
    ) {
        this.planetRepository = planetRepository;
        this.planetAttributeRepository = planetAttributeRepository;
        this.planetMoonRepository = planetMoonRepository;
        this.planetAtmosphereRepository = planetAtmosphereRepository;
    }

    @BeforeAll
    public static void initialization() {
        log.info("Application tests [jpa/bidirectional]: In Action...");
    }

    @Nested
    @Tag("bidirectional")
    @Tag("one-to-one")
    @DisplayName("Test: one-to-one")
    class OneToOneTest {

        @Test
        @DisplayName("Test: query data")
        void possibleToFindPlanetAndAttributes() {
            final var ID = 1_001L;

            Optional<Planet> optionalPlanet =
                planetRepository.findById(ID);
            Assertions.assertTrue(optionalPlanet.isPresent());

            Optional<PlanetAttribute> optionalPlanetAttributes =
                planetAttributeRepository.findById(ID);
            Assertions.assertTrue(optionalPlanetAttributes.isPresent());
        }

        @Test
        @DisplayName("Test: create data")
        void possibleToAddPlanetAndAttributes() {
            var count = planetRepository.count();
            var attrs = planetAttributeRepository.count();

            assertEquals(count, attrs);

            Planet planet = new Planet();
            planet.setName("Test");
            planet.setType(PlanetType.GIANT);
            planet.setSign("+");
            planet.setNote("Virtual");

            PlanetAttribute attribute = new PlanetAttribute();
            attribute.setPlanet(planet);
            attribute.setDiameter(BigDecimal.ONE);
            attribute.setMass(BigDecimal.ONE);
            attribute.setMoons(BigInteger.TEN);
            attribute.setRings(Boolean.FALSE);

            planet.setAttribute(attribute);

            planetRepository.save(planet);

            assertEquals(count + 1, planetRepository.count());
            assertEquals(attrs + 1, planetAttributeRepository.count());
        }

        @Test
        @DisplayName("Test: remove data")
        void possibleToRemovePlanetAndAttributes() {
            final var ID = 1_001L;

            var count = planetRepository.count();
            var attrs = planetAttributeRepository.count();

            var planet = planetRepository.getReferenceById(ID);
            assertNotNull(planet);
            var attribute = planetAttributeRepository.getReferenceById(ID);
            assertNotNull(attribute);

            planetRepository.deleteById(ID);
            planetRepository.flush();

            assertEquals(count - 1, planetRepository.count());
            assertEquals(attrs - 1, planetAttributeRepository.count());
        }
    }

    @Nested
    @Tag("bidirectional")
    @Tag("one-to-many")
    @DisplayName("Test: one-to-many")
    class OneToManyTest {

        @Test
        @DisplayName("Test: query data")
        void possibleToFindPlanetAndMoons() {
            final var ID = 1_005L;

            Optional<Planet> optionalPlanet =
                planetRepository.findById(ID);

            assertTrue(optionalPlanet.isPresent());
            assertNotNull(optionalPlanet.get().getMoons());
            assertTrue(optionalPlanet.get().getMoons().size() > 0);

            List<PlanetMoon> planetMoons =
                planetMoonRepository.getAllByPlanetId(ID);

            assertNotNull(planetMoons);
            assertEquals(new ArrayList<>(optionalPlanet.get().getMoons()), planetMoons);
        }

        @Test
        @DisplayName("Test: create data")
        void possibleToAddPlanetAndMoons() {
            var count = planetRepository.count();
            var moons = planetMoonRepository.count();

            assertTrue(count > 0);
            assertTrue(moons > 0);

            Planet planet = new Planet();
            planet.setName("Test");
            planet.setType(PlanetType.GIANT);
            planet.setSign("+");
            planet.setNote("Virtual");

            PlanetMoon planetMoon = new PlanetMoon();
            planetMoon.setName("Mock");
            planetMoon.setDistance(BigDecimal.valueOf(100_000));
            planetMoon.setRadius(BigDecimal.valueOf(100));
            planetMoon.setNote("Test I");

            planet.addMoon(planetMoon);

            var savedPlanet = planetRepository.save(planet);

            assertEquals(count + 1, planetRepository.count());
            assertEquals(moons + 1, planetMoonRepository.count());

            assertNotNull(savedPlanet);
            assertNotNull(savedPlanet.getMoons());
            assertEquals(1, savedPlanet.getMoons().size());
            assertEquals(planetMoon, savedPlanet.getMoons().get(0));
        }

        @Test
        @DisplayName("Test: remove data")
        void possibleToRemovePlanetAndMoons() {
            final var ID = 1_005L;

            var count = planetRepository.count();
            var moons = planetMoonRepository.count();

            var planet = planetRepository.getReferenceById(ID);
            assertNotNull(planet);

            var planetMoon = planet.getMoons().get(0);

            planetMoonRepository.delete(planetMoon);
            planetMoonRepository.flush();

            assertEquals(moons, planetMoonRepository.count());

            planet.removeMoon(planetMoon);
            planetRepository.flush();

            assertEquals(moons - 1, planetMoonRepository.count());

            planetRepository.deleteById(ID);
            planetRepository.flush();

            assertEquals(count - 1, planetRepository.count());
            assertEquals(moons - 8, planetMoonRepository.count());
        }
    }

    @Nested
    @Tag("bidirectional")
    @Tag("many-to-many")
    @DisplayName("Test: many-to-many")
    class ManyToManyTest {

        @Test
        @DisplayName("Test: query data")
        void possibleToFindPlanetAndAtmospheres() {
            final var ID = 1_005L;

            Optional<Planet> optionalPlanet =
                planetRepository.findById(ID);

            assertTrue(optionalPlanet.isPresent());
            assertNotNull(optionalPlanet.get().getAtmospheres());
            assertTrue(optionalPlanet.get().getAtmospheres().size() > 0);

            List<PlanetAtmosphere> planetAtmospheres =
                planetAtmosphereRepository.findAllByPlanetId(ID);

            assertNotNull(planetAtmospheres);
            assertTrue(planetAtmospheres.size() > 0);
        }

        @Test
        @DisplayName("Test: create data")
        void possibleToAddPlanetAndAtmospheres() {
            var count = planetRepository.count();
            var atmos = planetAtmosphereRepository.count();

            assertTrue(count > 0);
            assertTrue(atmos > 0);

            Planet planet = new Planet();
            planet.setName("Test");
            planet.setType(PlanetType.GIANT);
            planet.setSign("+");
            planet.setNote("Virtual");

            PlanetAtmosphere planetAtmosphere = new PlanetAtmosphere();
            planetAtmosphere.setName("Xe");

            planet.addAtmosphere(planetAtmosphere);

            var savedPlanet = planetRepository.save(planet);

            assertEquals(count + 1, planetRepository.count());
            assertEquals(atmos + 1, planetAtmosphereRepository.count());

            assertNotNull(savedPlanet);
            assertNotNull(savedPlanet.getAtmospheres());
            assertEquals(1, savedPlanet.getAtmospheres().size());
            assertEquals(planetAtmosphere, savedPlanet.getAtmospheres().stream().toList().get(0));
        }

        @Test
        @DisplayName("Test: remove data")
        void possibleToRemoveAtmosphereAndAtmospheres() {
            final var ID = 1_005L;

            var count = planetRepository.count();
            var atmos = planetAtmosphereRepository.count();

            var planet = planetRepository.getReferenceById(ID);
            assertNotNull(planet);

            var planetAtmosphere = planet.getAtmospheres().stream().toList().get(0);

            planetAtmosphereRepository.delete(planetAtmosphere);
            planetAtmosphereRepository.flush();

            assertEquals(atmos, planetAtmosphereRepository.count());

            planet.removeAtmosphere(planetAtmosphere);
            planetRepository.flush();

            assertEquals(atmos, planetAtmosphereRepository.count());

            planetRepository.deleteById(ID);
            planetRepository.flush();

            assertEquals(count - 1, planetRepository.count());
            assertEquals(atmos, planetAtmosphereRepository.count());
        }
    }
}
