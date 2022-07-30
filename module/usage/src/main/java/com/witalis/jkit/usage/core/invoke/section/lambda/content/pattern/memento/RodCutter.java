package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.memento;

import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.memento.Memoizer;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class RodCutter {
    final List<Integer> prices;

    // sample prices for different lengths, starting with 1 inch
    public static final List<Integer> priceValues = Arrays.asList(
        2, 1, 1, 2, 2, 2, 1, 8, 9, 15
    );

    public RodCutter(final List<Integer> pricesForLength) {
        prices = pricesForLength;
    }

    public int maxProfit(final int length) {
        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
        for (int i = 1; i < length; i++) {
            int priceWhenCut = maxProfit(i) + maxProfit(length - i);
            if(profit < priceWhenCut) profit = priceWhenCut;
        }

        return profit;
    }

    public int maxProfitMemoized(final int rodLength) {
        BiFunction<Function<Integer, Integer>, Integer, Integer> compute =
            (func, length) -> {
                int profit = length <= prices.size() ? prices.get(length - 1) : 0;
                for (int i = 1; i < length; i++) {
                    int priceWhenCut = func.apply(i) + func.apply(length - i);
                    if (profit < priceWhenCut) {
                        profit = priceWhenCut;
                    }
                }
                return profit;
            };
        return Memoizer.callMemoized(compute, rodLength);
    }
}
