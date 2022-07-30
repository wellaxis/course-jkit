package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Help Page class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class HelpPage extends WebPage {

    public HelpPage(Theme theme) {
        super(theme);
    }

    @Override
    public void getContent() {
        log.info("Web page is Help in {}", theme.getColor());
    }
}
