package com.witalis.jkit.usage.core.invoke.section.graphics.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Desc: Graph Demo
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class GraphDemo extends Frame {

    public GraphDemo() {
        setBackground(Color.cyan);
        setForeground(Color.gray);
        addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        initialize();
    }

    public void initialize() {
        var g = getGraphics();
        if (g != null) {
            // color
            var color = Color.cyan;
            var hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
            var rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
            log.info(" Color: {}, RGB {}, HSB {}", color.toString(), rgb, hsb);
            // font
            var font = g.getFont();
            var name = font.getName();
            var style = font.getStyle();
            var size = font.getSize();
            log.info(" Font: {}, style {}, size {}", name, style, size);
            // env
            log.info(" Available fonts:");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            var fonts = ge.getAvailableFontFamilyNames();
            for (String f : fonts) {
                log.info(" - {}", f);
            }
        }
    }

    public void paint(Graphics g) {
        var metrics = g.getFontMetrics();
        // line
        g.drawLine(100, 45, 160, 150);
        g.drawLine(100, 150, 150, 70);
        g.drawLine(100, 80, 170, 120);
        // arc
        g.drawArc(100, 45, 75, 95, 0, 90);
        // rectangle
        g.drawRect(200, 50, 25, 35);
        g.fillRect(200, 100, 25, 35);
        g.drawRoundRect(250, 50, 25, 35, 10, 10);
        g.fillRoundRect(250, 100, 25, 35, 20, 20);
        // circle
        g.drawOval(200, 150, 25, 35);
        g.fillOval(200, 200, 25, 35);
        g.drawOval(250, 150, 25, 25);
        // polygon
        int[] x = {10, 35, 50, 65, 80, 95};
        int[] y = {40, 105, 105, 110, 95, 95};
        g.drawPolygon(x, y, 6);
        g.fillPolygon(
            new int[]{10, 35, 50, 65, 80, 95},
            new int[]{140, 205, 205, 210, 195, 195}, 6
        );
        // 3d
        g.draw3DRect(120, 170, 40, 40, false);
        g.fill3DRect(140, 190, 40, 40, true);
        // text
        int textX = 20, textY = 260;
        g.drawString("Java is cool!", textX, textY);
        textY += metrics.getHeight();
        g.drawString("The next row)", textX, textY);
        // label
        Label one = new Label("One");
        Label two = new Label("Two");
        Label three = new Label("Three");
        add(one);
        add(two);
        add(three);
    }
}
