package com.witalis.jkit.usage.core.invoke.section.utils.content.bundle;

import java.util.ListResourceBundle;

public class CurrencyBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {"currency", "EUR"},
            {"price", 30.0D}
        };
    };
}
