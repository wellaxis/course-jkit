package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Desc: Simple annotation
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleAnnotation {
    // methods as fields
    String info();
    int value();
}
