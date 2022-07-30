package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.chain;

/**
 * Desc: Payable interface.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public interface Payable {
    void pay(float amount);
    boolean canPay(float amount);
}
