package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.observer;

import lombok.Data;
import lombok.ToString;

/**
 * Desc: News Channel - observer.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@ToString
public class NewsChannel implements Channel {
    private String news = "Absent";

    @Override
    public void update(Object news) {
        setNews((String) news);
    }
}
