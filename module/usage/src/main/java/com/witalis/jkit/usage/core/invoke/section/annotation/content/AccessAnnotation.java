package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import java.lang.annotation.*;

/**
 * Desc: Built-in annotation
 * User: Wellaxis
 * Date: 4/10/2022
 */
// annotation built-in
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(
    // list of annotation access
    {
        ElementType.FIELD,
        ElementType.MODULE,
        ElementType.METHOD,
        ElementType.CONSTRUCTOR,
        ElementType.ANNOTATION_TYPE,
        ElementType.LOCAL_VARIABLE,
        ElementType.PACKAGE,
        ElementType.PARAMETER,
        ElementType.TYPE,
        ElementType.TYPE_USE,
        ElementType.TYPE_PARAMETER
    }
)
@Inherited
@Deprecated
@MarkerAnnotation
public @interface AccessAnnotation {
}
