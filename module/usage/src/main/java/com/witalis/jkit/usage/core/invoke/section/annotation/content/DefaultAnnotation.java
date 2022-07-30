package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Desc: Default annotation
 * User: Wellaxis
 * Date: 4/10/2022
 */
// annotation with default values
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultAnnotation {
    String NOTES = "Default comments";
    int ID = 84375645;

    // default value
    String comment() default NOTES;
    int version() default ID;

    // int test() throws ClassCastException - unable throwing
    // int test(int param) - unable parameters in methods
}
