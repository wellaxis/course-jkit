package com.witalis.jkit.usage.core.invoke.section.modifiers.content.publics;

/**
 * Desc: Public members
 * User: Wellaxis
 * Date: 4/11/2022
 */
public class PublicMembers {
    public int code = 100;

    public PublicMembers(int code) {
        this.code = code;
    }

    public String getMessage() {
        return "Message is protected";
    }
}
