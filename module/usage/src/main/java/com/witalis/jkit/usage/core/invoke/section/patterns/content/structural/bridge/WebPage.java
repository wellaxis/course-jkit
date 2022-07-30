package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.bridge;

/**
 * Desc: Web Page class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public abstract class WebPage implements Page {
    protected Theme theme;

    protected WebPage(Theme theme) {
        this.theme = theme;
    }
}
