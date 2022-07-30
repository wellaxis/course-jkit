package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.mediator;

import lombok.Data;
import lombok.ToString;

/**
 * Desc: Chat User class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@ToString
public
class ChatUser {
    private String name;
    private ChatMediator mediator;

    public ChatUser(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void send(String message) {
        mediator.showMessage(this, message);
    }
}
