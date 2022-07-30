package com.witalis.jkit.usage.core.invoke.section.events;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.events.content.MouseEventDemo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

/**
 * Desc: event declarations
 * User: Wellaxis
 * Date: 2021/02/15
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class EventInvoker extends Invoker {

    public EventInvoker() {
        setTitle("Event chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // event
        log.info("## Event");
        invokeEvent();
        // tab
        log.info("");
        // frame
        log.info("## Frame");
        invokeFrame();
        // tab
        log.info("");
        // adapter
        log.info("## Adapter");
        invokeAdapter();
    }

    /**
     * Basic postulates of events.
     */
    private void invokeBasis() {
        // Changing the state of an object is known as an event.
        // The java.awt.event package provides many event classes and Listener interfaces for event handling.

        log.info("Event - changing the state of an object");
    }

    /**
     * Base event declaration.
     */
    private void invokeEvent() {
        log.info("-- Base Event");

        var object = "source";

        // parent event
        EventObject eventObject = new EventObject(object);
        log.info(" - event [object]: {}", eventObject);

        // awt event
        var type = ActionEvent.ACTION_PERFORMED;
        var command = "press";
        AWTEvent awtEvent = new ActionEvent(object, type, command);
        var id = awtEvent.getID();
        log.info(" - event [awt]: ID = {}, {}", id, awtEvent);
    }

    /**
     * Frame event implementation.
     */
    private void invokeFrame() {
        final var graphics = System.getProperty("graphics", "false");
        final var activate = graphics.equalsIgnoreCase("true");

        log.info("-- AWT Frame");

        if (!activate) {
            log.info(" - to activate GUI sample, specify 'graphics' system properties");
            return;
        }
        // demo
        try {
            var demo = new MouseEventDemo();
            demo.setSize(new Dimension(300, 300));
            demo.setTitle("Mouse Event Demo");
            demo.setVisible(true);
        } catch (Exception e) {
            log.error("Unable to initialize graphics: {}", e.getMessage());
        }
    }

    /**
     * Event adapters.
     */
    private void invokeAdapter() {
        log.info("-- Event Adapter");

        // it is convenient to use inner classes and anonymous inner classes (without name)

        class DemoMouseAdapter extends MouseAdapter {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                log.info(" * mouse clicked");
            }
        }

        var demoMouseClicker = new DemoMouseAdapter();
        log.info(" - MouseEvent [clicker]: {}", demoMouseClicker);

        class DemoMouseMotionAdapter extends MouseMotionAdapter {

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                log.info(" * mouse dragged");
            }
        }

        var demoMouseDragger = new DemoMouseMotionAdapter();
        log.info(" - MouseEvent [dragger]: {}", demoMouseDragger);
    }
}
