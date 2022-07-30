package com.witalis.jkit.usage.core.invoke.section.graphics.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Desc: Graph2 Demo
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class Graph2Demo extends Frame {

    public Graph2Demo() {
        setBackground(Color.cyan);
        setForeground(Color.gray);
    }

    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        // line
        Line2D line = new Line2D.Double(30, 30, 200, 200);
        g2d.draw(line);
    }
}
