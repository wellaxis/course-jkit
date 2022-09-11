package com.witalis.jkit.jooq.utils;

import com.witalis.jkit.jooq.model.tables.daos.*;
import com.witalis.jkit.jooq.model.tables.pojos.*;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.UpdatableRecord;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DefaultConfiguration;

import java.util.List;

@Slf4j
public final class TestUtils {

    private TestUtils() {
        super();
    }

    public static Configuration initConfiguration(DSLContext context) {
        return new DefaultConfiguration()
            .set(new Settings()
                .withRenderQuotedNames(RenderQuotedNames.NEVER)
                .withRenderNameCase(RenderNameCase.AS_IS)
            )
            .set(context.parsingConnection())
            .set(SQLDialect.H2);
    }

    public static PlanetSystemDao initPlanetSystemDao(DSLContext context) {
        return (PlanetSystemDao) initDao(context, PlanetSystemDao.class);
    }

    public static PlanetDao initPlanetDao(DSLContext context) {
        return (PlanetDao) initDao(context, PlanetDao.class);
    }

    public static PlanetAttributeDao initPlanetAttributeDao(DSLContext context) {
        return (PlanetAttributeDao) initDao(context, PlanetAttributeDao.class);
    }

    public static PlanetMoonDao initPlanetMoonDao(DSLContext context) {
        return (PlanetMoonDao) initDao(context, PlanetMoonDao.class);
    }

    public static PlanetAtmosphereDao initPlanetAtmosphereDao(DSLContext context) {
        return (PlanetAtmosphereDao) initDao(context, PlanetAtmosphereDao.class);
    }

    public static PlanetAtmosphereMapDao initPlanetAtmosphereMapDao(DSLContext context) {
        return (PlanetAtmosphereMapDao) initDao(context, PlanetAtmosphereMapDao.class);
    }

    private static <R extends UpdatableRecord<R>, P, T> DAOImpl<R, P, T> initDao(DSLContext context, Class<? extends DAOImpl<R, P, T>> clazz) {
        DAOImpl<R, P, T> dao = null;
        try {
            dao = clazz.getDeclaredConstructor().newInstance();
            dao.setConfiguration(initConfiguration(context));
        } catch (Exception e) {
            log.error("", e);
        }
        return dao;
    }

    public static PlanetSystem initPlanetSystem() {
        return new PlanetSystem(
            1L,
            "Solar System",
            4_568_000_000L,
            1,
            "Milky Way Galaxy",
            "Distance to Galactic Center: 27000 +/- 1000 ly"
        );
    }

    public static List<PlanetSystem> initPlanetSystems() {
        return List.of(
            new PlanetSystem(
                1L,
                "Solar System",
                4_568_000_000L,
                1,
                "Milky Way Galaxy",
                "Distance to Galactic Center: 27000 +/- 1000 ly"
            ),
            new PlanetSystem(
                2L,
                "Virtual System",
                3_632_000_000L,
                3,
                "Virtual Galaxy",
                "Distance to Galactic Center: 35000 +/- 3000 ly"
            ),
            new PlanetSystem(
                3L,
                "Weird System",
                9_999_000_000L,
                9,
                "Weird Galaxy",
                "Distance to Galactic Center: unknown"
            )
        );
    }
}
