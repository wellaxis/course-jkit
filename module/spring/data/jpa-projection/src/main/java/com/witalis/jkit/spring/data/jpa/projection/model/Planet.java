package com.witalis.jkit.spring.data.jpa.projection.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        getMoons().add(moon);
        moon.setPlanet(this);
    }

    public void removeMoon(PlanetMoon moon) {
        getMoons().remove(moon);
        moon.setPlanet(null);
    }
}
