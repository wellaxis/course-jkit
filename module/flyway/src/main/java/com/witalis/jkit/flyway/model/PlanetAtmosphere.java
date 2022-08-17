package com.witalis.jkit.flyway.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "PLANET_ATMOSPHERE", schema = "ASTRONOMY")
@Entity(name = "PlanetAtmosphere")
public class PlanetAtmosphere implements Serializable {
    @Serial
    private static final long serialVersionUID = 1916935031700303477L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planetAtmosphereSeq")
    @SequenceGenerator(
        name = "planetAtmosphereSeq",
        schema = "ASTRONOMY",
        sequenceName = "PLANET_ATMOSPHERE_SEQ",
        initialValue = 1_001,
        allocationSize = 1
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    // bidirectional: many-to-many [inverse side]

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(
        mappedBy = "atmospheres",
        targetEntity = Planet.class,
        fetch = FetchType.LAZY
    )
    private Set<Planet> planets = new HashSet<>();
}
