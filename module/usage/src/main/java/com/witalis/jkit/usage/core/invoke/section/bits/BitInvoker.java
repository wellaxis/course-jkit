package com.witalis.jkit.usage.core.invoke.section.bits;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Bit declaration
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class BitInvoker extends Invoker {

    public BitInvoker() {
        setTitle("Bits chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // operations
        log.info("## Operations");
        invokeOperation();
        // tab
        log.info("");
        // shifting
        log.info("## Shifting");
        invokeShifting();
    }

    /**
     * Basic data types definitions.
     */
    private void invokeBasis() {
        // variable definition
        var value = 0b101001; // 41
        log.info(
            "Bits [value]: decimal={}, binary={}",
            value,
            Integer.toBinaryString(value)
        );
    }

    /**
     * Operations with bits.
     */
    private void invokeOperation() {
        log.info(" * Logical operators");

        int v1 = 0b101001;
        log.info("    Bits[v1]: decimal={}, binary={}", v1, Integer.toBinaryString(v1));

        int v2 = 0b110101;
        log.info("    Bits[v2]: decimal={}, binary={}", v2, Integer.toBinaryString(v2));

        // AND '&' (logical multiplication), 1 if both are 1
        log.info(
            "    Bits[AND][v1 & v2]: v1={}, v2={}, res={}",
            Integer.toBinaryString(v1),
            Integer.toBinaryString(v2),
            Integer.toBinaryString(v1 & v2)
        );

        // OR '|' (logical addition), 1 if at least one is 1
        log.info(
            "    Bits[ OR][v1 | v2]: v1={}, v2={}, res={}",
            Integer.toBinaryString(v1),
            Integer.toBinaryString(v2),
            Integer.toBinaryString(v1 | v2)
        );

        // XOR '^' (logical exclusive OR), 1 if both are different
        log.info(
            "    Bits[XOR][v1 ^ v2]: v1={}, v2={}, res={}",
            Integer.toBinaryString(v1),
            Integer.toBinaryString(v2),
            Integer.toBinaryString(v1 ^ v2)
        );

        // NOT '~' (logical negation), inverts all the digits
        log.info(
            "    Bits[NOT][~v1]: v1={}, v2={}, res={}",
            Integer.toBinaryString(v1),
            ~v1,
            Integer.toBinaryString(~v1)
        );

        // exclusive
        log.info("    Bits[xor 1]: n ^ 1");
        int start = 25;
        int end = 31;
        for (int i = start; i < end; i++) {
            int iXor = i ^ 1;
            int iMod = (i % 2 == 0) ? (i + 1) : (i - 1);
            log.info("      - {} ^ 1: xor={}, mod={}", i, iXor, iMod);
        }
    }

    /**
     * Shifting of bits.
     */
    private void invokeShifting() {
        log.info(" * Bits shifting");

        int v1 = -72;
        log.info("    Bits[value]: decimal=" + v1 + ", binary=" + Integer.toBinaryString(v1));

        // doubling: x << y = x * 2^y
        log.info("    Bits[<<][doubling]: x << y = x * 2^y");
        for (int i = 0; i < 10; i++) {
            log.info(
                "      - {} << {}: res={}, bres={}",
                v1,
                i,
                v1 << i,
                Integer.toBinaryString(v1 << i)
            );
        }

        // halving: x >> y = x / 2^y
        log.info("    Bits[>>][halving]: x >> y = x / 2^y");
        for (int i = 0; i < 10; i++) {
            log.info(
                "      - {} >> {}: res={}, bres={}",
                v1,
                i,
                v1 >> i,
                Integer.toBinaryString(v1 >> i)
            );
        }

        // unsigned shifting: x >>> y
        log.info("    Bits[>>>][unsigned]: x >>> y");
        for (int i = 0; i < 10; i++) {
            log.info(
                "      - {} >>> {}: res={}, bres={}",
                v1,
                i,
                v1 >>> i,
                Integer.toBinaryString(v1 >>> i)
            );
        }

        // pow of two: 1 << n = 2^n
        int p1 = 1 << 0; // 2^0 = 1
        int p2 = 1 << 1; // 2^1 = 2
        int p4 = 1 << 2; // 2^2 = 4
        int p8 = 1 << 3; // 2^3 = 8
        log.info("    Bits[pow of 2][1 << n = 2^n]: {}, {}, {}, {}", p1, p2, p4, p8);
    }
}
