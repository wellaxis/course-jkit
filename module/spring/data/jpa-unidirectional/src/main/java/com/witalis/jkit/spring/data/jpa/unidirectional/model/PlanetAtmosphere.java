package com.witalis.jkit.spring.data.jpa.unidirectional.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "ATMOSPHERE")
@Entity(name = "Atmosphere")
public class PlanetAtmosphere implements Serializable {
    @Serial
    private static final long serialVersionUID = 1916935031700303477L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atmosphereSeq")
    @SequenceGenerator(
        name = "atmosphereSeq",
        sequenceName = "ATMOSPHERE_SEQ",
        initialValue = 1_001,
        allocationSize = 1
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;
}
