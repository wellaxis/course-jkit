package com.witalis.jkit.usage.core.invoke.section.beans.content.scenario;

import lombok.extern.slf4j.Slf4j;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Bean class
 * User: Wellaxis
 * Date: 05.04.2021
 */
@Slf4j
public class Colors extends Canvas implements Serializable {
    transient private Color color;
    private boolean rectangular;

    public Colors() {
        addMouseListener(
            new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    change();
                }
            }
        );
        rectangular = false;
        setSize(200, 100);
        change();
    }

    public boolean isRectangular() {
        return rectangular;
    }

    public void setRectangular(boolean rectangular) {
        this.rectangular = rectangular;
        repaint();
    }

    public void change() {
        color = randomColor();
        repaint();
    }

    private Color randomColor() {
        var random = ThreadLocalRandom.current();
        int r = random.nextInt(0, 256);
        int g = random.nextInt(0, 256);
        int b = random.nextInt(0, 256);
        return new Color(r, g, b);
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        int h = d.height;
        int w = d.width;
        g.setColor(color);
        if (rectangular) {
            g.fillRect(0, 0, w - 1, h - 1);
        } else {
            g.fillOval(0, 0, w - 1, h - 1);
        }
    }
}
