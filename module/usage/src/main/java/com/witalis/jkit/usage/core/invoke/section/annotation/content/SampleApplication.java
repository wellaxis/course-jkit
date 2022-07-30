package com.witalis.jkit.usage.core.invoke.section.annotation.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Class with annotations
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Slf4j
@MarkerAnnotation
@DefaultAnnotation(comment = "Overridden comment")
@SimpleAnnotation(info = "Annotation chapter", value = 0)
public class SampleApplication {

    @SimpleAnnotation(info = "It's constant", value = 1)
    public static final int REVISION_UNDEFINED = -1;

    @SimpleAnnotation(info = "It's variable", value = 2)
    private int revision;

    @SimpleAnnotation(info = "Default constructor", value = 3)
    public SampleApplication() {
        this.revision = REVISION_UNDEFINED;
    }

    @SimpleAnnotation(info = "Basic constructor", value = 4)
    public SampleApplication(int revision) {
        this.revision = revision;
    }

    @SimpleAnnotation(info = "Super info", value = 5)
    @Override
    public String toString() {
        return super.toString();
    }

    @SimpleAnnotation(info = "Super equals", value = 6)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleApplication that = (SampleApplication) o;
        return revision == that.revision;
    }

    @SimpleAnnotation(info = "Super hash", value = 7)
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @SimpleAnnotation(info = "Show information", value = 8)
    public void showInfo(int id, String message) {
        log.info("Show {} information: {}", id, message);
    }

    @SingleAnnotation(9)
    public void getVersion(int version) {
        log.info("Version: {}", version);
    }
}
