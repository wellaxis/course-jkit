package com.witalis.jkit.spring.data.jpa.pagination.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NamedEntityGraphs(
    value = {
        @NamedEntityGraph(
            name = "moon-planet",
            attributeNodes = {
                @NamedAttributeNode(value = "planet")
            }
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "MOON")
@Entity(name = "Moon")
public class PlanetMoon implements Serializable {
    @Serial
    private static final long serialVersionUID = 1916935031700303477L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moonSeq")
    @SequenceGenerator(
        name = "moonSeq",
        sequenceName = "MOON_SEQ",
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
    @Column(name = "NOTE", length = 200)
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
