package com.witalis.jkit.usage.core.invoke.section.beans.content.scenario;

import lombok.extern.slf4j.Slf4j;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * Desc: bean info
 * User: Wellaxis
 * Date: 05.04.2021
 */
@Slf4j
public class ColorsBeanInfo extends SimpleBeanInfo {

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(
                "rectangular",
                Colors.class
            );
            return new PropertyDescriptor[]{descriptor};
        } catch (Exception e) {
            log.error("Java Bean exception occurred:{}", e.getLocalizedMessage());
        }
        return new PropertyDescriptor[0];
    }
}
