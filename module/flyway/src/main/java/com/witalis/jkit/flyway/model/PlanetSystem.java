package com.witalis.jkit.flyway.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "PLANET_SYSTEM", schema = "ASTRONOMY")
@Entity(name = "PlanetSystem")
public class PlanetSystem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1916935031700303477L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planetSystemSeq")
    @SequenceGenerator(
        name = "planetSystemSeq",
        schema = "ASTRONOMY",
        sequenceName = "PLANET_SYSTEM_SEQ",
        initialValue = 1_001,
        allocationSize = 1
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Basic
    @Column(name = "AGE")
    private Long age;

    @Basic
    @Column(name = "STARS")
    private Integer stars;

    @Basic
    @Column(name = "LOCATION", length = 200)
    private String location;

    @Basic
    @Column(name = "NOTE", length = 1000)
    private String note;
}
