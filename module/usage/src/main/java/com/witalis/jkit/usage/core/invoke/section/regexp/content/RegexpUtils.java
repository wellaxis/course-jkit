package com.witalis.jkit.usage.core.invoke.section.regexp.content;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc: Regex utilities
 * User: Wellaxis
 * Date: 4/27/2022
 */
public final class RegexpUtils {

    private RegexpUtils() {
        super();
    }

    public static int qty(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }
}
