package com.witalis.jkit.usage.core.invoke.section.cloning;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.cloning.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Cloning API
 * User: Wellaxis
 * Date: 2022/03/23
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class CloningInvoker extends Invoker {

    public CloningInvoker() {
        setTitle("Cloning chapter.");
    }

    @Override
    public void invoke() {
        // native
        log.info("-- Native");
        invokeNative();
        // tab
        log.info("");
        // constructor
        log.info("-- Constructor");
        invokeConstructor();
    }

    /**
     * Cloning using native algorithm.
     */
    public void invokeNative() {
        // native algorithm:
        // 1) primitives - the same values [ok]
        // 2) references - the same objects [bad]

        House house = Factory.makeHouse();
        log.info("House [original]: {}", house.toString());

        // cloning
        {
            try {
                House clone = house.clone();
                log.info("House [cloning/native]: {}", clone.toString());
            } catch (CloneNotSupportedException e) {
                log.error("Cloning errors: {}", e.getMessage());
            }
        }
    }

    /**
     * Cloning via constructor.
     */
    public void invokeConstructor() {
        // cloning via constructor:
        // 1) shallow - clone only primitives only
        // 2) deep - clone references and collections

        House house = Factory.makeHouse();
        log.info("House [original]: {}", house.toString());

        // cloning - shallow
        {
            House clone = house.cloning(false);
            log.info("House [cloning/shallow]: {}", clone.toString());
        }

        // cloning - deep
        {
            House clone = house.cloning(true);
            log.info("House [cloning/deep]: {}", clone.toString());
        }
    }
}
