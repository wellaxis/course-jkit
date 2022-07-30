package com.witalis.jkit.usage.core.invoke.section.graphics.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

/**
 * Desc: Image Producer Demo
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public
class ImageProducerDemo extends Frame {
    private Image image;
    private int w = 512;
    private int h = 512;

    public ImageProducerDemo() throws HeadlessException {
        int[] pixels = new int[w * h];
        int i = 0;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int r = (x ^ y) & 0xff;
                int g = (x * 2 ^ y * 2) & 0xff;
                int b = (x * 4 ^ y * 4) & 0xff;
                pixels[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
            }
        }
        image = createImage(new MemoryImageSource(w, h, pixels, 0, w));
        addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, getInsets().left, getInsets().top, null);
    }
}
