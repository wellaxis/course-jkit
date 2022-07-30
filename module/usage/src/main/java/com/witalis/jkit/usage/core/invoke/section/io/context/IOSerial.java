package com.witalis.jkit.usage.core.invoke.section.io.context;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class IOSerial implements Serializable {
    private static final long serialVersionUID = -19038955977267981L;

    int id;
    public String name;
    protected boolean admin;
    private double salary;
    transient String card;
    public static long work;
}
