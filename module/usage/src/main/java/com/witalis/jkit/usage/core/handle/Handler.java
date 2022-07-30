package com.witalis.jkit.usage.core.handle;

import com.witalis.jkit.usage.core.action.IHandler;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: abstract handler
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@ToString
@NoArgsConstructor
public abstract class Handler implements IHandler {
    // constant
    public static final String MSG_CHAR = "/";
    public static final int MSG_LENGTH = 80;
    // variable
    protected String title;
    protected boolean active;

    protected Handler(boolean active) {
        setActive(active);
    }

    @Override
    public boolean active() {
        return isActive();
    }

    @Override
    public void header() {
        log.info(getMarkup());
        log.info(MSG_CHAR.repeat(2) + " " + getTitle());
        log.info(getMarkup());
    }

    @Override
    public void footer() {
        log.info(getMarkup());
    }

    private String getMarkup() {
        return MSG_CHAR.repeat(MSG_LENGTH);
    }
}
