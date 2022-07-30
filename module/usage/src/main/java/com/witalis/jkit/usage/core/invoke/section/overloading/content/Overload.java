package com.witalis.jkit.usage.core.invoke.section.overloading.content;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc: Overload class
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Slf4j
public class Overload {

    private String prefix() {
        return "Info: ";
    }

    // overloading method - no args
    public void overload() {
        log.info(prefix() + "none");
    }

    // overloading method - single argument
    public void overload(String arg) {
        log.info(prefix() + arg);
    }

    // overloading method - another argument
    public void overload(Integer arg) {
        log.info(prefix() + arg);
    }

    // overloading method - return value
    public String overload(Long arg) {
        log.info(prefix() + arg);
        return String.valueOf(arg);
    }

    // overloading method - several args
    public String overload(String arg, String def) {
        var value = arg != null ? arg : def;
        log.info(prefix() + value);
        return value;
    }

    // overloading method - multiple args
    public void overload(String... args) {
        log.info(prefix() + Arrays.toString(args));
    }

    // overloading method - private modifier
    private void overload(List<String> args) {
        log.info(prefix() + args.toString());
    }

    // overloading method - final modifier
    public final void overload(Set<String> args) {
        log.info(prefix() + args.toString());
    }

    // overloading method - static modifier
    public static void overload(Map<String, String> args) {
        log.info("Info: " + args.toString());
    }
}
