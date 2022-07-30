package com.witalis.jkit.usage.core.invoke.section.core.content;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Desc: Java Versions
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
@Getter
public final class Java {
    // constant
    public static final List<Java> instances;
    // variable
    private final String name;
    private final String version;
    private final String codename;
    private final LocalDate release;

    @Override
    public String toString() {
        return "Java [" + name + " " + version + " '" + codename + "' at " + release.format(DateTimeFormatter.BASIC_ISO_DATE) + "]";
    }

    static {
        instances = List.of(
            new Java("JDK", "0.1", "Beta", LocalDate.of(1995, Month.JANUARY, 1)),
            new Java("JDK", "1.0", "Oak", LocalDate.of(1996, Month.JANUARY, 23)),
            new Java("JDK", "1.1.4", "Sparkler", LocalDate.of(1997, Month.SEPTEMBER, 12)),
            new Java("JDK", "1.1.5", "Pumpkin", LocalDate.of(1997, Month.DECEMBER, 3)),
            new Java("JDK", "1.1.6", "Abigail", LocalDate.of(1998, Month.APRIL, 24)),
            new Java("JDK", "1.1.7", "Brutus", LocalDate.of(1998, Month.SEPTEMBER, 28)),
            new Java("JDK", "1.1.8", "Chelsea", LocalDate.of(1999, Month.APRIL, 8)),
            new Java("J2SE", "1.2", "Playground", LocalDate.of(1998, Month.DECEMBER, 4)),
            new Java("J2SE", "1.2.1", "(none)", LocalDate.of(1999, Month.MARCH, 30)),
            new Java("J2SE", "1.2.2", "Cricket", LocalDate.of(1999, Month.JULY, 8)),
            new Java("J2SE", "1.3", "Kestrel", LocalDate.of(2000, Month.MAY, 8)),
            new Java("J2SE", "1.3.1", "Ladybird", LocalDate.of(2001, Month.MAY, 17)),
            new Java("J2SE", "1.4.0", "Merlin", LocalDate.of(2002, Month.FEBRUARY, 13)),
            new Java("J2SE", "1.4.1", "Hopper", LocalDate.of(2003, Month.SEPTEMBER, 16)),
            new Java("J2SE", "5.0", "Tiger", LocalDate.of(2004, Month.SEPTEMBER, 29)),
            new Java("Java SE", "6", "Mustang", LocalDate.of(2006, Month.DECEMBER, 11)),
            new Java("Java SE", "7", "Mustang", LocalDate.of(2011, Month.JULY, 7)),
            new Java("Java SE", "8", "(none)", LocalDate.of(2014, Month.MARCH, 18)),
            new Java("Java SE", "9", "(none)", LocalDate.of(2017, Month.SEPTEMBER, 21)),
            new Java("Java SE", "10", "(none)", LocalDate.of(2018, Month.MARCH, 20)),
            new Java("Java SE", "11", "(none)", LocalDate.of(2018, Month.SEPTEMBER, 25)),
            new Java("Java SE", "12", "(none)", LocalDate.of(2019, Month.MARCH, 19)),
            new Java("Java SE", "13", "(none)", LocalDate.of(2019, Month.SEPTEMBER, 17)),
            new Java("Java SE", "14", "(none)", LocalDate.of(2020, Month.MARCH, 20)),
            new Java("Java SE", "15", "(none)", LocalDate.of(2020, Month.SEPTEMBER, 15)),
            new Java("Java SE", "16", "(none)", LocalDate.of(2021, Month.MARCH, 16)),
            new Java("Java SE", "17", "(none)", LocalDate.of(2021, Month.SEPTEMBER, 14)),
            new Java("Java SE", "18", "(none)", LocalDate.of(2022, Month.MARCH, 22))
        );
    }

    public Java(String name, String version, String codename, LocalDate release) {
        this.name = name;
        this.version = version;
        this.codename = codename;
        this.release = release;
    }

    public static void history() {
        instances.forEach(java -> log.info(java.toString()));
    }
}
