package com.witalis.jkit.spring.data.jpa.bidirectional.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraphs(
    value = {
        @NamedEntityGraph(
            name = "planet-moon",
            attributeNodes = {
                @NamedAttributeNode(value = "moons")
            }
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "PLANET")
@Entity(name = "Planet")
public class Planet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1916935031700303477L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planetSeq")
    @SequenceGenerator(
        name = "planetSeq",
        sequenceName = "PLANET_SEQ",
        initialValue = 1_001,
        allocationSize = 1
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", length = 20, nullable = false)
    private PlanetType type;

    @Basic
    @Column(name = "SIGN", length = 1)
    private String sign;

    @Basic
    @Column(name = "NOTE", length = 200)
    private String note;

    // bidirectional one-to-one [inverse side]

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(
        mappedBy = "planet",
        targetEntity = PlanetAttribute.class,
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private PlanetAttribute attribute;

    public void setAttribute(PlanetAttribute attribute) {
        if (attribute == null) {
            if (this.getAttribute() != null) {
                this.getAttribute().setPlanet(null);
            }
        } else {
            attribute.setPlanet(this);
        }
        this.attribute = attribute;
    }

    // bidirectional one-to-many [inverse side]

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        mappedBy = "planet",
        targetEntity = PlanetMoon.class,
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PlanetMoon> moons = new ArrayList<>();

    public void addMoon(PlanetMoon moon) {
        this.getMoons().add(moon);
        moon.setPlanet(this);
    }

    public void removeMoon(PlanetMoon moon) {
        this.getMoons().remove(moon);
        moon.setPlanet(null);
    }

    // bidirectional many-to-many [owning side]

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(
        targetEntity = PlanetAtmosphere.class,
        fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinTable(
        name = "PLANET_ATMOSPHERE",
        joinColumns = {
            @JoinColumn(
                name = "PLANET_ID",
                nullable = false,
                foreignKey = @ForeignKey(
                    name = "FKPT_PLANET_ID",
                    value = ConstraintMode.CONSTRAINT
                )
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "ATMOSPHERE_ID",
                nullable = false,
                foreignKey = @ForeignKey(
                    name = "FKTP_ATMOSPHERE_ID",
                    value = ConstraintMode.CONSTRAINT
                )
            )
        }
    )
    private Set<PlanetAtmosphere> atmospheres = new HashSet<>();

    public void addAtmosphere(PlanetAtmosphere atmosphere) {
        this.getAtmospheres().add(atmosphere);
        atmosphere.getPlanets().add(this);
    }

    public void removeAtmosphere(PlanetAtmosphere atmosphere) {
        this.getAtmospheres().remove(atmosphere);
        atmosphere.getPlanets().remove(this);
    }
}
