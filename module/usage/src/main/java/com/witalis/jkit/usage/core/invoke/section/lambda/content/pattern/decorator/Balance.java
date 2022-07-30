package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.decorator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder(setterPrefix = "of")
@AllArgsConstructor
@RequiredArgsConstructor
public class Balance {
    public static final int BONUS_RATE = 5;
    public static final int TAXES_RATE = 20;
    private double amount;

    public Balance income(double sum) {
        return Balance.builder()
            .ofAmount(getAmount() + sum)
            .build();
    }

    public Balance outcome(double sum) {
        return Balance.builder()
            .ofAmount(getAmount() - sum)
            .build();
    }

    public Balance bonus() {
        return Balance.builder()
            .ofAmount(getAmount() * (1 + 0.05))
            .build();
    }

    public Balance taxes() {
        return Balance.builder()
            .ofAmount(getAmount() * ( 1 - 0.2))
            .build();
    }
}
