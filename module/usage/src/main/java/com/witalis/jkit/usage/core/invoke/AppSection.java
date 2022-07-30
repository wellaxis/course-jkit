package com.witalis.jkit.usage.core.invoke;

import java.util.Arrays;

public enum AppSection {
    ABSTRACTION("abstraction"),
    ANNOTATION("annotation"),
    APPLETS("applets"),
    ASSERTION("assertion"),
    BEANS("beans"),
    BITS("bits"),
    BLOCKS("blocks"),
    CLASSES("classes"),
    CLONING("cloning"),
    COLLECTION("collection"),
    CONSTANTS("constants"),
    CONSTRUCTORS("constructors"),
    CORE("core"),
    DATES("dates"),
    ENCAPSULATION("encapsulation"),
    ENUMERATION("enumeration"),
    EVENTS("events"),
    EXCEPTION("exception"),
    FORMATS("formats"),
    GARBAGE("garbage"),
    GENERICS("generics"),
    GRAPHICS("graphics"),
    INHERITANCE("inheritance"),
    INTERFACES("interfaces"),
    IO("io"),
    KEYWORDS("keywords"),
    LAMBDA("lambda"),
    METHODS("methods"),
    MODIFIERS("modifiers"),
    MODULES("modules"),
    MULTITHREADING("multithreading"),
    NETWORKS("networks"),
    NUMBERS("numbers"),
    OOP("oop"),
    OPERATORS("operators"),
    OVERLOADING("overloading"),
    OVERRIDING("overriding"),
    PACKAGES("packages"),
    PATTERNS("patterns"),
    POLYMORPHISM("polymorphism"),
    PRIMITIVES("primitives"),
    PROFILE("profile"),
    PROPERTIES("properties"),
    RECURSION("recursion"),
    REFERENCES("references"),
    REFLECTION("reflection"),
    REGEXP("regexp"),
    SECURITY("security"),
    SERVLETS("servlet"),
    STATEMENTS("statements"),
    STREAMS("streams"),
    STRINGS("strings"),
    SYSTEM("system"),
    TOKENS("tokens"),
    TYPES("types"),
    UTILS("utils"),
    VARIABLES("variables");

    private final String name;

    AppSection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AppSection getSection(String name) {
        return Arrays.stream(AppSection.values())
            .filter(section -> section.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
}
