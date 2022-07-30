package com.witalis.jkit.usage.core.invoke.section.encapsulation.content;

import java.util.Objects;

/**
 * Desc: Product class
 * User: Wellaxis
 * Date: 4/9/2022
 */
public class Product {
    private String name;
    private double cost;

    public Product() {
        super();
    }

    public Product(String name) {
        this();
        this.name = name;
    }

    public Product(String name, double cost) {
        this(name);
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotalCost(float tax) {
        return calculateTotalCost(tax);
    }

    /**
     * It is private => is hidden from client.
     */
    private double calculateTotalCost(float tax) {
        return getCost() * (1 + tax);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getCost(), getCost()) == 0 &&
            Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCost());
    }

    @Override
    public String toString() {
        return "Product [" + "name='" + name + '\'' + ", cost=" + cost + ']';
    }
}
