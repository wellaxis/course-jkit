/*
 * This file is generated by jOOQ.
 */
package com.witalis.jkit.jooq.model.tables.records;


import com.witalis.jkit.jooq.model.tables.PlanetSystem;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Entity representing planet system.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlanetSystemRecord extends UpdatableRecordImpl<PlanetSystemRecord> implements Record6<Long, String, Long, Integer, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>astronomy.planet_system.id</code>. Planet system
     * identifier. Primary Key.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>astronomy.planet_system.id</code>. Planet system
     * identifier. Primary Key.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>astronomy.planet_system.name</code>. Name of the planet
     * system.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>astronomy.planet_system.name</code>. Name of the planet
     * system.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>astronomy.planet_system.age</code>. Age of the planet
     * system.
     */
    public void setAge(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>astronomy.planet_system.age</code>. Age of the planet
     * system.
     */
    public Long getAge() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>astronomy.planet_system.stars</code>. Number of stars
     * into the planet system.
     */
    public void setStars(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>astronomy.planet_system.stars</code>. Number of stars
     * into the planet system.
     */
    public Integer getStars() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>astronomy.planet_system.location</code>. Location of the
     * planet system.
     */
    public void setLocation(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>astronomy.planet_system.location</code>. Location of the
     * planet system.
     */
    public String getLocation() {
        return (String) get(4);
    }

    /**
     * Setter for <code>astronomy.planet_system.note</code>. Note of the planet
     * system.
     */
    public void setNote(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>astronomy.planet_system.note</code>. Note of the planet
     * system.
     */
    public String getNote() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, Long, Integer, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, String, Long, Integer, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return PlanetSystem.PLANET_SYSTEM.ID;
    }

    @Override
    public Field<String> field2() {
        return PlanetSystem.PLANET_SYSTEM.NAME;
    }

    @Override
    public Field<Long> field3() {
        return PlanetSystem.PLANET_SYSTEM.AGE;
    }

    @Override
    public Field<Integer> field4() {
        return PlanetSystem.PLANET_SYSTEM.STARS;
    }

    @Override
    public Field<String> field5() {
        return PlanetSystem.PLANET_SYSTEM.LOCATION;
    }

    @Override
    public Field<String> field6() {
        return PlanetSystem.PLANET_SYSTEM.NOTE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Long component3() {
        return getAge();
    }

    @Override
    public Integer component4() {
        return getStars();
    }

    @Override
    public String component5() {
        return getLocation();
    }

    @Override
    public String component6() {
        return getNote();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Long value3() {
        return getAge();
    }

    @Override
    public Integer value4() {
        return getStars();
    }

    @Override
    public String value5() {
        return getLocation();
    }

    @Override
    public String value6() {
        return getNote();
    }

    @Override
    public PlanetSystemRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PlanetSystemRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public PlanetSystemRecord value3(Long value) {
        setAge(value);
        return this;
    }

    @Override
    public PlanetSystemRecord value4(Integer value) {
        setStars(value);
        return this;
    }

    @Override
    public PlanetSystemRecord value5(String value) {
        setLocation(value);
        return this;
    }

    @Override
    public PlanetSystemRecord value6(String value) {
        setNote(value);
        return this;
    }

    @Override
    public PlanetSystemRecord values(Long value1, String value2, Long value3, Integer value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlanetSystemRecord
     */
    public PlanetSystemRecord() {
        super(PlanetSystem.PLANET_SYSTEM);
    }

    /**
     * Create a detached, initialised PlanetSystemRecord
     */
    public PlanetSystemRecord(Long id, String name, Long age, Integer stars, String location, String note) {
        super(PlanetSystem.PLANET_SYSTEM);

        setId(id);
        setName(name);
        setAge(age);
        setStars(stars);
        setLocation(location);
        setNote(note);
    }

    /**
     * Create a detached, initialised PlanetSystemRecord
     */
    public PlanetSystemRecord(com.witalis.jkit.jooq.model.tables.pojos.PlanetSystem value) {
        super(PlanetSystem.PLANET_SYSTEM);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setAge(value.getAge());
            setStars(value.getStars());
            setLocation(value.getLocation());
            setNote(value.getNote());
        }
    }
}
