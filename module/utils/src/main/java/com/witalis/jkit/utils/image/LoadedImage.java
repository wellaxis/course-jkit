package com.witalis.jkit.utils.image;

import java.awt.*;

public class LoadedImage extends Canvas {
    private Image image;

    public LoadedImage(Image i) {
        set(i);
    }

    void set(Image i) {
        image = i;
        repaint();
    }

    public void paint(Graphics g) {
        if (image == null) {
            g.drawString("no image", 10, 30);
        } else {
            g.drawImage(image, 0, 0, this);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(
            image.getWidth(this),
            image.getHeight(this)
        );
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
