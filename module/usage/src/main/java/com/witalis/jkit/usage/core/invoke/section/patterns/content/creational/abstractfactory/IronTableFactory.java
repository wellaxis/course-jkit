package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.abstractfactory;

/**
 * Desc: Iron Table factory class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class IronTableFactory implements TableFactory {

    @Override
    public Table makeTable() {
        return new IronTable();
    }

    @Override
    public TableExpert makeExpert() {
        return new Welder();
    }
}
