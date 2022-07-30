package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.chain;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Account class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@ToString
@Slf4j
public abstract class Account implements Payable {
    protected Account successor;
    private String name;
    private double balance;

    protected Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void setNext(Account account) {
        this.successor = account;
    }

    @Override
    public void pay(float amount) {
        if (canPay(amount)) {
            log.info("1) Payment of {}$ done by {} account", amount, getName());
            balance = -amount;
        } else if (successor != null) {
            log.info("2) Unable to pay by {} account", getName());
            successor.pay(amount);
        } else {
            log.error("3) There is not enough amount in the accounts");
        }
    }

    @Override
    public boolean canPay(float amount) {
        return getBalance() >= amount;
    }
}
