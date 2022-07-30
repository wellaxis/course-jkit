package com.witalis.jkit.usage.core.invoke.section.classes.content;

/**
 * Desc: Sample class
 * User: Wellaxis
 * Date: 4/11/2022
 */
// class name & definition
public class ConcreteClass implements Comparable {
    // constants
    public static final int DEF_ID = 1000;
    // static variables
    public static boolean active;
    private static int counter;
    // instance variables
    private long id;
    public String name;

    // static block
    static {
        active = true;
    }

    // default constructor
    public ConcreteClass() {
        super();
    }

    // basic constructor
    public ConcreteClass(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // instance methods
    public String getInformation() {
        return "Sample information.";
    }

    // static methods
    public static int count() {
        return ++counter;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(id, ((ConcreteClass) o).id);
    }

    @Override
    public String toString() {
        return "Sample [id=" + id + ", name='" + name + "']";
    }

    public static void main(String[] args) {
        ConcreteClass sample = new ConcreteClass(1L, "Example");
        sample.getInformation();
    }
}
