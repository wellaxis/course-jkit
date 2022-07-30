package com.witalis.jkit.usage.core.invoke.section.events.content;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.event.*;

/**
 * Desc: mouse event demo
 * User: Wellaxis
 * Date: 5/22/2022
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MouseEventDemo extends Frame implements MouseListener, MouseMotionListener {
    private String message = "";
    private int x = 0;
    private int y = 0;

    public MouseEventDemo() {
        setBackground(Color.cyan);
        setForeground(Color.gray);
        setTitle("Demo");
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

    public void paint(Graphics graphics) {
        graphics.drawString(getMessage(), getX(), getY());
    }

    // mouse click
    public void mouseClicked(MouseEvent mouseEvent) {
        message = message + " -- click received";
        repaint();
    }

    // mouse enter
    public void mouseEntered(MouseEvent mouseEvent) {
        setX(100);
        setY(100);
        message = message + " -- mouse entered";
        repaint();
    }

    // mouse exit
    public void mouseExited(MouseEvent mouseEvent) {
        setX(100);
        setY(100);
        message = " -- mouse exited";
        repaint();
    }

    // button press
    public void mousePressed(MouseEvent mouseEvent) {
        setX(mouseEvent.getX());
        setY(mouseEvent.getY());
        message = " -- mouse pressed";
        repaint();
    }

    // button release
    public void mouseReleased(MouseEvent mouseEvent) {
        setX(mouseEvent.getX());
        setY(mouseEvent.getY());
        message = " -- mouse released";
        repaint();
    }

    // mouse drag
    public void mouseDragged(MouseEvent mouseEvent) {
        setX(mouseEvent.getX());
        setY(mouseEvent.getY());
        message = " -- mouse dragged: [" + getX() + "," + getY() + "]";
        repaint();
    }

    // mouse move
    public void mouseMoved(MouseEvent mouseEvent) {
        message = " -- mouse moved: [" + mouseEvent.getX() + "," + mouseEvent.getY() + "]";
        repaint();
    }
}
