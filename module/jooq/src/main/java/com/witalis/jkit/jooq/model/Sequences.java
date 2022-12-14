/*
 * This file is generated by jOOQ.
 */
package com.witalis.jkit.jooq.model;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in astronomy.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>astronomy.planet_atmosphere_map_seq</code>
     */
    public static final Sequence<Long> PLANET_ATMOSPHERE_MAP_SEQ = Internal.createSequence("planet_atmosphere_map_seq", Astronomy.ASTRONOMY, SQLDataType.BIGINT.nullable(false), 1001, null, null, null, false, null);

    /**
     * The sequence <code>astronomy.planet_atmosphere_seq</code>
     */
    public static final Sequence<Long> PLANET_ATMOSPHERE_SEQ = Internal.createSequence("planet_atmosphere_seq", Astronomy.ASTRONOMY, SQLDataType.BIGINT.nullable(false), 1001, null, null, null, false, null);

    /**
     * The sequence <code>astronomy.planet_attribute_seq</code>
     */
    public static final Sequence<Long> PLANET_ATTRIBUTE_SEQ = Internal.createSequence("planet_attribute_seq", Astronomy.ASTRONOMY, SQLDataType.BIGINT.nullable(false), 1001, null, null, null, false, null);

    /**
     * The sequence <code>astronomy.planet_moon_seq</code>
     */
    public static final Sequence<Long> PLANET_MOON_SEQ = Internal.createSequence("planet_moon_seq", Astronomy.ASTRONOMY, SQLDataType.BIGINT.nullable(false), 1001, null, null, null, false, null);

    /**
     * The sequence <code>astronomy.planet_seq</code>
     */
    public static final Sequence<Long> PLANET_SEQ = Internal.createSequence("planet_seq", Astronomy.ASTRONOMY, SQLDataType.BIGINT.nullable(false), 1001, null, null, null, false, null);

    /**
     * The sequence <code>astronomy.planet_system_seq</code>
     */
    public static final Sequence<Long> PLANET_SYSTEM_SEQ = Internal.createSequence("planet_system_seq", Astronomy.ASTRONOMY, SQLDataType.BIGINT.nullable(false), 1001, null, null, null, false, null);
}
