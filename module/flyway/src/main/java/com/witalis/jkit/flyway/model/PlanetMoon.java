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
@Table(name = "PLANET_MOON", schema = "ASTRONOMY")
@Entity(name = "PlanetMoon")
public class PlanetMoon implements Serializable {
    @Serial
    private static final long serialVersionUID = 1916935031700303477L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planetMoonSeq")
    @SequenceGenerator(
        name = "planetMoonSeq",
        schema = "ASTRONOMY",
        sequenceName = "PLANET_MOON_SEQ",
        initialValue = 1_001,
        allocationSize = 1
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Basic
    @Column(name = "DISTANCE", precision = 10, scale = 2)
    private BigDecimal distance;

    @Basic
    @Column(name = "RADIUS", precision = 10, scale = 3)
    private BigDecimal radius;

    @Basic
    @Column(name = "NOTE", length = 1000)
    private String note;

    // bidirectional: many-to-one [owning side]

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(
        targetEntity = Planet.class,
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "PLANET_ID",
        referencedColumnName = "ID",
        nullable = false,
        foreignKey = @ForeignKey(
            name = "FKPM_PLANET_ID",
            value = ConstraintMode.CONSTRAINT
        )
    )
    private Planet planet;
}
