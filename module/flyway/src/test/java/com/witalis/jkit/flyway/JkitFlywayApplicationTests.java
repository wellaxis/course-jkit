package com.witalis.jkit.flyway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@Tag("flyway")
@DisplayName("Test: flyway")
@SpringBootTest
@ContextConfiguration
class JkitFlywayApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void initialization() {
        log.info("Application tests [flyway]: In Action...");
    }

    @Nested
    @Tag("flyway")
    @DisplayName("Test: context")
    class ContextTest {

        @Test
        @DisplayName("Test: context loading")
        void contextLoads() {
            log.info("=========== Flyway: schema history ===========");

            var sql =
                """
                select h.*
                from   "astronomy"."flyway_schema_history" h
                """;
            var map = jdbcTemplate.queryForList(sql);
            map.forEach(
                row -> log.info(row.toString())
            );

            log.info("==============================================");
        }
    }

    @Nested
    @Tag("flyway")
    @DisplayName("Test: content")
    class ContentTest {

        @Test
        @DisplayName("Test: content loading")
        void contentLoads() {
            var sql = "";

            log.info("=========== Flyway: data statistics ==========");

            // planet system
            sql =
                """
                select count(*)
                from   "astronomy".planet_system ps
                """;
            log.info("Flyway [planet system]: {}", jdbcTemplate.queryForObject(sql, Integer.class));

            // planet
            sql =
                """
                select count(*)
                from   "astronomy".planet p
                """;
            log.info("Flyway [planet]: {}", jdbcTemplate.queryForObject(sql, Integer.class));

            // planet attribute
            sql =
                """
                select count(*)
                from   "astronomy".planet_attribute pa
                """;
            log.info("Flyway [planet attribute]: {}", jdbcTemplate.queryForObject(sql, Integer.class));

            // planet moon
            sql =
                """
                select count(*)
                from   "astronomy".planet_moon pm
                """;
            log.info("Flyway [planet moon]: {}", jdbcTemplate.queryForObject(sql, Integer.class));

            // planet atmosphere
            sql =
                """
                select count(*)
                from   "astronomy".planet_atmosphere pt
                """;
            log.info("Flyway [planet atmosphere]: {}", jdbcTemplate.queryForObject(sql, Integer.class));

            // planet atmosphere mapping
            sql =
                """
                select count(*)
                from   "astronomy".planet_atmosphere_map ptm
                """;
            log.info("Flyway [planet atmosphere map]: {}", jdbcTemplate.queryForObject(sql, Integer.class));

            log.info("==============================================");
        }
    }
}
