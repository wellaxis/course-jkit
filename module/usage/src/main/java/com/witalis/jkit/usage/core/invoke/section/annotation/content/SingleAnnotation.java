package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Desc: Single annotation
 * User: Wellaxis
 * Date: 4/10/2022
 */
// annotation single-member
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleAnnotation {
    // name of method equals to - value
    int value();
}
