package com.witalis.jkit.usage.core.invoke.section.modifiers.content.protecteds;

/**
 * Desc: Protected members
 * User: Wellaxis
 * Date: 4/11/2022
 */
public class ProtectedMembers {
    protected int code = 100;

    public ProtectedMembers(int code) {
        this.code = code;
    }

    protected String getMessage() {
        return "Message is protected";
    }
}
