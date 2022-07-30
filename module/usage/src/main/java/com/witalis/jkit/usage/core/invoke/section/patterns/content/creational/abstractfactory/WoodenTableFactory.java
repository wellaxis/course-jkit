package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.abstractfactory;

/**
 * Desc: Wooden Table factory class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class WoodenTableFactory implements TableFactory {

    @Override
    public Table makeTable() {
        return new WoodenTable();
    }

    @Override
    public TableExpert makeExpert() {
        return new Carpenter();
    }
}
