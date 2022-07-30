package com.witalis.jkit.usage.core.invoke.section.constant.content;

/**
 * Desc: Lambda constant
 * User: Wellaxis
 * Date: 4/10/2022
 */
public class LambdaConstant {
    private int number;

    // lambda generator
    interface LambdaGenerator<T> {
        T generate(T t);
    }

    public LambdaConstant(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int generate(Integer value) {
        // effectively final variable
        int defValue = 100;

        LambdaGenerator<Integer> intGenerator = (num) -> {
            // value = 200; -- unable
            // defValue = 200; -- unable

            return (value != null) ? value : defValue;
        };

        return intGenerator.generate(value);
    }
}
