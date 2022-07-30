package com.witalis.jkit.usage.core.invoke.section.profile;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: compact profiles
 * User: Wellaxis
 * Date: 2019/11/19
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ProfileInvoker extends Invoker {

    public ProfileInvoker() {
        setTitle("Profile chapter.");
    }

    @Override
    @Deprecated
    public void invoke() {
        // profiles: compact1 < compact2 < compact3 - since JDK 8
        // Using a compact profile reduces the size of the library
        // Sample: javac -profile compact2 Teat.java
        log.info("It is the deprecated functionality now.");

        // Instead of profiles use module combination - since JDK 9
    }
}
