package com.witalis.jkit.usage.core.invoke.section.overriding;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.overriding.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.ParseException;

/**
 * Desc: Overriding polymorphism
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class OverridingInvoker extends Invoker {

    public OverridingInvoker() {
        setTitle("Overriding chapter.");
    }

    @Override
    public void invoke() {
        // overriding
        log.info("## Overriding");
        overriding();
    }

    /**
     * Overriding details.
     */
    public void overriding() {
        // Polymorphism: runtime, dynamic, late binding

        // parent
        Parent parent = new Parent();
        parent.overrideUsual("Text", 100, true);
        parent.overrideParam(100L);
        // parent.overrideModifier("item-1", "item-2", "item-3");
        parent.overrideReturn("59");
        try {
            parent.overrideException("Java", "world");
        } catch (ParseException | IOException e) {
            log.error("Overriding exceptions", e);
        }
        log.info("--");

        // parent.nonOverridePrivate("Private");
        parent.nonOverrideFinal("Final");
        Parent.nonOverrideStatic("Static");


        // child
        Child child = new Child();
        child.overrideUsual("Text", 100, true);
        child.overrideParam(100L);
        child.overrideModifier("item-1", "item-2", "item-3");
        child.overrideReturn("59");
        child.overrideException("Java", "world");

        Child.nonOverrideStatic("Static");

        // shadow
        Parent shadow = new Child();
        Parent.nonOverrideStatic("parent's call");
        Child.nonOverrideStatic("child's call");
        shadow.nonOverrideStatic("parent's call");
    }
}
