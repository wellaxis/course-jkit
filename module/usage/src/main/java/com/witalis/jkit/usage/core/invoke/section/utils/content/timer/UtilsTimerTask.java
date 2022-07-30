package com.witalis.jkit.usage.core.invoke.section.utils.content.timer;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.TimerTask;

/**
 * Desc: utilities/timer task
 * User: Wellaxis
 * Date: 5/23/2022
 */
@Slf4j
public class UtilsTimerTask extends TimerTask {

    @Override
    public void run() {
        log.info(" > Utils timer task -> executed: " + LocalTime.now());
    }
}
