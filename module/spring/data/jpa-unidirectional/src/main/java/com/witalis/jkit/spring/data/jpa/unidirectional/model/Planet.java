package com.witalis.jkit.spring.data.jpa.unidirectional.model;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        targetEntity = PlanetMoon.class,
        cascade = CascadeType.ALL,
        orphanRemoval = true
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
    private List<PlanetMoon> moons = new ArrayList<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
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
}
