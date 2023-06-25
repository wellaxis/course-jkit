package com.witalis.jkit.flyway.model;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "PLANET_ATTRIBUTE", schema = "ASTRONOMY")
@Entity(name = "PlanetAttribute")
public class PlanetAttribute implements Serializable {
    @Serial
    private static final long serialVersionUID = 1916935031700303477L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planetAttributeSeq")
    @SequenceGenerator(
        name = "planetAttributeSeq",
        schema = "ASTRONOMY",
        sequenceName = "PLANET_ATTRIBUTE_SEQ",
        initialValue = 1_001,
        allocationSize = 10
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "DIAMETER", precision = 10, scale = 3)
    private BigDecimal diameter;

    @Basic
    @Column(name = "MASS", precision = 10, scale = 2)
    private BigDecimal mass;

    @Basic
    @Column(name = "AXIS", precision = 10, scale = 2)
    private BigDecimal axis;

    @Basic
    @Column(name = "PERIOD", precision = 10, scale = 2)
    private BigDecimal period;

    @Basic
    @Column(name = "INCLINATION", precision = 5, scale = 2)
    private BigDecimal inclination;

    @Basic
    @Column(name = "ECCENTRICITY", precision = 5, scale = 3)
    private BigDecimal eccentricity;

    @Basic
    @Column(name = "ROTATION", precision = 10, scale = 2)
    private BigDecimal rotation;

    @Basic
    @Column(name = "MOONS", precision = 100)
    private Integer moons;

    @Basic
    @Column(name = "RINGS")
    private Boolean rings;

    // bidirectional: one-to-one [owning side]

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @MapsId
    @OneToOne(
        targetEntity = Planet.class,
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "PLANET_ID",
        referencedColumnName = "ID",
        nullable = false,
        foreignKey = @ForeignKey(
            name = "FKPA_PLANET_ID",
            value = ConstraintMode.CONSTRAINT
        )
    )
    private Planet planet;
}
