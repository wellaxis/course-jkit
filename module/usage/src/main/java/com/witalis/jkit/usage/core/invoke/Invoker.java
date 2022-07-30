package com.witalis.jkit.usage.core.invoke;

import com.witalis.jkit.usage.core.action.IInvoker;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Desc: abstract invoker
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@ToString
@NoArgsConstructor
public abstract class Invoker implements IInvoker, Runnable {
    // constant
    public static final String MSG_CHAR = "=";
    public static final int MSG_LENGTH = 80;
    // variable
    protected String title;
    protected boolean active;

    protected Invoker(boolean active) {
        setActive(active);
    }

    @Override
    public boolean active() {
        return isActive();
    }

    @Override
    public void header() {
        log.info(getMarkup());
        var info = StringUtils.center(getTitle(), MSG_LENGTH - 4, ' ');
        log.info(MSG_CHAR.repeat(2) + info + MSG_CHAR.repeat(2));
        log.info(getMarkup());
    }

    @Override
    public void footer() {
        log.info(getMarkup());
    }

    @Override
    public void output() {
        log.info("");
    }

    /**
     * It runs the invoker task.
     */
    @Override
    public void run() {
        output();
        header();
        invoke();
        footer();
        output();
    }

    private String getMarkup() {
        return MSG_CHAR.repeat(MSG_LENGTH);
    }
}
