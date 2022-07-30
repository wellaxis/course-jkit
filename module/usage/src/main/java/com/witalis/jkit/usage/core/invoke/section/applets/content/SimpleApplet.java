package com.witalis.jkit.usage.core.invoke.section.applets.content;

import java.applet.Applet;
import java.awt.*;

/**
 * Desc: Applet sample
 * User: Wellaxis
 * Date: 4/10/2022
 */
// <applet code="SimpleApplet" width=200 heigth=60></applet>
@SuppressWarnings("removal")
public class SimpleApplet extends Applet {

    public void paint(Graphics g) {
        g.drawString("Simple Applet Sample", 20, 20);
    }
}
