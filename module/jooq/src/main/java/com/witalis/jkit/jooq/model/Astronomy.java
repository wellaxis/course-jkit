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

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Astronomy extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>astronomy</code>
     */
    public static final Astronomy ASTRONOMY = new Astronomy();

    /**
     * Entity representing planet.
     */
    public final Planet PLANET = Planet.PLANET;

    /**
     * Entity representing planet atmosphere.
     */
    public final PlanetAtmosphere PLANET_ATMOSPHERE = PlanetAtmosphere.PLANET_ATMOSPHERE;

    /**
     * Entity representing planet atmosphere mapping.
     */
    public final PlanetAtmosphereMap PLANET_ATMOSPHERE_MAP = PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP;

    /**
     * Entity representing planet attribute.
     */
    public final PlanetAttribute PLANET_ATTRIBUTE = PlanetAttribute.PLANET_ATTRIBUTE;

    /**
     * Entity representing planet moon.
     */
    public final PlanetMoon PLANET_MOON = PlanetMoon.PLANET_MOON;

    /**
     * Entity representing planet system.
     */
    public final PlanetSystem PLANET_SYSTEM = PlanetSystem.PLANET_SYSTEM;

    /**
     * No further instances allowed
     */
    private Astronomy() {
        super("astronomy", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.asList(
            Sequences.PLANET_ATMOSPHERE_MAP_SEQ,
            Sequences.PLANET_ATMOSPHERE_SEQ,
            Sequences.PLANET_ATTRIBUTE_SEQ,
            Sequences.PLANET_MOON_SEQ,
            Sequences.PLANET_SEQ,
            Sequences.PLANET_SYSTEM_SEQ
        );
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Planet.PLANET,
            PlanetAtmosphere.PLANET_ATMOSPHERE,
            PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP,
            PlanetAttribute.PLANET_ATTRIBUTE,
            PlanetMoon.PLANET_MOON,
            PlanetSystem.PLANET_SYSTEM
        );
    }
}
