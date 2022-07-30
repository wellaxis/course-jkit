package com.witalis.jkit.usage.core.invoke.section.beans.content.scenario;

import lombok.extern.slf4j.Slf4j;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Desc: introspection
 * User: Wellaxis
 * Date: 05.04.2021
 */
@Slf4j
public class ColorsIntrospector {

    public ColorsIntrospector() {
    }

    public void introspection() {
        try {
            // bean
            Class<?> bean = Class.forName("com.witalis.jkit.usage.core.invoke.section.beans.content.scenario.Colors");
            log.info("Bean: {}", bean.getCanonicalName());
            // bean info
            BeanInfo beanInfo = Introspector.getBeanInfo(bean);
            log.info("Bean info: {}", beanInfo.getClass().getCanonicalName());
            // properties
            log.info("Bean properties:" );
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : properties) {
                log.info(" - {}", property.getName());
            }
            // events
            log.info("Bean events:" );
            EventSetDescriptor[] events = beanInfo.getEventSetDescriptors();
            for (EventSetDescriptor event : events) {
                log.info(" - {}", event.getName());
            }
        } catch (Exception e) {
            log.error("Colors introspection error: {}", e.getLocalizedMessage());
        }
    }
}
