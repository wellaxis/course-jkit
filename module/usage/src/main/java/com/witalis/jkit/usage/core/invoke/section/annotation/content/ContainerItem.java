package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Desc: Container item annotation
 * User: Wellaxis
 * Date: 4/10/2022
 */
// annotation repeated item
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Container.class)
public @interface ContainerItem {
    String info() default "Information";
    int number() default 1000;
}
