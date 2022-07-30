package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.builder;

/**
 * Desc: Burger class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Burger {
    private int size;
    private boolean cheese = false;
    private boolean tomato = false;

    private Burger() {
    }

    public static Builder newBuilder() {
        return new Burger().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setSize(int size) {
            Burger.this.size = size;
            return this;
        }

        public Builder setCheese(boolean cheese) {
            Burger.this.cheese = cheese;
            return this;
        }

        public Builder setTomato(boolean tomato) {
            Burger.this.tomato = tomato;
            return this;
        }

        public Burger build() {
            return Burger.this;
        }
    }
}
