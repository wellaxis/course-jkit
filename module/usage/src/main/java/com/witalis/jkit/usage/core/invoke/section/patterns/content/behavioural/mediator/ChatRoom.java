package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.mediator;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Desc:
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class ChatRoom implements ChatMediator {

    @Override
    public void showMessage(ChatUser user, String message) {
        LocalDateTime dt = LocalDateTime.now();
        var format = dt.format(DateTimeFormatter.ISO_DATE_TIME);
        log.info("{} [{}]: {}", format, user.getName(), message);
    }
}
