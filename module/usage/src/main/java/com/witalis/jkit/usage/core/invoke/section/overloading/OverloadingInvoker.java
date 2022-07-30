package com.witalis.jkit.usage.core.invoke.section.overloading;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.overloading.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * Desc: Overloading polymorphism
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class OverloadingInvoker extends Invoker {

    public OverloadingInvoker() {
        setTitle("Overloading chapter.");
    }

    @Override
    public void invoke() {
        // overloading
        log.info("## Overloading");
        overloading();
    }

    /**
     * Overloading details.
     */
    public void overloading() {
        // In a Java class, it is possible to create methods with the same name if they differ in parameters.
        // This is known as method overloading in Java.

        Overload overloading = new Overload();
        overloading.overload();
        overloading.overload("single");
        overloading.overload(123);
        var overload = overloading.overload(321L);
        var nvl = overloading.overload(null, "default");
        overloading.overload("item-1", "item-2", "item-3");
        // overloading.overload(List.of("one", "two", "three"));
        overloading.overload(Set.of("one", "two", "three"));
        Overload.overload(Map.of("1", "one", "2", "two"));
    }
}
