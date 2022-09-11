package com.witalis.jkit.jooq;

import com.witalis.jkit.jooq.model.tables.daos.*;
import com.witalis.jkit.jooq.model.tables.pojos.PlanetSystem;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static com.witalis.jkit.jooq.model.tables.PlanetSystem.*;
import static com.witalis.jkit.jooq.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Tag("jooq")
@DisplayName("Test: jooq dao")
@JooqTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Disabled("Test: database should be up")
class JkitJooqDaoTests {
    private PlanetSystemDao planetSystemDao;
    private PlanetDao planetDao;
    private PlanetAttributeDao planetAttributeDao;
    private PlanetMoonDao planetMoonDao;
    private PlanetAtmosphereDao planetAtmosphereDao;
    private PlanetAtmosphereMapDao planetAtmosphereMapDao;

    @Autowired
    private DSLContext context;

    @BeforeAll
    public static void initialization() {
        log.info("Application tests [jooq]: In Action...");
    }

    @BeforeEach
    public void begin() {
        // definition
        planetSystemDao = initPlanetSystemDao(context);
        planetDao = initPlanetDao(context);
        planetAttributeDao = initPlanetAttributeDao(context);
        planetMoonDao = initPlanetMoonDao(context);
        planetAtmosphereDao = initPlanetAtmosphereDao(context);
        planetAtmosphereMapDao = initPlanetAtmosphereMapDao(context);
        // cleaning
        planetAtmosphereMapDao.delete(planetAtmosphereMapDao.findAll());
        planetAtmosphereDao.delete(planetAtmosphereDao.findAll());
        planetMoonDao.delete(planetMoonDao.findAll());
        planetAttributeDao.delete(planetAttributeDao.findAll());
        planetDao.delete(planetDao.findAll());
        planetSystemDao.delete(planetSystemDao.findAll());
    }

    @Nested
    @Tag("jooq")
    @DisplayName("Test: context")
    class ContextTest {

        @Test
        @DisplayName("Test: context loading")
        void contextLoads() {

            assertNotNull(context);
        }

        @Test
        @DisplayName("Test: dao loading")
        void daoLoads() {

            assertNotNull(planetSystemDao);
        }
    }

    @Nested
    @Tag("jooq")
    @DisplayName("Test: crud")
    class CRUDTest {

        @Nested
        @Tag("create")
        @DisplayName("Test: create data")
        class CreateData {

            @Test
            @DisplayName("Test: create one")
            void createOne() {

                long count = planetSystemDao.count();
                log.info("Count of planet systems: before -> {}", count);

                assertEquals(0, count);

                PlanetSystem planetSystem = initPlanetSystem();
                planetSystemDao.insert(planetSystem);
                PlanetSystem fetchedPlanetSystem = planetSystemDao.fetchOneById(1L);
                log.info("Planet system: {}", fetchedPlanetSystem);

                assertNotSame(planetSystem, fetchedPlanetSystem);
                assertEquals(planetSystem, fetchedPlanetSystem);

                count = planetSystemDao.count();
                log.info("Count of planet systems: after -> {}", count);

                assertEquals(1, count);
            }

            @Test
            @DisplayName("Test: create all")
            void createAll() {

                long count = planetSystemDao.count();
                log.info("Count of planet systems: before -> {}", count);

                assertEquals(0, count);

                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);
                List<PlanetSystem> fetchedPlanetSystems = planetSystemDao.findAll();
                log.info("Planet systems: {}", fetchedPlanetSystems);

                assertNotSame(planetSystems, fetchedPlanetSystems);
                assertEquals(planetSystems, fetchedPlanetSystems);
                assertEquals(planetSystems.size(), fetchedPlanetSystems.size());

                count = planetSystemDao.count();
                log.info("Count of planet systems: after -> {}", count);

                assertEquals(planetSystems.size(), count);
            }
        }

        @Nested
        @Tag("read")
        @DisplayName("Test: read data")
        class ReadData {

            @Test
            @DisplayName("Test: read count")
            void readCount() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                long count = planetSystemDao.count();
                log.info("Count of planet systems: {}", count);

                assertEquals(planetSystems.size(), count);
            }

            @Test
            @DisplayName("Test: read identifier")
            void readId() {
                PlanetSystem planetSystem = initPlanetSystem();
                planetSystemDao.insert(planetSystem);

                long id = planetSystemDao.getId(planetSystem);
                log.info("ID of planet system: {}", id);

                assertEquals(planetSystem.getId(), id);
            }

            @Test
            @DisplayName("Test: read one")
            void readOne() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                long id = 1L;
                PlanetSystem planetSystem = planetSystemDao.fetchOneById(id);
                log.info("Planet system by ID '{}': {}", id, planetSystem);

                assertNotNull(planetSystem);
                assertEquals(id, planetSystem.getId());
            }

            @Test
            @DisplayName("Test: read all")
            void readAll() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                List<PlanetSystem> foundPlanetSystems = planetSystemDao.findAll();
                log.info("Planet systems: {}", foundPlanetSystems);

                assertNotNull(planetSystems);
                assertNotNull(foundPlanetSystems);
                assertNotSame(planetSystems, foundPlanetSystems);
                assertEquals(planetSystems, foundPlanetSystems);
                assertEquals(planetSystems.size(), foundPlanetSystems.size());
            }

            @Test
            @DisplayName("Test: read set")
            void readSet() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                List<Integer> values = List.of(2,3,4);
                List<PlanetSystem> fetchedPlanetSystems = planetSystemDao.fetch(PLANET_SYSTEM.STARS, values);
                log.info("Planet systems: {}", fetchedPlanetSystems);

                assertNotNull(planetSystems);
                assertNotNull(fetchedPlanetSystems);
                assertNotSame(planetSystems, fetchedPlanetSystems);
                assertNotEquals(planetSystems, fetchedPlanetSystems);
                assertNotEquals(planetSystems.size(), fetchedPlanetSystems.size());
            }

            @Test
            @DisplayName("Test: read condition")
            void readCondition() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                String name = "Solar System";
                log.info("Planet systems, by name '{}': {}", name, planetSystemDao.fetchByName(name));

                int lowerBound = 3;
                int upperBound = 10;
                log.info("Planet systems, range of stars [{}, {}]: {}", lowerBound, upperBound, planetSystemDao.fetchRangeOfStars(lowerBound, upperBound));

                long id = 100L;
                log.info("Planet systems, optional by id '{}': {}", id, planetSystemDao.findOptionalById(id));
            }
        }

        @Nested
        @Tag("update")
        @DisplayName("Test: update data")
        class UpdateData {

            @Test
            @DisplayName("Test: update one")
            void updateOne() {
                PlanetSystem planetSystem = initPlanetSystem();
                planetSystemDao.insert(planetSystem);

                PlanetSystem fetchedPlanetSystem = planetSystemDao.fetchOneById(1L);
                log.info("Planet system, before: {}", fetchedPlanetSystem);

                fetchedPlanetSystem.setAge(5_000_000_000L);
                fetchedPlanetSystem.setName("So Away Galaxy");
                fetchedPlanetSystem.setStars(2);
                fetchedPlanetSystem.setNote("The nomadic galaxy");

                planetSystemDao.update(fetchedPlanetSystem);

                PlanetSystem updatedPlanetSystem = planetSystemDao.findById(1L);
                log.info("Planet system, after: {}", updatedPlanetSystem);

                assertNotNull(planetSystem);
                assertNotNull(updatedPlanetSystem);
                assertNotSame(planetSystem, updatedPlanetSystem);
                assertNotEquals(planetSystem, updatedPlanetSystem);
            }

            @Test
            @DisplayName("Test: update all")
            void updateAll() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                List<PlanetSystem> foundPlanetSystems = planetSystemDao.findAll();
                log.info("Planet systems, before: {}", foundPlanetSystems);
                foundPlanetSystems.forEach(system -> system.setNote("Secure definition..."));

                planetSystemDao.update(foundPlanetSystems);

                List<PlanetSystem> updatedPlanetSystems = planetSystemDao.findAll();
                log.info("Planet systems, after: {}", updatedPlanetSystems);

                assertNotNull(planetSystems);
                assertNotNull(updatedPlanetSystems);
                assertNotSame(planetSystems, updatedPlanetSystems);
                assertNotEquals(planetSystems, updatedPlanetSystems);
            }

            @Test
            @DisplayName("Test: update set")
            void updateSet() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                List<PlanetSystem> foundPlanetSystems = planetSystemDao.findAll();
                log.info("Planet systems, before: {}", foundPlanetSystems);

                int index = 0;
                foundPlanetSystems.remove(index);
                foundPlanetSystems.forEach(system -> system.setNote("Unreal Galaxy"));

                planetSystemDao.update(foundPlanetSystems);

                List<PlanetSystem> updatedPlanetSystems = planetSystemDao.findAll();
                log.info("Planet systems, after: {}", updatedPlanetSystems);

                assertNotNull(planetSystems);
                assertNotNull(updatedPlanetSystems);
                assertNotSame(planetSystems, updatedPlanetSystems);
                assertNotEquals(planetSystems, updatedPlanetSystems);
            }
        }

        @Nested
        @Tag("delete")
        @DisplayName("Test: delete data")
        class DeleteData {

            @Test
            @DisplayName("Test: delete one")
            void deleteOne() {
                log.info("Planet systems, before: {}", planetSystemDao.count());

                PlanetSystem planetSystem = initPlanetSystem();
                planetSystemDao.insert(planetSystem);

                log.info("Planet systems, process: {}", planetSystemDao.count());

                planetSystemDao.delete(planetSystem);

                log.info("Planet systems, after: {}", planetSystemDao.count());
            }

            @Test
            @DisplayName("Test: delete id")
            void deleteId() {
                log.info("Planet systems, before: {}", planetSystemDao.count());

                PlanetSystem planetSystem = initPlanetSystem();
                planetSystemDao.insert(planetSystem);

                log.info("Planet systems, process: {}", planetSystemDao.count());

                long id = planetSystem.getId();
                planetSystemDao.deleteById(id);

                log.info("Planet systems, after: {}", planetSystemDao.count());
            }

            @Test
            @DisplayName("Test: delete all")
            void deleteAll() {
                log.info("Planet systems, before: {}", planetSystemDao.count());

                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                log.info("Planet systems, process: {}", planetSystemDao.count());

                planetSystemDao.delete(planetSystems);

                log.info("Planet systems, after: {}", planetSystemDao.count());
            }

            @Test
            @DisplayName("Test: delete ids")
            void deleteIds() {
                log.info("Planet systems, before: {}", planetSystemDao.count());

                List<PlanetSystem> planetSystems = initPlanetSystems();
                planetSystemDao.insert(planetSystems);

                log.info("Planet systems, process: {}", planetSystemDao.count());

                List<Long> ids = List.of(1L, 2L, 3L);
                planetSystemDao.delete(planetSystems);

                log.info("Planet systems, after: {}", planetSystemDao.count());
            }
        }
    }

    @Rollback
    @AfterEach
    public void end() {
    }

    @AfterAll
    public static void finalization() {
        log.info("Application tests [jooq]: Shutdown...");
    }
}
