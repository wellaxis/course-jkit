package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import com.witalis.jkit.usage.core.invoke.section.annotation.AnnotationInvoker;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Desc: Container annotation
 * User: Wellaxis
 * Date: 4/10/2022
 */
// annotation repeated container
@Retention(RetentionPolicy.RUNTIME)
public @interface Container {
    ContainerItem[] value();
}
