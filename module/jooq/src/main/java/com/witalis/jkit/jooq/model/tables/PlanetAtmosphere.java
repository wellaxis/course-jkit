/*
 * This file is generated by jOOQ.
 */
package com.witalis.jkit.jooq.model.tables;


import com.witalis.jkit.jooq.model.Astronomy;
import com.witalis.jkit.jooq.model.Keys;
import com.witalis.jkit.jooq.model.tables.records.PlanetAtmosphereRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Entity representing planet atmosphere.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlanetAtmosphere extends TableImpl<PlanetAtmosphereRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>astronomy.planet_atmosphere</code>
     */
    public static final PlanetAtmosphere PLANET_ATMOSPHERE = new PlanetAtmosphere();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PlanetAtmosphereRecord> getRecordType() {
        return PlanetAtmosphereRecord.class;
    }

    /**
     * The column <code>astronomy.planet_atmosphere.id</code>. Planet atmosphere
     * identifier. Primary Key.
     */
    public final TableField<PlanetAtmosphereRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "Planet atmosphere identifier. Primary Key.");

    /**
     * The column <code>astronomy.planet_atmosphere.name</code>. Name of the
     * planet atmosphere.
     */
    public final TableField<PlanetAtmosphereRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "Name of the planet atmosphere.");

    /**
     * The column <code>astronomy.planet_atmosphere.note</code>. Note of the
     * planet atmosphere.
     */
    public final TableField<PlanetAtmosphereRecord, String> NOTE = createField(DSL.name("note"), SQLDataType.CLOB, this, "Note of the planet atmosphere.");

    private PlanetAtmosphere(Name alias, Table<PlanetAtmosphereRecord> aliased) {
        this(alias, aliased, null);
    }

    private PlanetAtmosphere(Name alias, Table<PlanetAtmosphereRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Entity representing planet atmosphere."), TableOptions.table());
    }

    /**
     * Create an aliased <code>astronomy.planet_atmosphere</code> table
     * reference
     */
    public PlanetAtmosphere(String alias) {
        this(DSL.name(alias), PLANET_ATMOSPHERE);
    }

    /**
     * Create an aliased <code>astronomy.planet_atmosphere</code> table
     * reference
     */
    public PlanetAtmosphere(Name alias) {
        this(alias, PLANET_ATMOSPHERE);
    }

    /**
     * Create a <code>astronomy.planet_atmosphere</code> table reference
     */
    public PlanetAtmosphere() {
        this(DSL.name("planet_atmosphere"), null);
    }

    public <O extends Record> PlanetAtmosphere(Table<O> child, ForeignKey<O, PlanetAtmosphereRecord> key) {
        super(child, key, PLANET_ATMOSPHERE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Astronomy.ASTRONOMY;
    }

    @Override
    public UniqueKey<PlanetAtmosphereRecord> getPrimaryKey() {
        return Keys.PKPT_ID;
    }

    @Override
    public PlanetAtmosphere as(String alias) {
        return new PlanetAtmosphere(DSL.name(alias), this);
    }

    @Override
    public PlanetAtmosphere as(Name alias) {
        return new PlanetAtmosphere(alias, this);
    }

    @Override
    public PlanetAtmosphere as(Table<?> alias) {
        return new PlanetAtmosphere(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PlanetAtmosphere rename(String name) {
        return new PlanetAtmosphere(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PlanetAtmosphere rename(Name name) {
        return new PlanetAtmosphere(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PlanetAtmosphere rename(Table<?> name) {
        return new PlanetAtmosphere(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}