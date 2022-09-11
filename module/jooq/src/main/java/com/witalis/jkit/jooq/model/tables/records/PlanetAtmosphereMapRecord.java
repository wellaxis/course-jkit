/*
 * This file is generated by jOOQ.
 */
package com.witalis.jkit.jooq.model.tables.records;


import com.witalis.jkit.jooq.model.tables.PlanetAtmosphereMap;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Entity representing planet atmosphere mapping.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlanetAtmosphereMapRecord extends UpdatableRecordImpl<PlanetAtmosphereMapRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>astronomy.planet_atmosphere_map.planet_id</code>.
     * Reference to the planet identifier.
     */
    public void setPlanetId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>astronomy.planet_atmosphere_map.planet_id</code>.
     * Reference to the planet identifier.
     */
    public Long getPlanetId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>astronomy.planet_atmosphere_map.atmosphere_id</code>.
     * Reference to the planet atmosphere identifier.
     */
    public void setAtmosphereId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>astronomy.planet_atmosphere_map.atmosphere_id</code>.
     * Reference to the planet atmosphere identifier.
     */
    public Long getAtmosphereId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP.PLANET_ID;
    }

    @Override
    public Field<Long> field2() {
        return PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP.ATMOSPHERE_ID;
    }

    @Override
    public Long component1() {
        return getPlanetId();
    }

    @Override
    public Long component2() {
        return getAtmosphereId();
    }

    @Override
    public Long value1() {
        return getPlanetId();
    }

    @Override
    public Long value2() {
        return getAtmosphereId();
    }

    @Override
    public PlanetAtmosphereMapRecord value1(Long value) {
        setPlanetId(value);
        return this;
    }

    @Override
    public PlanetAtmosphereMapRecord value2(Long value) {
        setAtmosphereId(value);
        return this;
    }

    @Override
    public PlanetAtmosphereMapRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlanetAtmosphereMapRecord
     */
    public PlanetAtmosphereMapRecord() {
        super(PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP);
    }

    /**
     * Create a detached, initialised PlanetAtmosphereMapRecord
     */
    public PlanetAtmosphereMapRecord(Long planetId, Long atmosphereId) {
        super(PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP);

        setPlanetId(planetId);
        setAtmosphereId(atmosphereId);
    }

    /**
     * Create a detached, initialised PlanetAtmosphereMapRecord
     */
    public PlanetAtmosphereMapRecord(com.witalis.jkit.jooq.model.tables.pojos.PlanetAtmosphereMap value) {
        super(PlanetAtmosphereMap.PLANET_ATMOSPHERE_MAP);

        if (value != null) {
            setPlanetId(value.getPlanetId());
            setAtmosphereId(value.getAtmosphereId());
        }
    }
}