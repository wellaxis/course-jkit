package com.witalis.jkit.usage.core.invoke.section.io.context;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Desc: I/O Enumeration
 * User: Wellaxis
 * Date: 02.02.2022
 */
@Slf4j
public class IOEnum implements Enumeration<ByteArrayInputStream> {
    private Enumeration<String> data;

    public IOEnum(Vector<String> files) {
        this.data = files.elements();
    }

    @Override
    public boolean hasMoreElements() {
        return data.hasMoreElements();
    }

    @Override
    public ByteArrayInputStream nextElement() {
        try {
            return new ByteArrayInputStream(data.nextElement().getBytes());
        } catch (Exception e) {
            log.error("File Input Stream Enumeration errors: " + e.getLocalizedMessage());
        }
        return null;
    }
}