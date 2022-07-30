package com.witalis.jkit.usage.core.invoke.section.exception.content;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Desc: Final rethrow = more precise rethrow -- since JDK 7
 * User: Wellaxis
 * Date: 4/27/2022
 */
@Slf4j
public class PreciseExample {
    boolean printTrace = false;

    public void rethrow() throws ParseException, IOException {
        try {
            new SimpleDateFormat("yyyyMMdd").parse("foo");
            new FileReader("file.txt").read();
        } catch (final Exception e) {
            if (printTrace) e.printStackTrace();
            log.error("Catch [" + e.getClass() + "]: " + e.getMessage());
            throw e;
        }
    }
}
