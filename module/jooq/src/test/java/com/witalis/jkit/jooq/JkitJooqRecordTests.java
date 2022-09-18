package com.witalis.jkit.jooq;

import com.witalis.jkit.jooq.model.tables.pojos.PlanetSystem;
import com.witalis.jkit.jooq.model.tables.records.PlanetSystemRecord;

import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.impl.DefaultDSLContext;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.witalis.jkit.jooq.model.tables.Planet.PLANET;
import static com.witalis.jkit.jooq.model.tables.PlanetAtmosphere.PLANET_ATMOSPHERE;
import static com.witalis.jkit.jooq.model.tables.PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP;
import static com.witalis.jkit.jooq.model.tables.PlanetAttribute.PLANET_ATTRIBUTE;
import static com.witalis.jkit.jooq.model.tables.PlanetMoon.PLANET_MOON;
import static com.witalis.jkit.jooq.model.tables.PlanetSystem.PLANET_SYSTEM;
import static com.witalis.jkit.jooq.utils.JooqUtils.*;
import static com.witalis.jkit.jooq.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Tag("jooq")
@DisplayName("Test: jooq record")
@JooqTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class JkitJooqRecordTests {
    private DSLContext context;

    @Autowired
    private DSLContext dslContext;

    @BeforeAll
    public static void initialization() {
        log.info("Application tests [jooq]: In Action...");
    }

    @BeforeEach
    public void begin() {
        context = new DefaultDSLContext(initConfiguration(dslContext));
        // cleaning
        delete(context, PLANET_ATMOSPHERE_MAP);
        delete(context, PLANET_ATMOSPHERE);
        delete(context, PLANET_MOON);
        delete(context, PLANET_ATTRIBUTE);
        delete(context, PLANET);
        delete(context, PLANET_SYSTEM);
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
        @DisplayName("Test: record loading")
        void daoLoads() {

            assertNotNull(PLANET_SYSTEM);
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
                long count = count(context, PLANET_SYSTEM);
                log.info("Count of planet systems: before -> {}", count);

                assertEquals(0, count);

                PlanetSystem planetSystem = initPlanetSystem();

                PlanetSystemRecord planetSystemRecord = context.newRecord(PLANET_SYSTEM, planetSystem);
                insert(planetSystemRecord);

                PlanetSystemRecord fetchedPlanetSystemRecord = getOne(context, PLANET_SYSTEM, PLANET_SYSTEM.ID.eq(1L));
                log.info("Planet system:\n {}", fetchedPlanetSystemRecord);

                assertNotSame(planetSystemRecord, fetchedPlanetSystemRecord);
                assertEquals(planetSystemRecord, fetchedPlanetSystemRecord);

                count = count(context, PLANET_SYSTEM);
                log.info("Count of planet systems: after -> {}", count);

                assertEquals(1, count);
            }

            @Test
            @DisplayName("Test: create all")
            void createAll() {
                long count = count(context, PLANET_SYSTEM);
                log.info("Count of planet systems: before -> {}", count);

                assertEquals(0, count);

                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);
                Result<PlanetSystemRecord> fetchedPlanetSystemRecords = getAll(context, PLANET_SYSTEM);
                log.info("Planet systems:\n {}", fetchedPlanetSystemRecords);

                assertNotSame(planetSystemRecords, fetchedPlanetSystemRecords);
                assertEquals(planetSystemRecords, fetchedPlanetSystemRecords);
                assertEquals(planetSystemRecords.size(), fetchedPlanetSystemRecords.size());

                count = count(context, PLANET_SYSTEM);
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
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                long count = count(context, PLANET_SYSTEM);
                log.info("Count of planet systems: {}", count);

                assertEquals(planetSystems.size(), count);
            }

            @Test
            @DisplayName("Test: read one")
            void readOne() {
                PlanetSystem planetSystem = initPlanetSystem();
                PlanetSystemRecord planetSystemRecord = context.newRecord(PLANET_SYSTEM, planetSystem);

                insert(planetSystemRecord);

                long id = 1L;
                Condition condition = PLANET_SYSTEM.ID.eq(id);
                PlanetSystemRecord fetchedPlanetSystemRecord = getOne(context, PLANET_SYSTEM, condition);
                log.info("Planet system by ID '{}':\n {}", id, fetchedPlanetSystemRecord);

                assertNotNull(planetSystem);
                assertNotNull(fetchedPlanetSystemRecord);
                assertEquals(id, planetSystem.getId());
                assertEquals(id, fetchedPlanetSystemRecord.getId());
            }

            @Test
            @DisplayName("Test: read all")
            void readAll() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                Result<PlanetSystemRecord> fetchedPlanetSystemRecords = getAll(context, PLANET_SYSTEM);
                log.info("Planet systems:\n {}", fetchedPlanetSystemRecords);

                assertNotNull(planetSystemRecords);
                assertNotNull(fetchedPlanetSystemRecords);
                assertNotSame(planetSystemRecords, fetchedPlanetSystemRecords);
                assertEquals(planetSystemRecords, fetchedPlanetSystemRecords);
                assertEquals(planetSystems.size(), fetchedPlanetSystemRecords.size());
            }

            @Test
            @DisplayName("Test: read set")
            void readSet() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                List<Integer> values = List.of(2, 3, 4);
                Condition condition = PLANET_SYSTEM.STARS.in(values);
                Result<PlanetSystemRecord> fetchedPlanetSystemRecords = getSet(context, PLANET_SYSTEM, condition);
                log.info("Planet systems:\n {}", fetchedPlanetSystemRecords);

                assertNotNull(planetSystemRecords);
                assertNotNull(fetchedPlanetSystemRecords);
                assertNotSame(planetSystemRecords, fetchedPlanetSystemRecords);
                assertNotEquals(planetSystemRecords, fetchedPlanetSystemRecords);
                assertNotEquals(planetSystems.size(), fetchedPlanetSystemRecords.size());
            }

            @Test
            @DisplayName("Test: read condition")
            void readCondition() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                long id = 1L;
                PlanetSystemRecord planetSystemRecord = getById(context, PLANET_SYSTEM, PLANET_SYSTEM.ID, id);
                log.info("Planet systems, by ID '{}':\n {}", id, planetSystemRecord);

                String description = "solar system";
                planetSystemRecord = getOne(context, PLANET_SYSTEM, PLANET_SYSTEM.NAME.equalIgnoreCase(description));
                log.info("Planet systems, by name '{}':\n {}", description, planetSystemRecord);

                int lowerBound = 3;
                int upperBound = 10;
                Result<PlanetSystemRecord> fetchedPlanetSystemRecords = getSet(context, PLANET_SYSTEM, PLANET_SYSTEM.STARS.between(lowerBound, upperBound));
                log.info("Planet systems, range of stars [{}, {}]:\n {}", lowerBound, upperBound, fetchedPlanetSystemRecords);

                long unrealId = 100L;
                Optional<PlanetSystemRecord> optionalPlanetSystemRecord = getOptional(context, PLANET_SYSTEM, PLANET_SYSTEM.ID.eq(unrealId));
                log.info("Planet systems, optional by id '{}':\n {}", unrealId, optionalPlanetSystemRecord);

                // view
                SelectFieldOrAsterisk[] fields = new SelectFieldOrAsterisk[] {
                    PLANET_SYSTEM.NAME,
                    PLANET_SYSTEM.STARS,
                    PLANET_SYSTEM.LOCATION
                };
                Result<PlanetSystemRecord> viewPlanetSystemRecords = getView(context, PLANET_SYSTEM, fields);
                log.info("Planet systems, view:\n {}", viewPlanetSystemRecords);
            }
        }

        @Nested
        @Tag("update")
        @DisplayName("Test: update data")
        class UpdateData {

            @Test
            @DisplayName("Test: update one")
            void updateOne() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                long id = 1L;

                PlanetSystemRecord fetchedPlanetSystemRecord = getOne(context, PLANET_SYSTEM, PLANET_SYSTEM.ID.eq(id));
                log.info("Planet system, before:\n {}", fetchedPlanetSystemRecord);

                fetchedPlanetSystemRecord.setAge(5_000_000_000L);
                fetchedPlanetSystemRecord.setName("So Away Galaxy");
                fetchedPlanetSystemRecord.setStars(2);
                fetchedPlanetSystemRecord.setNote("The nomadic galaxy");

                update(fetchedPlanetSystemRecord);

                PlanetSystemRecord updatedPlanetSystemRecord = getOne(context, PLANET_SYSTEM, PLANET_SYSTEM.ID.eq(id));
                log.info("Planet system, after:\n {}", updatedPlanetSystemRecord);

                assertNotNull(fetchedPlanetSystemRecord);
                assertNotNull(updatedPlanetSystemRecord);
                assertNotSame(fetchedPlanetSystemRecord, updatedPlanetSystemRecord);
                assertEquals(fetchedPlanetSystemRecord, updatedPlanetSystemRecord);
            }

            @Test
            @DisplayName("Test: update all")
            void updateAll() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                Result<PlanetSystemRecord> fetchedPlanetSystemRecords = getAll(context, PLANET_SYSTEM);
                log.info("Planet systems, before:\n {}", fetchedPlanetSystemRecords);

                String note = "Undefined message..";
                update(context, PLANET_SYSTEM, Map.of(PLANET_SYSTEM.NOTE, note));

                Result<PlanetSystemRecord> updatedPlanetSystems = getAll(context, PLANET_SYSTEM);
                log.info("Planet systems, after:\n {}", updatedPlanetSystems);

                assertNotNull(fetchedPlanetSystemRecords);
                assertNotNull(updatedPlanetSystems);
                assertNotSame(fetchedPlanetSystemRecords, updatedPlanetSystems);
                assertNotEquals(fetchedPlanetSystemRecords, updatedPlanetSystems);
            }

            @Test
            @DisplayName("Test: update set")
            void updateSet() {
                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                Result<PlanetSystemRecord> fetchedPlanetSystemRecords = getAll(context, PLANET_SYSTEM);
                log.info("Planet systems, before:\n {}", fetchedPlanetSystemRecords);

                Map<Field<?>, Object> fields = new HashMap<>();
                fields.put(PLANET_SYSTEM.LOCATION, "Far away galaxy");
                fields.put(PLANET_SYSTEM.NOTE, "Several stars system");

                int minValue = 2;
                int maxValue = 10;
                update(context, PLANET_SYSTEM, fields, PLANET_SYSTEM.STARS.between(minValue, maxValue));

                Result<PlanetSystemRecord> updatedPlanetSystems = getAll(context, PLANET_SYSTEM);
                log.info("Planet systems, after:\n {}", updatedPlanetSystems);

                assertNotNull(fetchedPlanetSystemRecords);
                assertNotNull(updatedPlanetSystems);
                assertNotSame(fetchedPlanetSystemRecords, updatedPlanetSystems);
                assertNotEquals(fetchedPlanetSystemRecords, updatedPlanetSystems);
            }
        }

        @Nested
        @Tag("delete")
        @DisplayName("Test: delete data")
        class DeleteData {

            @Test
            @DisplayName("Test: delete one")
            void deleteOne() {
                log.info("Planet systems, before: {}", count(context, PLANET_SYSTEM));

                PlanetSystem planetSystem = initPlanetSystem();
                PlanetSystemRecord planetSystemRecord = context.newRecord(PLANET_SYSTEM, planetSystem);

                insert(planetSystemRecord);

                log.info("Planet systems, process: {}", count(context, PLANET_SYSTEM));

                PlanetSystemRecord fetchedPlanetSystemRecord = getOne(context, PLANET_SYSTEM, PLANET_SYSTEM.ID.eq(planetSystemRecord.getId()));

                delete(fetchedPlanetSystemRecord);

                log.info("Planet systems, after: {}", count(context, PLANET_SYSTEM));
            }

            @Test
            @DisplayName("Test: delete all")
            void deleteAll() {
                log.info("Planet systems, before: {}", count(context, PLANET_SYSTEM));

                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                log.info("Planet systems, process: {}", count(context, PLANET_SYSTEM));

                delete(context, PLANET_SYSTEM);

                log.info("Planet systems, after: {}", count(context, PLANET_SYSTEM));
            }

            @Test
            @DisplayName("Test: delete set")
            void deleteSet() {
                log.info("Planet systems, before: {}", count(context, PLANET_SYSTEM));

                List<PlanetSystem> planetSystems = initPlanetSystems();
                List<PlanetSystemRecord> planetSystemRecords = planetSystems.stream()
                    .map(planetSystem -> context.newRecord(PLANET_SYSTEM, planetSystem))
                    .toList();

                insert(planetSystemRecords);

                log.info("Planet systems, process: {}", count(context, PLANET_SYSTEM));

                int minValue = 2;
                int maxValue = 10;
                delete(context, PLANET_SYSTEM, PLANET_SYSTEM.STARS.between(minValue, maxValue));

                log.info("Planet systems, after: {}", count(context, PLANET_SYSTEM));
            }
        }
    }

    @Rollback
    @AfterEach
    public void end() {
        context = null;
    }

    @AfterAll
    public static void finalization() {
        log.info("Application tests [jooq]: Shutdown...");
    }
}
