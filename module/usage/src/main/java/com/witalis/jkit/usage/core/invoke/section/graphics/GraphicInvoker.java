package com.witalis.jkit.usage.core.invoke.section.graphics;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.graphics.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

/**
 * Desc: Graphics
 * User: Wellaxis
 * Date: 2021/02/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class GraphicInvoker extends Invoker {
    private static final boolean activate = false;

    public GraphicInvoker() {
        setTitle("Graphics chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // awt
        log.info("## AWT");
        invokeAwt();
        // tab
        log.info("");
        // swing
        log.info("## Swing");
        invokeSwing();
        // tab
        log.info("");
        // java fx
        log.info("## JavaFX");
        invokeJavaFx();
    }

    /**
     * Basic postulates of GUI.
     */
    private void invokeBasis() {
        // GUI in Java

        log.info("AWT: Abstract Window Toolkit - the first framework with native components.");
        log.info("Swing: Portable framework with multiple Look&Feels for any platform.");
        log.info("SWT: Standard Widget Toolkit - native components via JNI from IBM.");
        log.info("JavaFX: Instead of Swing - not native components via FXML (CSS & XML).");
    }

    /**
     * AWT functionality.
     */
    private void invokeAwt() {
        log.info("-- AWT");
        // graphics
        log.info("-- Graphic");
        invokeAwtGraphics();
        // tab
        log.info("");
        // objects
        log.info("-- Object");
        invokeAwtObject();
        // tab
        log.info("");
        // components
        log.info("-- Component");
        invokeAwtComponent();
        // tab
        log.info("");
        // images
        log.info("-- Image");
        invokeAwtImage();
        // tab
        log.info("");
        // image producers
        log.info("-- Image Producer");
        invokeAwtImageProducer();
    }

    private void invokeAwtGraphics() {
        log.info("---- AWT Graphics");

        // instance
        Graphics g;
        Graphics2D g2;
    }

    private void invokeAwtObject() {
        log.info("---- AWT Objects");

        if (!activate) {
            log.info("Graphics mode is switched off.");
            return;
        }
        // demo
        try {
            var demo = new GraphDemo();
            demo.setSize(new Dimension(300, 300));
            demo.setTitle("Graphics Demo");
            demo.setVisible(true);
        } catch (Exception e) {
            log.error("Unable to initialize AWT graphics: {}", e.getMessage());
        }
    }

    private void invokeAwtComponent() {
        log.info("---- AWT Components");

        if (!activate) {
            log.info("Graphics mode is switched off.");
            return;
        }
        // demo
        try {
            var demo = new ComponentDemo();
            demo.setSize(new Dimension(500, 500));
            demo.setTitle("Component Demo");
            demo.setVisible(true);
        } catch (Exception e) {
            log.error("Unable to initialize AWT graphics: {}", e.getMessage());
        }
    }

    private void invokeAwtImage() {
        log.info("---- AWT Images");

        if (!activate) {
            log.info("Graphics mode is switched off.");
            return;
        }
        // demo
        try {
            var demo = new ImageDemo();
            demo.setSize(new Dimension(900, 520));
            demo.setTitle("Image Demo");
            demo.setVisible(true);
        } catch (Exception e) {
            log.error("Unable to initialize AWT graphics: {}", e.getMessage());
        }
    }

    private void invokeAwtImageProducer() {
        log.info("---- AWT Image Producers");

        if (!activate) {
            log.info("Graphics mode is switched off.");
            return;
        }
        // demo
        try {
            var demo = new ImageProducerDemo();
            demo.setSize(new Dimension(400, 400));
            demo.setTitle("Image Producer");
            demo.setVisible(true);
        } catch (Exception e) {
            log.error("Unable to initialize AWT graphics: {}", e.getMessage());
        }
    }

    /**
     * Swing functionality.
     */
    private void invokeSwing() {
        log.info("-- Swing");
        // objects
        log.info("-- Object");
        invokeSwingObject();
    }

    private void invokeSwingObject() {
        log.info("---- Swing Objects");

        if (!activate) {
            log.info("Graphics mode is switched off.");
            return;
        }
        // demo
        try {
            SwingUtilities.invokeLater(SwingDemo::new);
        } catch (Exception e) {
            log.error("Unable to initialize Swing graphics: {}", e.getMessage());
        }
    }

    /**
     * Java FX functionality.
     */
    private void invokeJavaFx() {
        log.info("-- JavaFX");
        // objects
        log.info("-- Object");
        invokeJavaFxObject();
    }

    private void invokeJavaFxObject() {
        log.info("    It has been added into jdk 8.");
        log.info("    It has been removed since jdk 11.");
        log.info("    JavaFX 16 - is the latest release launched in March 2021.");
    }
}
