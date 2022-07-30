package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Container application
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Slf4j
@MarkerAnnotation
@AccessAnnotation
@Deprecated(since = "1.1")
@SimpleAnnotation(info = "Ok", value = 100)
@SingleAnnotation(50)
@ContainerItem(info = "The 1st", number = 1)
@ContainerItem(info = "The 2nd", number = 2)
@ContainerItem(info = "The 3rd", number = 3)
@ContainerItem(info = "The 4th", number = 4)
public class ContainerApplication {

    @AccessAnnotation
    public void info(
        @AccessAnnotation ContainerApplication this,
        @AccessAnnotation String data
    ) throws @AccessAnnotation NullPointerException {
        log.info("Container application information.");
    }
}
