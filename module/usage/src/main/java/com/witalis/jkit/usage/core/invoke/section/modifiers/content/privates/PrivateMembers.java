package com.witalis.jkit.usage.core.invoke.section.modifiers.content.privates;

/**
 * Desc: Private members
 * User: Wellaxis
 * Date: 4/11/2022
 */
public class PrivateMembers {
    private int code = 100;

    public PrivateMembers(int code) {
        this.code = code;
    }

    private String getMessage() {
        return "Message is private";
    }
}
