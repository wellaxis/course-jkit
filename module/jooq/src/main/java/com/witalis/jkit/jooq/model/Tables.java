/*
 * This file is generated by jOOQ.
 */
package com.witalis.jkit.jooq.model;


import com.witalis.jkit.jooq.model.tables.Planet;
import com.witalis.jkit.jooq.model.tables.PlanetAtmosphere;
import com.witalis.jkit.jooq.model.tables.PlanetAtmosphereMap;
import com.witalis.jkit.jooq.model.tables.PlanetAttribute;
import com.witalis.jkit.jooq.model.tables.PlanetMoon;
import com.witalis.jkit.jooq.model.tables.PlanetSystem;


/**
 * Convenience access to all tables in astronomy.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * Entity representing planet.
     */
    public static final Planet PLANET = Planet.PLANET;

    /**
     * Entity representing planet atmosphere.
     */
    public static final PlanetAtmosphere PLANET_ATMOSPHERE = PlanetAtmosphere.PLANET_ATMOSPHERE;

    /**
     * Entity representing planet atmosphere mapping.
     */
    public static final PlanetAtmosphereMap PLANET_ATMOSPHERE_MAP = PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP;

    /**
     * Entity representing planet attribute.
     */
    public static final PlanetAttribute PLANET_ATTRIBUTE = PlanetAttribute.PLANET_ATTRIBUTE;

    /**
     * Entity representing planet moon.
     */
    public static final PlanetMoon PLANET_MOON = PlanetMoon.PLANET_MOON;

    /**
     * Entity representing planet system.
     */
    public static final PlanetSystem PLANET_SYSTEM = PlanetSystem.PLANET_SYSTEM;
}